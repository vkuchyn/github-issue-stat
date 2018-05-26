package juja.github.stat;

import com.jcabi.github.Github;
import com.jcabi.github.RtGithub;
import org.junit.Test;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
public class GithubIssueSystemIntegrationTest {
    @Test
    public void fetchTimeReportsFromGithub() throws Exception {
//        Github github = new RtGithub("Insert your api code");
        Github github = new RtGithub(); //without token github has small rate limit
        List<TimeReport> reports = new GithubIssueSystem(github, "vkuchyn", "github-issue-stat", "@JuJad").fetchTimeReports();
        assertThat(reports, hasSize(5));
        assertThat(reports, hasItem(new TimeReport(
                "220794548", "https://github.com/vkuchyn/github-issue-stat/issues/1#issuecomment-220794548",
                "Some text before time report\n@JujaD time spent 10m\nSome text after report",
                "vkuchyn",
                Duration.ofMinutes(10L),
                "1",
                "FirstIssue"
        )));
        assertThat(reports, hasItem(new TimeReport(
                "220794605", "https://github.com/vkuchyn/github-issue-stat/issues/1#issuecomment-220794605",
                "@JujaD time spend 1h65m some text and more 20m",
                "vkuchyn",
                Duration.ofMinutes(145L),
                "1",
                "FirstIssue"

        )));

        System.out.println(reports);

    }
}
