package com.gorillas.technicaltest.query

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
@AutoConfigureWebTestClient
class DeliveryQueryTest(@Autowired private val testClient: WebTestClient) {

    val GRAPHQL_ENDPOINT = "/graphql"
    val GRAPHQL_MEDIA_TYPE = MediaType("application", "graphql")
    val DATA_JSON_PATH = "data"

    @Test
    fun `verify expected deliveries query`() {
        val query = "getAllDeliveries"
        val received = "false"

        testClient.post()
            .uri(GRAPHQL_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(GRAPHQL_MEDIA_TYPE)
            .bodyValue("query { $query(received: $received){deliveryId} }")
            .exchange()
            .expectStatus().isOk()
            .expectBody().jsonPath("$DATA_JSON_PATH.$query")
            .isEmpty
    }

    @Test
    fun `verify received deliveries query`() {
        val query = "getAllDeliveries"
        val received = "true"

        testClient.post()
            .uri(GRAPHQL_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(GRAPHQL_MEDIA_TYPE)
            .bodyValue("query { $query(received: $received){deliveryId} }")
            .exchange().expectBody()
            .jsonPath("$DATA_JSON_PATH.$query.length()").isEqualTo(8)
    }
}