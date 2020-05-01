package com.example.examenabril

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {
    lateinit var createViewModel: CreateViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        createViewModel = CreateViewModel(CreateRepository())
        createViewModel.model.observe(this, Observer(::updateUi))

        create_btn.setOnClickListener {
            createViewModel.doLogin(name.text.toString())
        }
    }

    private fun updateUi(model: CreateViewModel.UiModel?) {
        loading_progress_bar.visibility = if ( model is LoginViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when ( model ) {
            is CreateViewModel.UiModel.Login -> validarLogin(model.success)
        }
    }

    private fun validarLogin( resp: Boolean) {
        if( resp) {
            Toast.makeText(this, "Exito", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "No exitoso", Toast.LENGTH_LONG).show()
        }

    }

}