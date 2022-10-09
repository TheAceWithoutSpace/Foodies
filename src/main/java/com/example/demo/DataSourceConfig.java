package com.example.demo;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource() {
        System.out.println(System.getenv("huroku_db_user"));
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:postgresql://ec2-54-175-79-57.compute-1.amazonaws.com:5432/d3pm3gm1o6vo2c");
        dataSourceBuilder.username("ybkluooxankkmt");
        dataSourceBuilder.password("e7d56ce60cb540064dd8abe00526760bd8855166cf4ea301e515913595a716df");
        return dataSourceBuilder.build();
    }
}
