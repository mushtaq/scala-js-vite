import org.openqa.selenium.chrome.ChromeOptions
import org.scalajs.jsenv.selenium.SeleniumJSEnv

inThisBuild(
  Seq(
    version           := "0.1.0-SNAPSHOT",
    organization      := "com.github.mushtaq.scala-js-vite",
    organizationName  := "ThoughtWorks",
    scalafmtOnCompile := true,
    scalaVersion      := "2.13.8",
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-feature",
      "-unchecked",
      "-deprecation",
      "-Wconf:any:warning-verbose",
      "-Wdead-code",
      "-Xlint:_,-missing-interpolator",
      "-Xsource:3",
      "-Xasync",
      "-Xcheckinit"
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
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule).withSourceMap(false) },
    Compile / jsEnv                 := seleniumConfig(Compile, port = 5173).value,
    Test / jsEnv                    := seleniumConfig(Test, port = 5173).value,
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % "3.2.11" % Test
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
  WebDriverManager.chromedriver().setup()
  new ChromeOptions().setHeadless(true)
}
