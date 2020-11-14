package com.teyyihan.data

object ApiConsts {

    private const val LOCAL_IP = "192.168.1.5"

    const val SPRING_BASE_RUL = "http://${LOCAL_IP}:8080/"
    const val KEYCLOAK_BASE_RUL = "http://${LOCAL_IP}:8081/"

    const val KEYCLOAK_REALM = "E2EE"
    const val CLIENT_ID = "frontend"
    const val CLIENT_SECRET = "23998e3f-c57b-4590-afe8-69533c2126f8"

}