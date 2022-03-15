package com.example.kuver.ui.main.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.kuver.Utils.ActivityUtil
import com.example.kuver.data.api.ApiHelperImpl
import com.example.kuver.data.api.RetrofitBuilder
import com.example.kuver.data.model.LoginResponse
import com.example.kuver.databinding.ActivityLoginBinding
import com.example.kuver.ui.main.home.HomeActivity
import com.example.kuver.ui.main.intent.MainIntent
import com.example.kuver.ui.main.viewModel.LoginViewModel
import com.example.kuver.ui.main.viewState.MainState
import com.example.mviapp.util.ViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {

            loginViewModel.state.collect {
                when (it) {

                    is MainState.Idel -> {
                        Log.e("LoginIdel", "Login: ")
                    }
                    is MainState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is MainState.LoginStatus -> {
                        binding.progressBar.visibility = View.GONE

                        if(it.response.status.equals("1")) {
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            intent.putExtra("mobileNum", binding.mobileNum.text.toString())
                            intent.putExtra("passcode", binding.passcode.text.toString())
                            ActivityUtil.startActivity(this@LoginActivity, intent, true)
                            renderLoginUser(it.response)

                            Log.e("LoginDetails","Login" + it.response.base_path + it.response.user_details?.profile_pic)

                        }else{
                            Toast.makeText(this@LoginActivity,it.response.message,Toast.LENGTH_SHORT).show()
                        }
                    }
                    is MainState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Log.e("Error", "Failed to Login" + it.error)
                    }
                }
            }
        }

    }

    private fun renderLoginUser(loginStatus: LoginResponse) {

        Log.e("LoginStatus!!!!", loginStatus.status + " " + loginStatus.message)
    }


    private fun setupViewModel() {

        loginViewModel = ViewModelProviders.of(
            this, ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(LoginViewModel::class.java)

    }

    private fun setupClicks() {
        binding.loginButton.setOnClickListener {
            if (checkValidations()) {
                lifecycleScope.launch {
                    loginViewModel.loginIntent.send(
                        MainIntent.LoginUser(
                            binding.mobileNum.text.toString(),
                            binding.passcode.text.toString()
                        )
                    )

                }
            }
        }

        binding.signUp.setOnClickListener {

            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupUI() {

        //  mobileNum = intent?.getStringExtra("mobileNum")
        //  passcode = intent?.getStringExtra("passcode")

    }

    private fun checkValidations(): Boolean {


        if (binding.mobileNum.text.toString().trim().isEmpty()) {
            binding.mobileNum.error = " Please Enter Mobile Number "

            return false
        }

       /* if (binding.mobileNum.text.toString().trim().length != 10) {
            binding.mobileNum.error = " Please Enter the Valid Number"

            return false
        }*/

        if (binding.mobileNum.text.toString().trim().contains(" ")) {
            binding.mobileNum.error = "Please Remove Extra Space in Number"

            return false
        }

        if (binding.passcode.text?.isEmpty()!!) {
            showMessage("Please Enter Passcode")

            return false
        }
        /* if (passcode?.length != 6) {

             showMessage("Please Enter valid Passcode")

             return false
         }
 */
        return true
    }

    fun showMessage(mgs: String?) {

        //  AlertUtil.showSnackBar(binding.constraintLay, mgs)

    }

}