package com.gorillas.technicaltest.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@Configuration
class DBInitializer {
    @Bean
    fun initializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer {
        val initializer = ConnectionFactoryInitializer()
        initializer.setConnectionFactory(connectionFactory)
        val databasePopulator = CompositeDatabasePopulator()
        databasePopulator.addPopulators(ResourceDatabasePopulator(ClassPathResource("scripts/schema.sql")))
        databasePopulator.addPopulators(ResourceDatabasePopulator(ClassPathResource("scripts/data.sql")))
        initializer.setDatabasePopulator(databasePopulator)
        return initializer
    }
}
