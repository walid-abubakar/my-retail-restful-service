package com.target.myretail.app.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.client.RestTemplate

class RedSkyService(private val mapper: ObjectMapper) {
    private val baseUrl = "https://redsky.target.com/v2/pdp/tcin/"
    private val exclude = "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics"

    fun getProductName(id: String) : String {
        val restTemplate = RestTemplate()
        val response = restTemplate.getForEntity("$baseUrl/$id$exclude", String::class.java)
        val root = mapper.readTree(response.body)
        val name = root.at("/product/item/product_description/title").asText()
        return name
    }
}