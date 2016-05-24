package juja.github.stat.mongo;

import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import juja.github.stat.TestConfiguration;
import juja.github.stat.TimeReport;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.time.Duration;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbConfigurationBuilder.mongoDb;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestConfiguration.class)
public class TimeReportMongoDaoTest {

    @Inject
    private TimeReportDao dao;
    @Rule
    public MongoDbRule remoteMongoDbRule = new MongoDbRule(mongoDb().databaseName("test").build());

    @Test
    @UsingDataSet(locations = {"/mongodb/empty.json"}, loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    @ShouldMatchDataSet(location = "/mongodb/shouldSaveTimeReport-expected.json")
    public void shouldSaveTimeReport() throws Exception {
        TimeReport report = new TimeReport("123", "url", "test comment",
            "JujaD", Duration.ofHours(2));
        dao.saveTimeReport(report);
    }
}
