package id.neotica.neotoast

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform