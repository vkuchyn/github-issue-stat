package juja.github.stat;

import com.jcabi.github.Github;
import com.jcabi.github.RtGithub;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
public class LocalStarter {

    public static void main(String[] args) throws IOException {
        Github github = new RtGithub("Insert you private token");
        GithubIssueSystem issueSystem = new GithubIssueSystem(github, "vkuchyn", "socialscore", "@Bizon4ik");
        final List<TimeReport> timeReports = issueSystem.fetchTimeReports();
        System.out.println(timeReports);

        final Map<String, Duration> map = timeReports.stream()
            .collect(
                toMap(
                    TimeReport::getAuthor, TimeReport::getDuration,
                    (d1, d2) -> Duration.ofMinutes(d1.toMinutes() + d2.toMinutes()))
            );
        System.out.println(map);
    }
}
