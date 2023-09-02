package com.hendisantika.employeeservice.repostory

import com.hendisantika.employeeservice.model.Employee
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Flux

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
interface EmployeeRepository : R2dbcRepository<Employee, Int> {
    fun findByOrganizationId(organizationId: Int): Flux<Employee>
}
