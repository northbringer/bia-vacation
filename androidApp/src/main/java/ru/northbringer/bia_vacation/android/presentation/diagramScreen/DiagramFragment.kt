package ru.northbringer.bia_vacation.android.presentation.diagramScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.northbringer.bia_vacation.android.R

class DiagramFragment : Fragment() {

    private lateinit var diagramShowFiltersButton: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_diagram, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
    }

    private fun initViews(view: View) {
        diagramShowFiltersButton = view.findViewById(R.id.filter_button_back)
    }

    private fun initListeners() {
        diagramShowFiltersButton.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_filterFragment)
        }
    }

}