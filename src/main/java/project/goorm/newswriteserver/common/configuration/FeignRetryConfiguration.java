package project.goorm.newswriteserver.common.configuration;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignRetryConfiguration {

    @Bean
    public Retryer retryer() {
        // 0.1초 간격아로 시작해 최대 3초의 간격으로 점점 증가하며, 최대 3번 재시도한다.
        return new Retryer.Default(100L, TimeUnit.SECONDS.toMillis(3L), 3);
    }
}
