package com.target.myretail.app.service

import com.target.myretail.app.domain.CurrentPrice
import com.target.myretail.app.domain.Product
import com.target.myretail.app.domain.ResponseVM
import com.target.myretail.app.repository.ProductRepo
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ProductServiceTest {

    private val productRepo = mockk<ProductRepo>()

    private val productService = mockk<ProductService>()

    @BeforeEach
    fun setUp() {
        every{productRepo.findById(1L).get()} returns CurrentPrice(1L, "2.99", "USD")
        every{productService.findOne("75574683")} returns Product(
                75574683L,
                "Sony Over-Ear Wireless Noise-Cancelling Headphones (WH1000XM3/B)",
                ResponseVM(299.99F, "USD")
        )
    }

    @Test
    fun findOneRepo() {
        val product = productRepo.findById(1)
        assertEquals(product.get(), productRepo.findById(1).get())
    }

    @Test
    fun findOneFromService() {
        val product = productService.findOne("75574683")
        assertEquals(product, productService.findOne("75574683"))
    }
}