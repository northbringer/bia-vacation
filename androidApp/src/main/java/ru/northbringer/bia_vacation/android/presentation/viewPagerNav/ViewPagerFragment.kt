package ru.northbringer.bia_vacation.android.presentation.viewPagerNav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.northbringer.bia_vacation.android.R
import ru.northbringer.bia_vacation.android.presentation.diagramScreen.DiagramFragment
import ru.northbringer.bia_vacation.android.presentation.mainScreen.MainFragment
import ru.northbringer.bia_vacation.android.presentation.planningScreen.PlanningFragment

class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf(MainFragment(), PlanningFragment(), DiagramFragment())

        val viewPagerAdapter =
            ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = viewPagerAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.tabItemMain)
                    tab.setIcon(R.drawable.ic_home)
                }

                1 -> {
                    tab.text = getString(R.string.tabItemNewVacation)
                    tab.setIcon(R.drawable.ic_add)
                }

                2 -> {
                    tab.text = getString(R.string.tabItemDiagram)
                    tab.setIcon(R.drawable.ic_calendar)
                }
            }
        }.attach()
    }
}