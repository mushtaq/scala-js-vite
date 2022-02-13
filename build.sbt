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
    Test / jsEnv                    := {
      new SeleniumJSEnv(
        new ChromeOptions().setHeadless(true),
        seleniumConfig(
          port = 3000,
          baseDir = baseDirectory.value,
          testJsDir = (Test / fastLinkJS / scalaJSLinkerOutputDirectory).value
        )
      )
    },
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % "3.2.11" % Test
    )
  )

def seleniumConfig(port: Int, baseDir: File, testJsDir: File): SeleniumJSEnv.Config = {
  import _root_.io.github.bonigarcia.wdm.WebDriverManager
  WebDriverManager.chromedriver().setup()
  SeleniumJSEnv
    .Config()
    .withMaterializeInServer(
      testJsDir.getAbsolutePath,
      s"http://localhost:$port/${testJsDir.relativeTo(baseDir).get}/"
    )
}
