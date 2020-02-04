package com.target.myretail.app.repository

import com.target.myretail.app.domain.CurrentPrice
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepo: MongoRepository<CurrentPrice, Long>