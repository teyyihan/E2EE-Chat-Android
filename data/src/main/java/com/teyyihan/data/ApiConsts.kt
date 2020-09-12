package com.teyyihan.data

object ApiConsts {

    private const val LOCAL_IP = "192.168.1.7"

    const val SPRING_BASE_RUL = "http://${LOCAL_IP}:8080/"
    const val KEYCLOAK_BASE_RUL = "http://${LOCAL_IP}:8081/"

    const val KEYCLOAK_REALM = "E2EE"
    const val CLIENT_ID = "frontend"
    const val CLIENT_SECRET = "33113f50-1d32-4a01-a36c-080a2656ae00"

}