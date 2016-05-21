package juja.github.stat;

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
    public void calculateTimeTrack() throws Exception {
        List<TimeReport> reports = new GithubIssueSystem().fetchTimeReports();
        assertThat(reports, hasSize(2));
        assertThat(reports, hasItem(new TimeReport(
            "220794586", "https://github.com/vkuchyn/github-issue-stat/issues/1#issuecomment-220794586",
            "Some text before time report\n@JujaD time spent 10m\nSome text after report",
            Duration.ofMinutes(10L)
        )));
        assertThat(reports, hasItem(new TimeReport(
            "220794605", "https://github.com/vkuchyn/github-issue-stat/issues/1#issuecomment-220794605",
            "@JujaD time spend 1h 65m some text and more 20m",
            Duration.ofMinutes(145L)
        )));

    }
}
