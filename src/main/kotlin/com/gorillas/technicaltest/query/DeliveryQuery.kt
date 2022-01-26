package com.gorillas.technicaltest.query

import com.expediagroup.graphql.server.operations.Query
import com.gorillas.technicaltest.data.Delivery
import com.gorillas.technicaltest.repository.DeliveryRepository
import org.springframework.stereotype.Component

@Component
class DeliveryQuery(private val repository: DeliveryRepository) : Query {
    fun getAllDeliveries(): List<Delivery> {
        return repository.findAll();
    }

    fun findDeliveryById(id: Int): Delivery? {
        return repository.findById(id);
    }
}