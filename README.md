# LANUltra 
## Mod adds hybrid(License+Offline) auth in LAN game now

A mod for playing with a license and offline players  
Allows licensed and unlicensed players to play simultaneously  
**If you don't trust players, use something like AuthMe for Forge**  
If the host with a licensed player is a pirate  
Then he will not see the licensed players' skins(Using mods like SkinRestorer [click](https://www.curseforge.com/minecraft/mc-mods/offlineskins) )
For 1.7.10 nothing as SR
I recommend using it together with [e4mc](https://modrinth.com/mod/e4mc-retro)


Download [kotlin-stdlib:1.3.72](https://repo1.maven.org/maven2/org/jetbrains/kotlin/kotlin-stdlib/1.3.72/kotlin-stdlib-1.3.72.jar)  for correct work  
Download [ALT](https://raw.githubusercontent.com/Mirik9724/LANUltra/master/LicenseRun/mods/kotlin-stdlib-1.3.72.jar)

## DEV only
```$env:JAVA_HOME = "F:\Program\jdk\AmC-1.8"(Your path to jdk 8)```  
```./gradlew setupDecompWorkspace``` - genSources  
```./gradlew runClient``` - for local test  
