addSbtPlugin("com.timushev.sbt" % "sbt-updates"  % "0.6.4")
addSbtPlugin("org.scalameta"    % "sbt-scalafmt" % "2.5.0")
addSbtPlugin("com.timushev.sbt" % "sbt-rewarn"   % "0.1.3")

libraryDependencies += "io.github.bonigarcia" % "webdrivermanager"     % "5.3.1"
libraryDependencies += "org.scala-js"        %% "scalajs-env-selenium" % "1.1.1"
// note, 'sbt-scalajs' must come after 'scalajs-env-selenium'
// reference: https://github.com/scala-js/scala-js-env-selenium#usage
addSbtPlugin("org.scala-js"                   % "sbt-scalajs"          % "1.12.0")

scalacOptions ++= Seq(
  "-encoding",
  "UTF-8",
  "-feature",
  "-unchecked",
  "-deprecation",
  "-Xlint:-unused,_",
  "-Ywarn-dead-code",
  "-Xfuture"
)
