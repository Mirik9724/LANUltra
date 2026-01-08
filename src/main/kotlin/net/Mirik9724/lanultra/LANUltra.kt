package net.Mirik9724.lanultra

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import net.minecraft.server.management.ServerConfigurationManager

fun log(ms: String){
    println("[LANUltra]: "+ms)
}

@Mod(modid = LANUltra.Companion.MODID, version = LANUltra.Companion.VERSION,acceptableRemoteVersions = "*")
class LANUltra {
    private var serverConfigManager: ServerConfigurationManager? = null
    @Mod.EventHandler
    fun init(event: FMLInitializationEvent?) {
        // some example code
        log("Mod ON")
    }

    companion object {
        const val MODID: String = "lanultra"
        const val VERSION: String = "0.1"
        const val acceptableRemoteVersions = "*"
    }



}

