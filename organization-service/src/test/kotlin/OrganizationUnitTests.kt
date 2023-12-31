import com.hendisantika.dto.OrganizationDTO
import com.hendisantika.model.Employee
import com.hendisantika.model.Organization
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-webflux
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 9/2/23
 * Time: 08:07
 * To change this template use File | Settings | File Templates.
 */
class OrganizationUnitTests {
    @Test
    fun test() {
//        val ret : Mono<Flux<OrganizationDTO>> = getOrganizationByName("X")
//                .map { org -> getEmployees(org.id!!).map { emp -> OrganizationDTO(org.id as Int, org.name, emp) } }

        val ret: Mono<OrganizationDTO> = getOrganizationByName("X").zipWith(getEmployeesByOrganizationId(1).collectList()).map { tuple ->
            OrganizationDTO(tuple.t1.id as Int, tuple.t1.name, tuple.t2)
        }
        println("Mono: ${ret.block()!!.employees}")

    }

    fun getEmployeesByOrganizationId(organizationId: Int): Flux<Employee> {
        return Flux.fromIterable(mutableListOf(Employee(1, "X", 1, 1), Employee(2, "Y", 2, 2)))
    }

    fun getOrganizationByName(name: String): Mono<Organization> {
        val org = Organization(name)
        org.id = 1
        return Mono.just(org)
    }
}
