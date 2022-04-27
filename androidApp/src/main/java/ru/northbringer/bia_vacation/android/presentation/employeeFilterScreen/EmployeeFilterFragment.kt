package ru.northbringer.bia_vacation.android.presentation.employeeFilterScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import ru.northbringer.bia_vacation.android.R


class EmployeeFilterFragment : Fragment() {

    private lateinit var employeeFilterBackButton: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_employee_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
    }

    private fun initViews(view: View) {
        employeeFilterBackButton = view.findViewById(R.id.employee_filter_button_back)
    }

    private fun initListeners() {
        employeeFilterBackButton.setOnClickListener {
            activity!!.onBackPressed()
        }
    }

}