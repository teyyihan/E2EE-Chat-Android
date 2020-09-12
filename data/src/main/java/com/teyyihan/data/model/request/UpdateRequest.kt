package com.teyyihan.data.model.request

import java.security.KeyPair

data class UpdateRequest(
    val publicKey: String?,
    val fcmToken: String
)

data class UpdateRequestLocal(
    val fcmToken: String,
    val keyPair: KeyPair?
)