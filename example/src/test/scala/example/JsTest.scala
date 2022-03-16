package example

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import scala.scalajs.js.Object.keys

class JsTest extends AnyFreeSpec with Matchers {

  "camelcase" in {
    Utils.camelcase("my name is blah") shouldBe "myNameIsBlah"
  }

  "css" in {
    keys(Css.Styles).toList.head shouldBe "red"
  }

}
