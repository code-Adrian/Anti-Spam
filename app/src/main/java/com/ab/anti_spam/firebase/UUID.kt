package com.ab.anti_spam.firebase

import java.util.*

fun genUID():Long{
    val uniqueId: Long = UUID.randomUUID().mostSignificantBits and (1L shl 48) - 1
    return uniqueId
}