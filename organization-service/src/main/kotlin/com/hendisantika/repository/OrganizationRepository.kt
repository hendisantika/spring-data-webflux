package com.hendisantika.repository

import com.hendisantika.model.Organization
import org.springframework.data.r2dbc.repository.R2dbcRepository

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
interface OrganizationRepository : R2dbcRepository<Organization, Int>
