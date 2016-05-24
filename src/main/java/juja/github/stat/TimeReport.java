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

    public TimeReport(String id, String url, String text, String author, Duration duration) {
        this.id = id;
        this.url = url;
        this.text = text;
        this.author = author;
        this.duration = duration;
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
        sb.append(", text='").append(text).append('\'');
        sb.append(", duration=").append(duration);
        sb.append(", author=").append(author);
        sb.append('}');
        return sb.toString();
    }
}
