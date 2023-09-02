package com.hendisantika.employeeservice

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.PostgreSQLContainer

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-webflux
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 9/2/23
 * Time: 07:45
 * To change this template use File | Settings | File Templates.
 */
@TestConfiguration
class PostgresContainerDevMode {

    @Bean
    @ServiceConnection
    fun postgresql(): PostgreSQLContainer<*>? {
        return PostgreSQLContainer("postgres:15-alpine")
                .withUsername("hendi")
                .withPassword("hendi34")
    }
}
