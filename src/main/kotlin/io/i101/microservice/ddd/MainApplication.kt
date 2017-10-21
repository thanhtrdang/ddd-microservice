package io.i101.microservice.ddd

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@ComponentScan("io.i101.microservice.ddd", lazyInit = true)
@EnableJpaRepositories
@EnableTransactionManagement
class MainApplication
fun main(args: Array<String>) {
    SpringContainer.context = SpringApplication.run(MainApplication::class.java, *args)
}

/**
 * Kotlin 1.2: Replace this object by <code>private lateinit var springContext: ApplicationContext</code>
 */
private object SpringContainer {
    lateinit var context: ApplicationContext
}
/**
 * @param clazz T::class.java
 */
fun <T> springBean(clazz: Class<T>): T = SpringContainer.context.getBean(clazz)