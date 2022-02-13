package example

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object Utils {
  @JSImport("camelcase", JSImport.Default)
  @js.native
  def camelcase(input: String): String = js.native
}
