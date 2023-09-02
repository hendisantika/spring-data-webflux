package com.hendisantika.dto

import com.hendisantika.model.Employee

data class OrganizationDTO(var id: Int?, var name: String) {
    var employees: MutableList<Employee> = ArrayList()

    constructor(employees: MutableList<Employee>) : this(null, "") {
        this.employees = employees
    }

    constructor(id: Int, name: String, employees: MutableList<Employee>) : this(id, name) {
        this.employees = employees
    }

}
