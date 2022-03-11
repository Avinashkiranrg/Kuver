package com.example.kuver.ui.main.viewState

import com.example.kuver.data.model.LoginResponse
import com.example.kuver.data.model.RegistrationResponseModel

sealed class MainState{

    object Idel : MainState()
    object Loading : MainState()
    data class LoginStatus(val response: LoginResponse): MainState()
    data class RegisterUserStatus(val responseRegister: RegistrationResponseModel): MainState()
    data class Error(val error: String?): MainState()
}
