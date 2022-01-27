package com.gorillas.technicaltest.repository

import com.gorillas.technicaltest.data.Delivery
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Component

@Component
interface DeliveryRepository : ReactiveCrudRepository<Delivery, String> {
}