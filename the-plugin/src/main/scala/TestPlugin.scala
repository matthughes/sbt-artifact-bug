
import sbt._
import Keys._

object ThePlugin extends AutoPlugin {

  override def requires = plugins.IvyPlugin
  override def trigger = allRequirements

  object autoImport {
    lazy val thePluginConfig = config("the-plugin-config")
    lazy val aTask = taskKey[Option[File]]("creates a file")
  }

  import autoImport._

  override lazy val projectSettings: Seq[Setting[_]] = 
    Seq(
      aTask := {
        (test in Test).value
        None
      }
    ) ++ 
    inConfig(thePluginConfig)(Classpaths.ivyBaseSettings ++ Classpaths.ivyPublishSettings) ++ Seq(
      artifacts in thePluginConfig := {
        Seq.empty
      },
      packagedArtifacts in thePluginConfig := {
        val taskResults = aTask.value
        Map.empty
      },
      publish <<= publish.dependsOn(publish in thePluginConfig),
      publishLocal <<= publishLocal.dependsOn(publishLocal in thePluginConfig)
    )
}
