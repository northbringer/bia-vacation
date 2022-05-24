package ru.northbringer.bia_vacation.android.presentation.filterScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.northbringer.bia_vacation.android.R
import java.util.*


class FilterFragment : Fragment() {

    private lateinit var filterOpenEmployeeFilterButton: ImageView
    private lateinit var filterOpenPeriodFilterButton: ImageView
    private lateinit var filterBackButton: ImageView

    private lateinit var filterSelectedPeriodTextView: TextView

    private var startDate = ""
    private var endDate = ""

    private val dateRangePicker =
        MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("Выберите даты")
            .build()

    private val viewModel by viewModel<FilterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadStartDate()
        initViews(view)
        initListeners()
        viewModel.test.observe(this, {
            filterSelectedPeriodTextView.text = it.toString()
        })
        viewModel.loadData()

    }

    private fun initViews(view: View) {
        filterOpenEmployeeFilterButton = view.findViewById(R.id.filter_button_open_employee_filter)
        filterOpenPeriodFilterButton = view.findViewById(R.id.filter_button_open_period_filter)
        filterBackButton = view.findViewById(R.id.filter_button_back)
        filterSelectedPeriodTextView = view.findViewById(R.id.filter_textView_selected_period)
        filterSelectedPeriodTextView.text = "$startDate - $endDate"
    }

    private fun initListeners() {
        filterOpenEmployeeFilterButton.setOnClickListener {
            findNavController().navigate(R.id.action_filterFragment_to_employeeFilterFragment)
        }

        filterOpenPeriodFilterButton.setOnClickListener {

            dateRangePicker.show(childFragmentManager, "tag");

            //findNavController().navigate(R.id.action_filterFragment_to_periodFilterFragment)
        }

        filterBackButton.setOnClickListener {
            activity?.onBackPressed()
        }

        dateRangePicker.addOnPositiveButtonClickListener {
            val startDt = it.first
            val endDt = it.second
            startDate = android.text.format.DateFormat.format(
                "dd/MM/yyyy",
                Date(startDt)
            ).toString()
            endDate =
                android.text.format.DateFormat.format("dd/MM/yyyy", Date(endDt)).toString()
            val date = "Start: " + startDate + " End: " + endDate
            Toast.makeText(requireContext(), date, Toast.LENGTH_SHORT).show()
            filterSelectedPeriodTextView.text = "$startDate - $endDate"
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadStartDate()
    }
}