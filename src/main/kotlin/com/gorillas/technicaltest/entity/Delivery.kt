package com.gorillas.technicaltest.entity

import com.expediagroup.graphql.generator.annotations.GraphQLName
import org.springframework.data.annotation.Id

data class Delivery(
    @GraphQLName("deliveryId")
    @Id
    val id: String,
    val product: String,
    val supplier: String,
    val quantity: Int,
    var expectedDate: String,
    val expectedWarehouse: String,
)
