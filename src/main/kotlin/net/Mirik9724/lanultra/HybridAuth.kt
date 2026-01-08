package net.Mirik9724.lanultra.auth

import com.google.gson.JsonParser
import com.mojang.authlib.GameProfile
import net.minecraft.server.MinecraftServer
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.*

object HybridAuth {
    private val premiumCache = mutableMapOf<String, GameProfile>()

    fun authenticatePlayer(server: MinecraftServer, profile: GameProfile): GameProfile {
        val name = profile.name.toLowerCase()

        // Проверяем кэш
        premiumCache[name]?.let { return it }

        try {
            // Пробуем Mojang API
            val realProfile = verifyMojang(name)
            if (realProfile != null) {
                premiumCache[name] = realProfile
                log("License: $name (UUID: ${realProfile.id})")
                return realProfile
            }
        } catch (e: Exception) {
            log("NotLicense $name: ${e.message}")
        }

        // Fallback оффлайн
        val offlineUuid = UUID.nameUUIDFromBytes(("OfflinePlayer:$name").toByteArray(StandardCharsets.UTF_8))
        val offlineProfile = GameProfile(offlineUuid, name)
        log("OFFLINE: $name")
        return offlineProfile
    }

    private fun verifyMojang(username: String): GameProfile? {
        val url = URL("https://api.mojang.com/users/profiles/minecraft/$username")
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.connectTimeout = 5000
        conn.readTimeout = 5000

        if (conn.responseCode != 200) return null

        val response = conn.inputStream.bufferedReader().readText()
        val parser = JsonParser()
        val json = parser.parse(response).asJsonObject

        val uuidRaw = json["id"].asString
        val uuid = UUID.fromString(
            uuidRaw.replace(
                Regex("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})"),
                "$1-$2-$3-$4-$5"
            )
        )

        return GameProfile(uuid, username)
    }

    fun clearCache() {
        premiumCache.clear()
        log("Cache cleared")
    }

    private fun log(msg: String) {
        println("[LANUltra-Auth]: $msg")
    }
}