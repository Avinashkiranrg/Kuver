package com.example.mviapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kuver.data.api.ApiHelperImpl
import com.example.kuver.data.repository.MainRepository
import com.example.kuver.ui.main.viewModel.CategoryViewModel
import com.example.kuver.ui.main.viewModel.LoginViewModel
import com.example.kuver.ui.main.viewModel.RegisterViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ViewModelFactory(private val apiHelper: ApiHelperImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(MainRepository(apiHelper)) as T
        } else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(MainRepository(apiHelper)) as T
        } else if (modelClass.isAssignableFrom(CategoryViewModel::class.java))
            return CategoryViewModel(MainRepository(apiHelper)) as T

        throw IllegalAccessException("Unknow class name")
    }
}