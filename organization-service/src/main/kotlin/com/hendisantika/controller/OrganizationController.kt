package com.hendisantika.controller

import com.hendisantika.model.Organization
import com.hendisantika.repository.OrganizationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-webflux
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 9/2/23
 * Time: 08:01
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/organizations")
class OrganizationController {

    @Autowired
    lateinit var repository: OrganizationRepository

    @Autowired
    lateinit var client: WebClient

    @GetMapping
    fun findAll(): Flux<Organization> = repository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): Mono<Organization> = repository.findById(id)
}
