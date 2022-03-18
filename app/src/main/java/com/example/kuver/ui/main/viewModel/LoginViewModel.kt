package com.example.kuver.ui.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kuver.data.model.LoginRequest
import com.example.kuver.data.repository.MainRepository
import com.example.kuver.ui.main.intent.MainIntent
import com.example.kuver.ui.main.viewState.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val     repository: MainRepository
) : ViewModel() {

    val loginIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Idel)
    val state: StateFlow<MainState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {

            loginIntent.consumeAsFlow().collect {
                when (it) {

                    is MainIntent.LoginUser -> fatchUser(it.mobileNo, it.passcode)
                }
            }

        }
    }

    private fun fatchUser(mobileNo: String, passWord: String) {

        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                val request = LoginRequest(9876543, "en", mobileNo, passWord, 2, "android", 123456)
                MainState.LoginStatus(repository.getLoginRequest(request))
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }

    }
}