package ru.northbringer.bia_vacation.android.presentation.forgotPasswordScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import ru.northbringer.bia_vacation.android.R

class ForgotPasswordFragment : Fragment() {

    private lateinit var forgotPasswordBackButton: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
    }

    private fun initViews(view: View) {
        forgotPasswordBackButton = view.findViewById(R.id.forgot_password_back_button)
    }

    private fun initListeners() {
        forgotPasswordBackButton.setOnClickListener {
            activity!!.onBackPressed()
        }
    }

}