package com.hendisantika.mapper

import com.hendisantika.dto.OrganizationDTO

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-webflux
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 9/2/23
 * Time: 08:00
 * To change this template use File | Settings | File Templates.
 */
class EmployeeMapper {
    fun map(organizations: MutableList<OrganizationDTO>): OrganizationDTO {
        val org = OrganizationDTO(null, "")
        for (o in organizations) {
            if (o.id != null) org.id = o.id
            if (o.name != null) org.name = o.name
            if (o.employees != null) org.employees = o.employees
        }
        return org
    }
}
