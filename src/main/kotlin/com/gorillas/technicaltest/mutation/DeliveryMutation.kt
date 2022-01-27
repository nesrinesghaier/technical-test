package com.gorillas.technicaltest.mutation

import com.expediagroup.graphql.server.operations.Mutation
import com.gorillas.technicaltest.data.Delivery
import com.gorillas.technicaltest.repository.DeliveryRepository
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Component
class DeliveryMutation(private val deliveryRepository: DeliveryRepository) : Mutation {

    suspend fun markDeliveryAsReceived(id: String): Delivery {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
        val existingDelivery = deliveryRepository.findById(id).awaitFirst()
        existingDelivery.expectedDate = formatter.format(ZonedDateTime.now()).toString()
        return deliveryRepository.save(existingDelivery).awaitFirst()
    }
}