package com.target.myretail.app.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.target.myretail.app.domain.CurrentPrice
import com.target.myretail.app.domain.Product
import com.target.myretail.app.domain.ResponseVM
import com.target.myretail.app.repository.ProductRepo
import org.springframework.stereotype.Service
import javax.ws.rs.WebApplicationException


@Service
class ProductService(val productRepo: ProductRepo) {
    private val redSkyService: RedSkyService = RedSkyService( ObjectMapper())
    fun findAll(): Collection<CurrentPrice> {
        return productRepo.findAll()
    }

    fun findOne(id: String): Product {
        val name = redSkyService.getProductName(id)
        val priceInfo = productRepo.findById(id.toLong())
        if (priceInfo.isPresent) {
            return Product(id.toLong(), name, ResponseVM(priceInfo.get().value.toFloatOrNull(),priceInfo.get().currency_code))
        } else {
            throw Exception("Product Not Found")
        }
    }

    fun updateProductById(id: String, product: Product): Product {
        if (productRepo.existsById(id.toLong()) && id == product.id.toString()) {
            val name = redSkyService.getProductName(id)
            val currentPrice = productRepo.save(CurrentPrice(
                    product.id,
                    product.current_price.value.toString(),
                    product.current_price.currency_code.toString()

            ))
            return Product(id.toLong(), name, ResponseVM(value = currentPrice.value.toFloatOrNull(), currency_code = currentPrice.currency_code))
        } else {
            throw Exception("Could not update product by Id = $id. Product does not exist or Id mismatch")
        }
    }

    fun insertProductPrice(id: Long, currentPrice: CurrentPrice): Product {
        val name = redSkyService.getProductName(id.toString())
        val response = productRepo.save(CurrentPrice(
                id = id,
                value = currentPrice.value,
                currency_code = currentPrice.currency_code
        ))
        return Product(response.id, name, ResponseVM(response.value.toFloatOrNull(), response.currency_code))
    }

    fun removeProductPrice(id: String) {
        val response = productRepo.findById(id.toLong())
         if (response.isPresent) return productRepo.delete(response.get()) else throw WebApplicationException("Could not find a product by that Id")
    }
}