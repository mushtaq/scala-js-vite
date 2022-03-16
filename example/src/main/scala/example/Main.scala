package example

import scala.scalajs.js.Object.keys

object Main {

  def main(args: Array[String]): Unit = {
    println(Utils.camelcase("my name is blah"))
    println(keys(Css.Styles))
  }

}
