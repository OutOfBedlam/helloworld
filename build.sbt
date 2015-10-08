
name := "helloworld"

organization := "com.example"

version := "2.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

mainClass in assembly := Some("com.example.Hello")

jarName in assembly := "hello-world.jar"

docker <<= (docker dependsOn assembly)

dockerfile in docker := {
  val artifact = (outputPath in assembly).value
  val artifactTargetPath = s"/app/${artifact.name}"
  val configFile = baseDirectory.value / "src" / "main" / "resources" / "docker.conf"
  val configFileTargetPath = s"/app/application.conf"
  val logbackFile = baseDirectory.value / "src" / "main" / "resources" / "logback.xml"
  val logbackFileTargetPath = "/app/logback.xml"
  new Dockerfile {
    from("java:8")
    add(artifact, artifactTargetPath)
    add(configFile, configFileTargetPath)
    add(logbackFile, logbackFileTargetPath)
    env("MONGO_DB", "test")
    env("MONGO_HOST", "localhost")
    env("MONGO_PORT", "27017")
    entryPoint("java",
      s"-Dconfig.file=$configFileTargetPath",
      s"-Dlogback.configurationFile=$logbackFileTargetPath",
      "-jar", artifactTargetPath)
  }
}

imageNames in docker := Seq(
  ImageName(s"dnvriend/${name.value}:latest"),
  ImageName (s"dnvriend/${name.value}:v${version.value}")
)

lazy val helloWorldApp = (project in file(".")).
  enablePlugins(DockerPlugin)