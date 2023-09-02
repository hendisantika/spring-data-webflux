package com.hendisantika.employeeservice.controller

import com.hendisantika.employeeservice.model.Employee
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-webflux
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 9/2/23
 * Time: 07:46
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class EmployeeControllerTests {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    companion object {

        @Container
        @ServiceConnection
        val container = PostgreSQLContainer<Nothing>("postgres:15-alpine").apply {
            withDatabaseName("spring")
            withUsername("hendi")
            withPassword("hendi34")
        }
    }

    @Test
    @Order(1)
    fun shouldStart() {
    }

    @Test
    @Order(2)
    fun shouldAddEmployee() {
        webTestClient.post().uri("/employees").contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Employee("Test", 1000, 1))
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("$.id").isNotEmpty
    }

    @Test
    @Order(3)
    fun shouldFindEmployee() {
        webTestClient.get().uri("/employees/1").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("$.id").isNotEmpty
    }
}
