package com.example

import org.scalatest.{FlatSpec, Matchers}

class HelloTest extends FlatSpec with Matchers {
  "Hello" should "say hello world!" in {
    Hello.Msg shouldBe "Hello, world!"
  }
}
