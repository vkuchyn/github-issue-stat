package juja.github.stat;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;

import java.net.UnknownHostException;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
@Configuration
@TestPropertySource("classpath:mongo.properties")
@ComponentScan
public class TestConfiguration {

//    @Bean
//    public TimeReportDao timeReportDao(MongoTemplate mongo) {
//        return new TimeReportMongoDao(mongo);
//    }

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        return new MongoTemplate(new MongoClient(), "test");
    }

}
