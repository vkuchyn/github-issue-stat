package juja.github.stat;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
public final class GithubIssueSystem implements IssueSystem {
    @Override
    public List<TimeReport> fetchTimeReports() {
        return Arrays.asList(
            new TimeReport(
                "220794586", "https://github.com/vkuchyn/github-issue-stat/issues/1#issuecomment-220794586",
                "Some text before time report\n@JujaD time spent 10m\nSome text after report",
                Duration.ofMinutes(10L)
            ),
            new TimeReport(
                "220794605", "https://github.com/vkuchyn/github-issue-stat/issues/1#issuecomment-220794605",
                "@JujaD time spend 1h 65m some text and more 20m",
                Duration.ofMinutes(145L)
            )
        );
    }
}
