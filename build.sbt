import spray.revolver.RevolverPlugin._

name := "helloworld"

organization := "com.example"

version := "4.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies ++= {
  val akkaVersion    = "2.3.12"
  val streamsVersion = "1.0"
  val logbackVersion = "1.1.3"
  Seq(
    "com.typesafe.akka"  %%  "akka-actor"                       % akkaVersion,
    "com.typesafe.akka"  %%  "akka-stream-experimental"         % streamsVersion,
    "com.typesafe.akka"   %% "akka-slf4j"                       % akkaVersion,
    "ch.qos.logback"       % "logback-classic"                  % logbackVersion,
    "ch.qos.logback"       % "logback-access"                   % logbackVersion,
    "net.logstash.logback" % "logstash-logback-encoder"         % "4.5.1",
    "org.scalatest" %% "scalatest" % "2.2.4" % "test"
  )
}

mainClass in Compile := Some("com.example.Hello")

// enable native packager: http://www.scala-sbt.org/sbt-native-packager/archetypes/
// read: http://www.scala-sbt.org/sbt-native-packager/archetypes/java_app/index.html
//enablePlugins(JavaAppPackaging)
// read: http://www.scala-sbt.org/sbt-native-packager/archetypes/java_server/index.html
enablePlugins(JavaServerAppPackaging) //

// enable the universal plugin: http://www.scala-sbt.org/sbt-native-packager/formats/universal.html
enablePlugins(UniversalPlugin)

// http://hirt.se/blog/?p=289
// jmx over rmi. It requires an RMI registry to be running, from which a stub for communicating
// with the RMI server can be looked up by the client. The port for the RMI server, which is returned
// in the stub retrieved from the registry, is however anonymous by default.
// This makes tunneling traffic pretty cumbersome. (like in a docker container)
//
// JDK 7u4 added the ability to specify the port of the RMI server by setting the 'com.sun.management.jmxremote.rmi.port'
// variable. By setting them both to the same value, it becomes much easier communicating with JMX behind a firewall.
//
// see: http://docs.oracle.com/javase/7/docs/technotes/guides/rmi/javarmiproperties.html
// see: http://docs.oracle.com/javase/7/docs/technotes/guides/management/agent.html
// see: http://www.oracle.com/technetwork/java/javase/compatibility-417013.html
javaOptions in Universal += Seq(
  "-Djava.rmi.server.hostname=127.0.0.1",
  "-Dcom.sun.management.jmxremote.port=9186", // port of the rmi registery
  "-Dcom.sun.management.jmxremote.rmi.port=9186", // port of the rmi server
  "-Dcom.sun.management.jmxremote.ssl=false", // To disable SSL
  "-Dcom.sun.management.jmxremote.local.only=false", // when true, it indicates that the local JMX RMI connector will only accept connection requests from local interfaces
  "-Dcom.sun.management.jmxremote.authenticate=false" // Password authentication for remote monitoring is disabled
).mkString(" ")

packageName in Universal := "helloworld"

name in Universal := "helloworld"

// enable dependencyGraph
net.virtualvoid.sbt.graph.Plugin.graphSettings

// enable Revolver
Revolver.settings

mainClass in Revolver.reStart := Some("com.example.Hello")

Revolver.enableDebugging(port = 5050, suspend = false)

