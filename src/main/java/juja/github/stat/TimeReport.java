package juja.github.stat;

import com.google.common.base.Objects;

import java.time.Duration;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
public class TimeReport {
    private final String id;
    private final String url;
    private final String text;
    private final Duration duration;

    public TimeReport(String id, String url, String text, Duration duration) {
        this.id = id;
        this.url = url;
        this.text = text;
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
            Objects.equal(this.duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.url, this.text, this.duration);
    }
}
