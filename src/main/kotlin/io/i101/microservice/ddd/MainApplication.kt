package io.i101.microservice.ddd

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
//@ComponentScan("io.i101.microservice.ddd", lazyInit = true)
@EnableJpaRepositories
@EnableTransactionManagement
class MainApplication(context: ApplicationContext) {
    init { _springContext = context }
}
fun main(args: Array<String>) {
    SpringApplication.run(MainApplication::class.java, *args)
}

/**
 * Kotlin 1.2: Replace this object by <code>private lateinit var _springContext: ApplicationContext</code>
 */
private var _springContext: ApplicationContext?? = null
/**
 * @param clazz T::class.java
 */
fun <T> springBean(clazz: Class<T>): T = _springContext!!.getBean(clazz)