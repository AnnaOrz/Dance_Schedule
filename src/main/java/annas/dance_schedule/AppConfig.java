package annas.dance_schedule;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="annas.dance_schedule")
@ComponentScan("annas.dance_schedule")
public class AppConfig implements WebMvcConfigurer {



}
