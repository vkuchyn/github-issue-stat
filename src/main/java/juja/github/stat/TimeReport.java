package juja.github.stat;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;

import java.time.Duration;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
public class TimeReport {
    @Id
    private final String id;
    private final String url;
    private final String text;
    private String author;
    private final Duration duration;
    private final String issueNumber;
    private final String parentIssueNumber;

    public TimeReport(String id, String url, String text, String author, Duration duration,
                      String issueNumber,
                      String parentIssueNumber) {
        this.id = id;
        this.url = url;
        this.text = text;
        this.author = author;
        this.duration = duration;
        this.issueNumber = issueNumber;
        this.parentIssueNumber = parentIssueNumber;
    }

    public String getId() {
        return this.id;
    }

    public String getUrl() {
        return this.url;
    }

    public String getText() {
        return this.text;
    }

    public String getAuthor() {
        return this.author;
    }

    public Duration getDuration() {
        return this.duration;
    }


    public String getIssueNumber() {
        return issueNumber;
    }

    public String getParentIssueNumber() {
        return parentIssueNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        TimeReport that = (TimeReport) o;
        return Objects.equal(this.id, that.id) &&
                Objects.equal(this.url, that.url) &&
                Objects.equal(this.text, that.text) &&
                Objects.equal(this.duration, that.duration) &&
                Objects.equal(this.author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.url, this.text, this.duration, this.author);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TimeReport{");
        sb.append("id='").append(id).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", duration=").append(duration);
        sb.append(", author=").append(author);
        sb.append(", issueNumber=").append(issueNumber);
        sb.append(", parentIssueNumber=").append(parentIssueNumber);
        sb.append('}');
        return sb.toString();
    }
}
