package io.i101.microservice.ddd

import io.restassured.RestAssured
import io.restassured.RestAssured.get
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus.OK
import org.springframework.test.context.junit4.SpringRunner

//@ActiveProfiles("dev")
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class MainApplicationTests {
	@LocalServerPort
	internal var port: Int = 0

	@Before
	fun setup() {
		RestAssured.port = port
	}

	@Test
	fun GET_ping() {
		get("/ping")
				.then()
				.statusCode(OK.value())
				.body(equalTo("PONG"))
	}

}