package io.i101.microservice.ddd

import io.i101.microservice.ddd.interfaces.Endpoint.namespace
import io.i101.microservice.ddd.interfaces.Endpoint.ping_find
import io.i101.microservice.ddd.interfaces.Endpoint.ping_ping
import io.i101.microservice.ddd.interfaces.Endpoint.ping_store
import io.restassured.RestAssured
import io.restassured.RestAssured.get
import io.restassured.RestAssured.post
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus.OK
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.test.context.junit4.SpringRunner

//@ActiveProfiles("dev")
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class MainApplicationIntegrationTests {
	@LocalServerPort
	internal var port: Int = 0

	@Before
	fun setup() {
		RestAssured.port = port
		RestAssured.basePath = namespace
	}

	@Test
	fun GET_ping_ping() {
		get(ping_ping)
				.then()
				.statusCode(OK.value())
				.body(equalTo("PONG"))
	}

	@Test
	fun GET_ping_find() {
		get(ping_find, "0001508662774334")
				.then()
				.statusCode(NO_CONTENT.value())
//				.body(equalTo("{\"id\":\"0001508662774334\",\"name\":\"0001508662774333\",\"age\":98,\"email\":{\"value\":\"thanhtrdang98@gmail.com\",\"domain\":\"gmail.com\",\"localPart\":\"thanhtrdang98\"}}"))
	}

	@Test
	fun POST_ping_store() {
		post(ping_store)
				.then()
				.statusCode(OK.value())
//				.body(equalTo(""))
	}
}