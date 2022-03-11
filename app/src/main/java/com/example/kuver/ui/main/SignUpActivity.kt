package com.example.kuver.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.parser.IntegerParser
import com.example.kuver.Utils.ActivityUtil
import com.example.kuver.data.api.ApiHelperImpl
import com.example.kuver.data.api.RetrofitBuilder
import com.example.kuver.databinding.ActivitySignUpBinding
import com.example.kuver.ui.main.intent.MainIntent
import com.example.kuver.ui.main.viewModel.RegisterViewModel
import com.example.kuver.ui.main.viewState.MainState
import com.example.mviapp.util.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {

    private var _binding: ActivitySignUpBinding? = null
    private val binding get() = _binding!!

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //   setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()

    }

    private fun setupClicks() {

        binding.signUpButton.setOnClickListener {
            if (checkValidations()){

                lifecycleScope.launch {

                    registerViewModel.registerIntent.send(

                        MainIntent.RegistrationUser(
                            binding.userNameEdtxt.text.toString(),
                            binding.fullNameEdtxt.text.toString(),
                            binding.mobileNumSgn.text.toString(),
                            binding.emailEdtxt.text.toString(),
                            binding.passwordSgn.text.toString(),
                            binding.cfmPasscodeSgn.text.toString()
                        )
                    )
                }

            }

        }

        binding.alloginTxt.setOnClickListener {

            val intent = Intent(this@SignUpActivity,LoginActivity::class.java)
            ActivityUtil.startActivity(this@SignUpActivity,intent,true)
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {

            registerViewModel.state.collect {
                when (it) {

                    is MainState.Idel -> {
                        Log.e("Register", "Registration")
                    }
                    is MainState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is MainState.RegisterUserStatus -> {
                        binding.progressBar.visibility = View.GONE

                        if(it.responseRegister.status.equals("1")){

                          val intent = Intent(this@SignUpActivity,HomeActivity::class.java)
                            ActivityUtil.startActivity(this@SignUpActivity,intent,true)
                            Toast.makeText(this@SignUpActivity,it.responseRegister.message,Toast.LENGTH_SHORT).show()

                        }else{
                            Toast.makeText(this@SignUpActivity,it.responseRegister.message,Toast.LENGTH_SHORT).show()
                        }
                    }
                    is MainState.Error -> {
                        binding.progressBar.visibility = View.GONE

                        Log.e("Error!","Failed to Register" + it.error)
                    }
                }
            }
        }
    }

    private fun setupViewModel() {

        registerViewModel = ViewModelProviders.of(
            this, ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(RegisterViewModel::class.java)
    }


    private fun checkValidations(): Boolean {

        if (binding.userNameEdtxt.text.toString().trim().isEmpty()) {
            binding.userNameEdtxt.error = "Please Enter User Name"
            return false
        } else if (binding.fullNameEdtxt.text.toString().trim().isEmpty()) {
            binding.fullNameEdtxt.error = "Please Enter Full Name"
            return false
        } else if (binding.mobileNumSgn.text.toString().trim().isEmpty()) {
            binding.mobileNumSgn.error = " Please Enter Mobile Number "
            return false
        } else if (binding.mobileNumSgn.text.toString().trim().length != 10) {
            binding.mobileNumSgn.error = " Please Enter the Valid Number"
            return false
        } else if (binding.emailEdtxt.text.toString().trim().isEmpty()) {
            binding.emailEdtxt.error = " Please Enter Email Id"
            return false
        } else if (binding.passwordSgn.text.toString().trim().isEmpty()) {
            binding.passwordSgn.error = " Please Enter the Valid Number"
            return false
        } else if (binding.cfmPasscodeSgn.text.toString().trim().isEmpty()) {
            binding.cfmPasscodeSgn.error = " Please Enter the Valid Number"
            return false
        }
        if (binding.passwordSgn.length() != 6) {

            showMessage("Please Enter More then Six words")

            return false
        }
        return true
    }

    fun showMessage(mgs: String?) {

        //  AlertUtil.showSnackBar(binding.constraintLay, mgs)

    }
}