package com.gorillas.technicaltest.repository

import com.gorillas.technicaltest.data.Delivery
import org.springframework.stereotype.Component

@Component
class DeliveryRepository {
    fun findAll(): List<Delivery> = listOf(Delivery(1, "JungleInc"),Delivery(2, "Bardock"))

    fun findById(id: Int): Delivery? = listOf(Delivery(1, "JungleInc"))
        .find { id == it.id }
}