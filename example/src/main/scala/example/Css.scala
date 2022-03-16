package example

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object Css {
  @JSImport("/css/styles.module.css", JSImport.Default)
  @js.native
  object Styles extends js.Object
}
