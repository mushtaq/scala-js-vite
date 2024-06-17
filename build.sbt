import org.openqa.selenium.chrome.ChromeOptions
import org.scalajs.jsenv.selenium.SeleniumJSEnv
import org.scalajs.linker.interface.ModuleSplitStyle

inThisBuild(
  Seq(
    version           := "0.1.0-SNAPSHOT",
    organization      := "com.github.mushtaq.scala-js-vite",
    organizationName  := "ThoughtWorks",
    scalafmtOnCompile := true,
    scalaVersion      := "3.4.2",
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-feature",
      "-unchecked",
      "-deprecation",
//      "-Wconf:any:warning-verbose",
//      "-Wdead-code",
//      "-Xlint:_,-missing-interpolator",
//      "-Xcheckinit"
    )
  )
)

lazy val `scala-js-vite` = project
  .in(file("."))
  .aggregate(example)

lazy val example = project
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withSourceMap(false)
        .withModuleSplitStyle(ModuleSplitStyle.SmallModulesFor(List("example")))
    },
    Compile / jsEnv                 := seleniumConfig(Compile, port = 5173).value,
    Test / jsEnv                    := seleniumConfig(Test, port = 5173).value,
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % "3.2.18" % Test
    )
  )

def seleniumConfig(sbtConfig: Configuration, port: Int) = Def.setting {
  val testJsDir = (sbtConfig / fastLinkJS / scalaJSLinkerOutputDirectory).value
  val webRoot   = s"http://localhost:$port/${testJsDir.relativeTo(baseDirectory.value).get}/"

  new SeleniumJSEnv(
    headlessChrome,
    SeleniumJSEnv
      .Config()
      .withMaterializeInServer(contentDir = testJsDir.getAbsolutePath, webRoot = webRoot)
  )
}

lazy val headlessChrome = {
  import _root_.io.github.bonigarcia.wdm.WebDriverManager
  WebDriverManager
    .chromedriver()
//    .browserVersion("126.0.6478.61")
    .setup()
  new ChromeOptions().setHeadless(true)
}
