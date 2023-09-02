package com.hendisantika.employeeservice.model

import org.springframework.data.annotation.Id

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-webflux
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 9/2/23
 * Time: 07:40
 * To change this template use File | Settings | File Templates.
 */
class Employee(val name: String, val salary: Int, val organizationId: Int) {

    @Id
    var id: Int? = null
}
