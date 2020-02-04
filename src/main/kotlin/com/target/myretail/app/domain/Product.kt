package com.target.myretail.app.domain

import org.springframework.data.annotation.Id
import java.io.Serializable

data class Product(
        @Id var id: Long,
        var name: String,
        var current_price: ResponseVM
) : Serializable