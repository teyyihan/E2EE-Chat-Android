package dependencies

object Dependencies {
    const val kotlin_standard_lib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
    const val android_core_ktx = "androidx.core:core-ktx:${Versions.ktx_version}"
    const val app_compat = "androidx.appcompat:appcompat:${Versions.app_compat_version}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt_version}"

}