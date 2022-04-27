package ru.northbringer.bia_vacation.android.presentation.planningCalendarScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import ru.northbringer.bia_vacation.android.R

class PlanningCalendarFragment : Fragment() {

    private lateinit var planningCalendarBackButton: ImageView
    private lateinit var planningCalendarCreateVacationButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_planning_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
    }

    private fun initViews(view: View) {
        planningCalendarBackButton = view.findViewById(R.id.period_filter_button_back)
        planningCalendarCreateVacationButton = view.findViewById(R.id.planning_calendar_button_create_vacation)
    }

    private fun initListeners() {
        planningCalendarBackButton.setOnClickListener {
            activity?.onBackPressed()
        }

        planningCalendarCreateVacationButton.setOnClickListener {
            //findNavController().navigate(R.id.action_planningCalendarFragment_to_viewPagerFragment) // check out backstack here
            activity?.onBackPressed()
        }
    }

}