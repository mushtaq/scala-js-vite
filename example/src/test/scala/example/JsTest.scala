package example

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class JsTest extends AnyFreeSpec with Matchers {

  "camelcase" in {
    Utils.camelcase("my name is blah") shouldBe "myNameIsBlah"
  }

}
