package `fun`.adaptive.example.mpw

import `fun`.adaptive.app.JvmServerApplication.Companion.jvmServer
import `fun`.adaptive.app.app.AppMainModuleServer
import `fun`.adaptive.auth.app.AuthBasicServerModule
import `fun`.adaptive.backend.setting.dsl.inline
import `fun`.adaptive.backend.setting.dsl.settings
import `fun`.adaptive.ktor.KtorJvmServerModule
import `fun`.adaptive.lib.util.app.UtilModule
import `fun`.adaptive.persistence.ensure
import `fun`.adaptive.value.app.ValueServerModule
import `fun`.adaptive.value.persistence.FilePersistence
import kotlinx.io.files.Path

fun main() {

    settings {
        inline(
            "KTOR_PORT" to 8080,
            "KTOR_WIREFORMAT" to "proto"
        )
    }

    jvmServer {
        module { UtilModule() }
        module { ValueServerModule(FilePersistence(Path("./var/values").ensure(), 2)) }
        module { AuthBasicServerModule() }
        module { KtorJvmServerModule() }
        module { AppMainModuleServer() }
    }
}