
import sbt._
import Keys._

object ThePlugin extends AutoPlugin {

  override def requires = plugins.IvyPlugin
  override def trigger = allRequirements

  object autoImport {
    lazy val thePluginConfig = config("the-plugin-config")
  }

  import autoImport._

  override lazy val projectSettings: Seq[Setting[_]] = 
    inConfig(thePluginConfig)(Classpaths.ivyBaseSettings ++ Classpaths.ivyPublishSettings) ++ Seq(
      artifacts in thePluginConfig := {
        Seq.empty
      },
      packagedArtifacts in thePluginConfig := {
        Map.empty
      },
      publish <<= publish.dependsOn(publish in thePluginConfig),
      publishLocal <<= publishLocal.dependsOn(publishLocal in thePluginConfig)
    )
}
