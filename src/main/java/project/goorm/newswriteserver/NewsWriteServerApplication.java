package project.goorm.newswriteserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@RefreshScope
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class NewsWriteServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsWriteServerApplication.class, args);
	}

}
