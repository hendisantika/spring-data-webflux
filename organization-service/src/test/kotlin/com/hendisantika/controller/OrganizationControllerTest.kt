package com.hendisantika.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.hendisantika.model.Employee
import com.hendisantika.model.Organization
import org.junit.jupiter.api.*
import org.mockserver.integration.ClientAndServer
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
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
 * Time: 08:09
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class OrganizationControllerTests {

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

        private var mockServer: ClientAndServer? = null

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            mockServer = ClientAndServer.startClientAndServer(8090)
        }

        @AfterAll
        @JvmStatic
        internal fun afterAll() {
            mockServer!!.stop()
        }
    }

    @Test
    @Order(1)
    fun shouldStart() {
    }

    @Test
    @Order(2)
    fun shouldAddOrganization() {
        webTestClient.post().uri("/organizations").contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Organization("Test"))
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("$.id").isNotEmpty
    }

    @Test
    @Order(3)
    fun shouldFindOrganization() {
        webTestClient.get().uri("/organizations/1").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("$.id").isNotEmpty
    }

    @Test
    @Order(3)
    fun shouldFindOrganizations() {
        webTestClient.get().uri("/organizations").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody().jsonPath("$.length()").isEqualTo(1)
                .jsonPath("$[0].id").isNotEmpty
    }

    @Test
    @Order(3)
    fun shouldFindOrganizationWithEmployees() {
        mockServer!!.`when`(request().withMethod("GET").withPath("/employees/organization/1"))
                .respond(response()
                        .withStatusCode(200)
                        .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON)
                        .withBody(createEmployees()))

        webTestClient.get().uri("/organizations/1/with-employees").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("$.id").isNotEmpty
                .jsonPath("$.employees.length()").isEqualTo(2)
                .jsonPath("$.employees[0].id").isEqualTo(1)
                .jsonPath("$.employees[1].id").isEqualTo(2)
    }

    private fun createEmployees(): String {
        val employees: List<Employee> = listOf<Employee>(
                Employee(1, "Test1", 10000, 1),
                Employee(2, "Test2", 20000, 1)
        )
        return jacksonObjectMapper().writeValueAsString(employees)
    }
}
