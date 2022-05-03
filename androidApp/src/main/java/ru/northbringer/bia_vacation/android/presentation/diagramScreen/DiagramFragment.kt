package ru.northbringer.bia_vacation.android.presentation.diagramScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.northbringer.bia_vacation.android.gant_view.GantView
import ru.northbringer.bia_vacation.android.R

class DiagramFragment : Fragment() {

    private lateinit var diagramShowFiltersButton: ImageView
    private lateinit var diagramGant: GantView

    private val viewModel by viewModel<DiagramViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_diagram, container, false)
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
        viewModel.getVacation().observe(this, {
            diagramGant.setTasks(it)
        })
        viewModel.loadVacations()
    }

    private fun initViews(view: View) {
        diagramShowFiltersButton = view.findViewById(R.id.filter_button_back)
        diagramGant = view.findViewById(R.id.gantView)
    }

    private fun initListeners() {
        diagramShowFiltersButton.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_filterFragment)
        }
    }
}