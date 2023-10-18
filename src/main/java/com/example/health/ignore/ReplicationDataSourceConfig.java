package com.example.health.ignore;



import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@RequiredArgsConstructor
//@Configuration
//@EnableTransactionManagement
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
//@Profile("production")
public class ReplicationDataSourceConfig {
    private final DataSourceKey dataSourceKey;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public DataSource routingDataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        RoutingDataSource routingDataSource = new RoutingDataSource(dataSourceKey);

        Map<Object, Object> sources = Map.of(
                dataSourceKey.getMasterKey(), masterDataSource,
                dataSourceKey.getDefaultSlaveKey(), slaveDataSource
        );

        routingDataSource.setTargetDataSources(sources);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);

        return routingDataSource;
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        return new LazyConnectionDataSourceProxy(routingDataSource(masterDataSource(), slaveDataSource()));
    }
}
