package com.target.myretail.app.controller

import com.target.myretail.app.domain.CurrentPrice
import com.target.myretail.app.domain.Product
import com.target.myretail.app.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/products")
class ProductResource(private val productService: ProductService) {
    @GetMapping()
    fun getAllProducts(): ResponseEntity<Collection<CurrentPrice>> {
        return ok(productService.findAll())
    }

    @GetMapping("/{Id}")
    fun getProduct(@PathVariable("Id") Id: String): Product {
        val product = productService.findOne(Id)
        return product
    }

    @PutMapping("/{Id}")
    fun updateProductPrice(@PathVariable("Id") Id: String, @Valid @RequestBody product: Product): Product {
        try {
            return productService.updateProductById(Id, product)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    @PostMapping
    fun postProductPrice(@RequestBody @Valid currentPrice: CurrentPrice): ResponseEntity<HttpStatus> {
            val result = productService.insertProductPrice(currentPrice.id.toLong(), currentPrice)
            val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
                    .buildAndExpand(result.id)
                    .toUri()
            return ResponseEntity.created(uri).build()
    }

    @DeleteMapping("/{Id}")
    fun remove(@PathVariable("Id") Id: String) {
        return productService.removeProductPrice(Id)
    }
}