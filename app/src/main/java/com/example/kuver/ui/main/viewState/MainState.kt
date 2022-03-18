package com.example.kuver.ui.main.viewState

import com.example.kuver.data.model.*

sealed class MainState {

    object Idel : MainState()
    object Loading : MainState()
    data class LoginStatus(val response: LoginResponse) : MainState()
    data class RegisterUserStatus(val responseRegister: RegistrationResponseModel) : MainState()
    data class CatagoryStatus(val categoryResponseModel: CatResponseModel) : MainState()
    data class SubCatagoryStatus(val subCatResponseModel: SubCatResponseModel) : MainState()
    data class CitiesStatus(val citiesResponseModel: CitiesResponseModel) : MainState()
    data class Error(val error: String?) : MainState()
}
