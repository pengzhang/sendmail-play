import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "sendmail"
  val appVersion      = "1.0"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "org.apache.commons" % "commons-email" % "1.3.1",
    "mysql" % "mysql-connector-java" % "5.1.26"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
