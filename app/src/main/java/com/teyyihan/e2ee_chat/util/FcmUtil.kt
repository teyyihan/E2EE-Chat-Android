package com.teyyihan.e2ee_chat.util


import com.google.android.gms.tasks.Task
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


suspend fun <T> Task<T>.awaitTask(): T? = suspendCoroutine { continuation ->
    this.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            continuation.resume(task.result)
        } else {
            continuation.resumeWithException(task.exception!!)
        }
    }
}