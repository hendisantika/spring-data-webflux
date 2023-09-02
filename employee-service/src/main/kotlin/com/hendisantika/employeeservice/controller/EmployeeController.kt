package com.hendisantika.employeeservice.controller

import com.hendisantika.employeeservice.model.Employee
import com.hendisantika.employeeservice.repostory.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-webflux
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 9/2/23
 * Time: 07:41
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/employees")
class EmployeeController {

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @GetMapping
    fun findAll(): Flux<Employee> = employeeRepository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): Mono<Employee> = employeeRepository.findById(id)

    @GetMapping("/organization/{organizationId}")
    fun findByOrganizationId(@PathVariable organizationId: Int): Flux<Employee> = employeeRepository.findByOrganizationId(organizationId)

    @PostMapping
    fun add(@RequestBody employee: Employee): Mono<Employee> = employeeRepository.save(employee)
}
