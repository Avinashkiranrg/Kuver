package com.example.kuver.ui.main.viewState

import com.example.kuver.data.model.*
import com.example.kuver.local.entity.CatDBModel

sealed class MainState {

    object Idel : MainState()
    object Loading : MainState()
    data class LoginStatus(val response: LoginResponse) : MainState()
    data class RegisterUserStatus(val responseRegister: RegistrationResponseModel) : MainState()
    data class CatagoryStatus(val categoryResponseModel: List<CatDBModel>) : MainState()
    data class SubCatagoryStatus(val subCatResponseModel: SubCatResponseModel) : MainState()
    data class CitiesStatus(val citiesResponseModel: CitiesResponseModel) : MainState()
    data class Error(val error: String?) : MainState()
}
