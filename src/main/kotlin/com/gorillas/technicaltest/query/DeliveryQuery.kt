package com.gorillas.technicaltest.query

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Query
import com.gorillas.technicaltest.entity.Delivery
import com.gorillas.technicaltest.repository.DeliveryRepository
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Component
class DeliveryQuery(private val deliveryRepository: DeliveryRepository) : Query {

    @GraphQLDescription("List a deliveries that are received or not yet received")
    suspend fun listAllDeliveries(received: Boolean): MutableList<Delivery> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")

        return deliveryRepository.findAll().filter { d ->
            if (received) LocalDateTime.parse(d.expectedDate, formatter).isBefore(LocalDateTime.now())
            else LocalDateTime.parse(d.expectedDate, formatter).isAfter(LocalDateTime.now())
        }.collectList().awaitFirst()
    }

}