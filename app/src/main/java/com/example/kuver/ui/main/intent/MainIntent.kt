package com.example.kuver.ui.main.intent

sealed class MainIntent {


    class LoginUser(var mobileNo: String, var passcode: String) : MainIntent()

    class RegistrationUser(
        var userName: String, var fullName: String, var mobileNum: String,
        var email: String, var passCode: String, var cfmPasscode: String
    ) : MainIntent()

    class CategoryMain() : MainIntent()

    class SubCategoryMain( var categoryID : String) : MainIntent()

    class CitiesMain():MainIntent()

}
