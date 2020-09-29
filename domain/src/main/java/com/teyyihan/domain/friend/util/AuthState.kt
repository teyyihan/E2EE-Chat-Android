package com.teyyihan.domain.friend.util


sealed class AuthState<out T> {

    data class Success<out T>(
        val value: Event<T>
    ) : AuthState<T>()

    data class Error(
        val errorModel: Event<AuthErrorModel>
    ) : AuthState<Nothing>()

    object Loading : AuthState<Nothing>()

    object NothingRN :  AuthState<Nothing>()


}

















//TODO: This works
//    data class Success<out T>(
//        val value: T
//    ): AuthState<T>()
//
//    data class Error(
//        val errorMessage: String,
//        val exception: Exception?,
//        val where: AuthStep
//    ): AuthState<Nothing>()
//
//    object Loading: AuthState<Nothing>()

//TODO: This works
//    val liveTest = liveData {
//        emit(Event(AuthState.Success(2)))
//    }

//TODO: This works
//        viewModel.liveTest.observe(viewLifecycleOwner){
//            Log.d(TAG, "onCreateView: test ${it.getContentIfNotHandled()}")
//            Log.d(TAG, "onCreateView: peek ${it.peekContent()}")
//        }
