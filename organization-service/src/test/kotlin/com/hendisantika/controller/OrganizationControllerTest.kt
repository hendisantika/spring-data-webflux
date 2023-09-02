package com.hendisantika.controller

import com.hendisantika.model.Organization
import org.junit.jupiter.api.*
import org.mockserver.integration.ClientAndServer
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

}
