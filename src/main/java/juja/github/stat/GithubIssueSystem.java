package juja.github.stat;

import com.jcabi.github.*;

import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
public final class GithubIssueSystem implements IssueSystem {

    private final Repo repo;
    private final String manager;

    public GithubIssueSystem(Github github, String owner, String name, String manager) {
        this.manager = manager.toLowerCase();
        this.repo = github.repos().get(new Coordinates.Simple(owner, name));
    }

    @Override
    public List<TimeReport> fetchTimeReports() throws IOException {
        final EnumMap<Issues.Qualifier, String> qualifiers = new EnumMap<>(Issues.Qualifier.class);
        qualifiers.put(Issues.Qualifier.STATE, Issue.OPEN_STATE);
        qualifiers.put(Issues.Qualifier.LABELS, "prod");//ready,in progress,review,stage,QA,prod,CLOSE IT
        Iterable<Issue> issues = repo.issues().search(Issues.Sort.CREATED, Search.Order.ASC, qualifiers);

        return StreamSupport.stream(issues.spliterator(), true).flatMap(
            issue -> StreamSupport.stream(issue.comments().iterate().spliterator(), true)
        ).filter(comment -> {
            try {
                String content = new Comment.Smart(comment).body().toLowerCase();
                Set<String> words = new HashSet<>(Arrays.asList(content.split("\\s+")));
                return words.contains(this.manager) && containsTimeReport(words)
                    && (words.contains("time")
                    || words.contains("spent") || words.contains("spend"));
            } catch (IOException e) {
                return false;
            }
        }).map(comment -> {
            Comment.Smart smart = new Comment.Smart(comment);
            try {
                String id = String.valueOf(smart.number());
                String url = comment.json().getString("html_url");
                final String body = smart.body().replaceAll("\r\n", "\n");
                return new TimeReport(id, url, body, smart.author().login(), calculateTimeDuration(body));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }).collect(toList());
    }

    private Duration calculateTimeDuration(String content) {
        Set<String> words = new HashSet<>(Arrays.asList(content.toLowerCase().split("\\s+")));
        Duration total = words.stream().filter(word -> isTimeReport(word)).flatMap(word -> {
            List<String> times = new ArrayList<>();
            while (!word.isEmpty()) {
                final String pattern = "(\\d+[h|m]){1}";
                Matcher matcher = Pattern.compile(pattern).matcher(word);
                if (matcher.find()) {
                    times.add(matcher.group(1));
                }
                word = word.replaceFirst(pattern, "");
            }
            return times.stream();
        }).map(time -> {
            Long amount = Long.valueOf(time.replaceAll("\\D", ""));
            if (time.endsWith("h")) {
                return Duration.ofHours(amount);
            } else if (time.endsWith("m")) {
                return Duration.ofMinutes(amount);
            } else return Duration.ZERO;
        }).reduce((left, right) -> left.plus(right)).orElse(Duration.ZERO);
        return total;
    }

    private static boolean containsTimeReport(Set<String> words) {
        for (String word : words) {
            if (isTimeReport(word)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isTimeReport(String word) {
        return word.matches("(\\d+[h|m])+");
    }

}
