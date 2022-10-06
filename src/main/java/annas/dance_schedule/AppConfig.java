package annas.dance_schedule;

import annas.dance_schedule.converters.UserConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="annas.dance_schedule")
@ComponentScan("annas.dance_schedule")
public class AppConfig implements WebMvcConfigurer {
    public Set<Converter> getConverters() {
        Set<Converter> converters = new HashSet<>();
        return converters;
    }


    @Bean(name = "conversionService")
    public ConversionService getConversionService() {
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        factory.setConverters(getConverters());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public UserConverter getUserConverter() {
        return new UserConverter();
    }



}
