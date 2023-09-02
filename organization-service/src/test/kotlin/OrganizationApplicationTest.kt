import com.hendisantika.OrganizationApplication
import org.springframework.boot.fromApplication
import org.springframework.boot.with

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-webflux
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 9/2/23
 * Time: 08:06
 * To change this template use File | Settings | File Templates.
 */
class OrganizationApplicationTest

fun main(args: Array<String>) {
    fromApplication<OrganizationApplication>().with(PostgresContainerDevMode::class).run(*args)
}
