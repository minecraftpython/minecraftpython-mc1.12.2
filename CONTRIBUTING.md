#Contributing to minecraftpython
Any and all PRs are of course welcome!  You can find a copy of this software's license included with it.

##How to Build
Forge mods are typically built using the Gradle build system.
Execute `./gradlew build` at the repository root to trigger a build; the artifact will appear in `build/libs`.

##How to Run
The mod can be installed and run like any other Forge mod once the .jar is built.

`./gradlew runClient` can be used for *some* testing, but when in doubt, build the mod - the mod's Python interoperability does not function in this configuration.