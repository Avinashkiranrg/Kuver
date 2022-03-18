package com.example.kuver.ui.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kuver.data.model.CatRequestModel
import com.example.kuver.data.model.CitiesRequestModel
import com.example.kuver.data.model.SubCatRequestModel
import com.example.kuver.data.repository.MainRepository
import com.example.kuver.ui.main.intent.MainIntent
import com.example.kuver.ui.main.viewState.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class CategoryViewModel(

    private val repository: MainRepository
) : ViewModel() {

    val catIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Idel)
    val state: StateFlow<MainState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {

            catIntent.consumeAsFlow().collect {
                when (it) {

                    is MainIntent.CategoryMain -> fatchCat()

                    is MainIntent.SubCategoryMain -> fatchSubCat(it.categoryID)

                    is MainIntent.CitiesMain -> fatchCities()

                }
            }
        }
    }

    private fun fatchSubCat(categoryID: String) {
        viewModelScope.launch {

            _state.value = MainState.Loading
            _state.value = try {

                val request = SubCatRequestModel("9876543", "en", categoryID)
                MainState.SubCatagoryStatus(repository.getSubCatRequest(request))
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }

        }
    }

    private fun fatchCat() {

        viewModelScope.launch {

            _state.value = MainState.Loading
            _state.value = try {
                val request = CatRequestModel("9876543", "en")
                MainState.CatagoryStatus(repository.getCatRequest(request))

            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }

    private fun fatchCities() {

        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {

                val request = CitiesRequestModel("9876543", "en")
                MainState.CitiesStatus(repository.getCitiesRequest(request))

            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }
}