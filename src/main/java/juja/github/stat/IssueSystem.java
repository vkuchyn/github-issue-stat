package juja.github.stat;

import java.io.IOException;
import java.util.List;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
public interface IssueSystem {
    List<TimeReport> fetchTimeReports() throws IOException;
}
