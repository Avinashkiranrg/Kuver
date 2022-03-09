package com.example.kuver.ui.main.intent

sealed class MainIntent {


    class LoginUser(var mobileNo: String, var passcode: String):MainIntent()


}
