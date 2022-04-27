package ru.northbringer.bia_vacation.android.presentation.loginScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.northbringer.bia_vacation.android.R
import ru.northbringer.bia_vacation.android.utils.showToast


class LoginFragment : Fragment() {

    private lateinit var loginEditTextLogin: EditText
    private lateinit var loginEditTextPassword: EditText
    private lateinit var loginSignInButton: Button
    private lateinit var loginForgotPasswordButton: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()

        devInfo()
    }

    private fun initViews(view: View) {
        loginEditTextLogin = view.findViewById(R.id.login_edit_text_username)
        loginEditTextPassword = view.findViewById(R.id.login_edit_text_password)
        loginSignInButton = view.findViewById(R.id.button_login)
        loginForgotPasswordButton = view.findViewById(R.id.login_button_forgot_password)
    }

    private fun initListeners() {

        loginSignInButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_viewPagerFragment)
        }

        loginForgotPasswordButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun devInfo() {
        loginEditTextLogin.setText("mserebryannikov")
        loginEditTextPassword.setText("12345")

        val density = resources.displayMetrics.density
        val densityDpi = resources.displayMetrics.densityDpi
        activity?.showToast("density:${density} densityDpi:${densityDpi}")
    }

}