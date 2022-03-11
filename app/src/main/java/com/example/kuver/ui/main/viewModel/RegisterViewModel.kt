package com.example.kuver.ui.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kuver.data.model.RegistrationRequestModel
import com.example.kuver.data.repository.MainRepository
import com.example.kuver.ui.main.intent.MainIntent
import com.example.kuver.ui.main.viewState.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class RegisterViewModel(
     private val mainRepository: MainRepository
) : ViewModel(){

    val registerIntent = Channel<MainIntent> (Channel.UNLIMITED)
    private val _state  = MutableStateFlow<MainState>(MainState.Idel)
    val state : StateFlow<MainState>
        get() = _state

    init {
        handelIntent()
    }

    private fun handelIntent() {

        viewModelScope.launch {

            registerIntent.consumeAsFlow().collect {
                when (it){

                    is  MainIntent.RegistrationUser -> registerUser(it.userName,it.fullName,it.email,it.passCode,it.cfmPasscode,it.mobileNum)
                }
            }
        }
    }

    private suspend fun registerUser(
        userName: String,
        fullName: String,
        email: String,
        passCode: String,
        cfmPasscode: String,
        mobileNum: String
    ) {

        _state.value = MainState.Loading
        _state.value = try {

                    val request = RegistrationRequestModel(9876543,"en",fullName,userName,email,
                        "+966",mobileNum,passCode, cfmPasscode,"1","Android","123456")
            MainState.RegisterUserStatus(mainRepository.getRegistrationRequest(request))

        }catch (e : Exception){
            MainState.Error(e.localizedMessage)
        }
    }
}