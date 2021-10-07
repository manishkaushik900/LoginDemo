package com.demo.logindemo.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.logindemo.R
import com.demo.logindemo.data.LoginRepository
import com.demo.logindemo.data.Result
import com.demo.logindemo.data.model.LoginFormState
import com.demo.logindemo.data.model.LoginResult
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        val result = loginRepository.login(username, password)

        if (result is Result.Success) {
            _loginResult.value = LoginResult(success =result.data.displayName)
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username) && !isPasswordValid(password)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username,
                passwordError = R.string.invalid_password)
        }
        else if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.isNotEmpty()) {
           /* !username.contains(" ") &&*/ isValidUserName(username)
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return isValidPassword(password)
    }

    private fun isValidUserName(username: String): Boolean {
        val expression = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=\\S+\$)[A-Za-z0-9]+$"

        val p: Pattern = Pattern.compile(expression)
        val m: Matcher = p.matcher(username)
        return m.matches();
    }

    private fun isValidPassword(username: String): Boolean {
        val expression =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@])(?=.*[!])(?=.*[?])(?=.*[_])(?=\\S+$).{4,}$"

        val p: Pattern = Pattern.compile(expression)
        val m: Matcher = p.matcher(username)
        return m.matches();
    }
}