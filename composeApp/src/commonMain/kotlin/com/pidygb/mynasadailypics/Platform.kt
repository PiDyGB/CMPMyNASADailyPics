package com.pidygb.mynasadailypics

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform