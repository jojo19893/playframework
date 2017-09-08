/*
 * Copyright (C) 2009-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package play.it.test

import play.api.libs.ws.WSResponse
import play.api.mvc._
import play.api.test.PlaySpecification

/**
 * Tests that [[OkHttpEndpointSupport]] works properly.
 */
class WSEndpointSpec extends PlaySpecification with AllEndpointsIntegrationSpecification with WSEndpointSupport {

  "WSEndpoint" should {
    "make a request and get a response" in {
      withResult(Results.Ok("Hello")) withAllWSEndpoints { endpointClient: WSEndpoint =>
        val response: WSResponse = endpointClient.makeRequest("/")
        response.body must_== "Hello"
      }
    }
    "support a WSTestClient-style API" in {
      withResult(Results.Ok("Hello")) withAllWSEndpoints { implicit endpointClient: WSEndpoint =>
        val response: WSResponse = await(wsUrl("/").get()) // Test for deprecated
        response.body must_== "Hello"
      }
    }
  }
}