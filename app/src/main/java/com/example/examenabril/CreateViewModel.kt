package com.example.examenabril


import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateViewModel(val createRepository: CreateRepository) : ViewModel() {

    val model: LiveData<UiModel>
        get() = _model
    private val _model = MutableLiveData<UiModel>()

    sealed class UiModel {
        class Login(val success: Boolean): UiModel()
        object Loading: UiModel()
    }

    fun doCreate(userName: String) {
        _model.value = UiModel.Loading
        val runnable = Runnable {
            _model.value = UiModel.Create( createRepository.create(Name))
        }
        Handler().postDelayed(runnable, 3000)

    }
}