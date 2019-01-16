package com.thumbttack.weather_wizard.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import static com.thumbttack.weather_wizard.util.Utils.*;

@Configuration
@EnableBatchProcessing
public class Config {

    @Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(CONFIG_RESOLVER_PREFIX);
        viewResolver.setSuffix(CONFIG_RESOLVER_SUFFIX);
        viewResolver.setContentType(CONFIG_RESOLVER_CONTENT_TYPE);
        viewResolver.setOrder(CONFIG_RESOLVER_ORDER);

        return viewResolver;
    }

}