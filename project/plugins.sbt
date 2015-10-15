resolvers += Resolver.url("bintray-sbt-plugin-releases", url("http://dl.bintray.com/content/sbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

// to bundle up Scala software built with SBT for native packaging systems
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.0.3")

// to show a dependency graph
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.5")

// to automatically rebuild and relaunch an application in SBT when the source code changes
addSbtPlugin("io.spray" % "sbt-revolver" % "0.7.1")