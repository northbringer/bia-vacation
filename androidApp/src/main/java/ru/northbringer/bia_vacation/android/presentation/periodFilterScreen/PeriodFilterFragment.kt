package ru.northbringer.bia_vacation.android.presentation.periodFilterScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.util.Pair
import com.google.android.material.datepicker.MaterialDatePicker
import ru.northbringer.bia_vacation.android.R
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import java.lang.String.format
import java.text.DateFormat
import java.util.*

class PeriodFilterFragment : Fragment() {

    private lateinit var periodFilterBackButton: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_period_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Выберите даты")
                .build()

        dateRangePicker.show(childFragmentManager, "tag");
        dateRangePicker.addOnPositiveButtonClickListener {
            val startDate = it.first
            val endDate = it.second
            val startDateString = android.text.format.DateFormat.format(
                "dd/MM/yyyy",
                Date(startDate)
            ).toString()
            val endDateString =
                android.text.format.DateFormat.format("dd/MM/yyyy", Date(endDate)).toString()
            val date = "Start: " + startDateString + " End: " + endDateString;
            Toast.makeText(requireContext(), date, Toast.LENGTH_SHORT).show();
        }
    }

    private fun initViews(view: View) {
        periodFilterBackButton = view.findViewById(R.id.period_filter_button_back)
    }

    private fun initListeners() {
        periodFilterBackButton.setOnClickListener {
            activity!!.onBackPressed()
        }
    }

}