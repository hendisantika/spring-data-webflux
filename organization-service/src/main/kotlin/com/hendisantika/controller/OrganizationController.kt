package com.hendisantika.controller

import com.hendisantika.dto.OrganizationDTO
import com.hendisantika.model.Employee
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

    @GetMapping("/{id}/with-employees")
    fun findByIdWithEmployees(@PathVariable id: Int): Mono<OrganizationDTO> {
        val employees: Flux<Employee> = client.get().uri("/employees/organization/$id")
                .retrieve().bodyToFlux(Employee::class.java)
        val org: Mono<Organization> = repository.findById(id)
        return org.zipWith(employees.collectList()).log()
                .map { tuple -> OrganizationDTO(tuple.t1.id as Int, tuple.t1.name, tuple.t2) }
    }

}
