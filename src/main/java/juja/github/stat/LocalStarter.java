package juja.github.stat;

import com.jcabi.github.Github;
import com.jcabi.github.RtGithub;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
public class LocalStarter {

    public static void main(String[] args) throws IOException {
//        Github github = new RtGithub("Insert you private token");
        Github github = new RtGithub(); //without token github has small rate limit
        GithubIssueSystem issueSystem = new GithubIssueSystem(github, "vkuchyn", "github-issue-stat", "@JujaD");
        final List<TimeReport> timeReports = issueSystem.fetchTimeReports();
        System.out.println(timeReports);

        System.out.println("\nTree issues:");

        final Map<String, List<TimeReport>> tree = timeReports
                .stream()
                .collect(groupingBy(TimeReport::getParentIssueNumber));

        for (String item : tree.keySet()) {
            System.out.println("\nIssue = " + item);
            System.out.println("Time by author:");
            final Map<String, Duration> durationByAuthor = durationByAuthor(tree.get(item));
            System.out.println(durationByAuthor);

            System.out.println("Time by related issues:");
            final Map<String, Duration> durationByIssue = durationByRelatedIssue(tree.get(item));
            System.out.println(durationByIssue);

            System.out.println("------------------------------------------------------------------");
        }

        System.out.println("\nTime by author:");
        final Map<String, Duration> map = durationByAuthor(timeReports);
        System.out.println(map);
    }

    private static Map<String, Duration> durationByAuthor(List<TimeReport> timeReports) {
        return timeReports.stream()
                .collect(
                        toMap(
                                TimeReport::getAuthor, TimeReport::getDuration,
                                (d1, d2) -> Duration.ofMinutes(d1.toMinutes() + d2.toMinutes()))
                );
    }


    private static Map<String, Duration> durationByRelatedIssue(List<TimeReport> timeReports) {
        return timeReports.stream()
                .filter(issue-> ! "FirstIssue".equalsIgnoreCase(issue.getParentIssueNumber()))
                .collect(
                        toMap(
                                TimeReport::getIssueNumber, TimeReport::getDuration,
                                (d1, d2) -> Duration.ofMinutes(d1.toMinutes() + d2.toMinutes()))
                );
    }

}
