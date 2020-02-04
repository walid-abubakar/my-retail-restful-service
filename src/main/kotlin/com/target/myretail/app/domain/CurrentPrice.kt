package com.target.myretail.app.domain

import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class CurrentPrice (
        @Id var id: Long,
        @NotNull
        var value: String,
        @NotNull
        var currency_code: String
)