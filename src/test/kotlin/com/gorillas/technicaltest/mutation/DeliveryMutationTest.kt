package com.gorillas.technicaltest.mutation

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@SpringBootTest
@AutoConfigureWebTestClient
class DeliveryMutationTest(@Autowired private val testClient: WebTestClient) {

    val GRAPHQL_ENDPOINT = "/graphql"
    val GRAPHQL_MEDIA_TYPE = MediaType("application", "graphql")
    val DATA_JSON_PATH = "data"

    @Test
    fun `verify mark delivery as received mutation`() {
        val mutation = "markDeliveryAsReceived"
        val id = "101"
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")

        testClient.post()
            .uri(GRAPHQL_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(GRAPHQL_MEDIA_TYPE)
            .bodyValue("mutation { $mutation(id: \"$id\"){deliveryId, expectedDate}}")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$DATA_JSON_PATH.$mutation.deliveryId")
            .isEqualTo("101")
            .jsonPath("$DATA_JSON_PATH.$mutation.expectedDate").value<String> {
                v-> assert(ZonedDateTime.parse(v, formatter).isBefore(ZonedDateTime.now()))
            }
    }

}