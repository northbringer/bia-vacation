package ru.northbringer.bia_vacation.android.presentation.periodFilterScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import ru.northbringer.bia_vacation.android.R


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