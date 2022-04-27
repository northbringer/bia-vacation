package ru.northbringer.bia_vacation.android.presentation.filterScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.northbringer.bia_vacation.android.R


class FilterFragment : Fragment() {

    private lateinit var filterOpenEmployeeFilterButton: ImageView
    private lateinit var filterOpenPeriodFilterButton: ImageView
    private lateinit var filterBackButton: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
    }

    private fun initViews(view: View) {
        filterOpenEmployeeFilterButton = view.findViewById(R.id.filter_button_open_employee_filter)
        filterOpenPeriodFilterButton = view.findViewById(R.id.filter_button_open_period_filter)
        filterBackButton = view.findViewById(R.id.filter_button_back)
    }

    private fun initListeners() {
        filterOpenEmployeeFilterButton.setOnClickListener {
            findNavController().navigate(R.id.action_filterFragment_to_employeeFilterFragment)
        }

        filterOpenPeriodFilterButton.setOnClickListener {
            findNavController().navigate(R.id.action_filterFragment_to_periodFilterFragment)
        }

        filterBackButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }



}