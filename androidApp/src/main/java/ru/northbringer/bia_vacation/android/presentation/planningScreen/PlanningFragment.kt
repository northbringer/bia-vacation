package ru.northbringer.bia_vacation.android.presentation.planningScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import ru.northbringer.bia_vacation.android.R


class PlanningFragment : Fragment() {

    private lateinit var planningAddPeriodButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_planning, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
    }

    private fun initViews(view: View) {
        planningAddPeriodButton = view.findViewById(R.id.button_add_period)
    }

    private fun initListeners() {
        planningAddPeriodButton.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_planningCalendarFragment)
        }
    }

}