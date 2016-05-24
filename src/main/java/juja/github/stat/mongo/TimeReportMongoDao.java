package juja.github.stat.mongo;

import juja.github.stat.TimeReport;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
@Component
public class TimeReportMongoDao implements TimeReportDao {
    private MongoTemplate mongo;

    @Inject
    public TimeReportMongoDao(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    @Override
    public void saveTimeReport(TimeReport report) {
        mongo.save(report, "timeReport");
    }
}
