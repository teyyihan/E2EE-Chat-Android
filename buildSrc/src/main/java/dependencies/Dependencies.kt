package dependencies

object Dependencies {
    const val kotlin_standard_lib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
    const val android_core_ktx = "androidx.core:core-ktx:${Versions.ktx_version}"
    const val app_compat = "androidx.appcompat:appcompat:${Versions.app_compat_version}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt_version}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    const val moshi_retrofit = "com.squareup.retrofit2:converter-moshi:${Versions.moshi_version}"
    const val hilt_lifecycle_viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha01"
    const val hilt_compiler = "androidx.hilt:hilt-compiler:1.0.0-alpha01"
    const val material_design = "com.google.android.material:material:${Versions.material_desing_version}"
}