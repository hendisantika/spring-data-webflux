package com.hendisantika.employeeservice

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.with

@SpringBootTest
class EmployeeServiceApplicationTests

fun main(args: Array<String>) {
    fromApplication<EmployeeServiceApplicationTests>().with(PostgresContainerDevMode::class).run(*args)
}
