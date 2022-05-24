package ru.northbringer.bia_vacation.android.presentation.employeeFilterScreen

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import ru.northbringer.bia_vacation.android.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class EmployeeFilterFragment : Fragment() {

    private lateinit var employeeFilterBackButton: ImageView
    private lateinit var employeeFilterSearchEditText: TextInputEditText
    private var adapter: EmployeeFilterAdapter = EmployeeFilterAdapter(arrayListOf())

    private lateinit var employeeFilterSelectAllLinearLayout: LinearLayout
    private lateinit var employeeFilterSelectAllCheckBox: CheckBox
    private lateinit var employeeFilterSelectAllTextView: TextView

    private lateinit var list: ArrayList<Employee>
    private val spanHighlight by lazy {
        ForegroundColorSpan(
            ResourcesCompat.getColor(resources, R.color.brand, null)
        )
    }
    private lateinit var employeeFilterRecyclerView: RecyclerView
    private val viewModel by viewModel<EmployeeFilterViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_employee_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val users = arrayListOf(Employee("Любченко Дмитрий"),
            Employee("Седельникова Кристина"),
            Employee("Копылов Никита"),
            Employee("Чежина Дарья"),
            Employee("Попов Павел"),
            Employee("Любченко Дмитрий"),
            Employee("Любченко Дмитрий"),
            Employee("Любченко Дмитрий"),
            Employee("Любченко Дмитрий"),
            Employee("Любченко Дмитрий"),
            Employee("Любченко Дмитрий"))
        list = users
        initViews(view)
        initListeners()
        viewModel.loadEmployees()
    }

    private fun initViews(view: View) {
        employeeFilterBackButton = view.findViewById(R.id.employee_filter_button_back)
        employeeFilterSearchEditText = view.findViewById(R.id.employee_filter_editText_search)
        employeeFilterRecyclerView = view.findViewById(R.id.rv)
        employeeFilterSelectAllLinearLayout = view.findViewById(R.id.lin)

        val child: View = layoutInflater.inflate(R.layout.list_item_employee_filter, null)
        employeeFilterSelectAllCheckBox = child.findViewById(R.id.listItem_checkBox)
        employeeFilterSelectAllTextView = child.findViewById(R.id.listItem_textVew_name)
        employeeFilterSelectAllTextView.text = getString(R.string.employee_filter_screen_selectAll_textView)
        employeeFilterSelectAllTextView.typeface = Typeface.DEFAULT_BOLD
        employeeFilterSelectAllLinearLayout.addView(child)

        employeeFilterSelectAllCheckBox = child.findViewById(R.id.listItem_checkBox)
        adapter = EmployeeFilterAdapter(list)
        employeeFilterRecyclerView.adapter = adapter
        employeeFilterRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initListeners() {
        employeeFilterBackButton.setOnClickListener {
            activity!!.onBackPressed()
        }
        employeeFilterSearchEditText.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                updateSearch()
                highlight()
            }

            override fun beforeTextChanged(
                arg0: CharSequence, arg1: Int, arg2: Int,
                arg3: Int
            ) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(arg0: Editable) {
                // TODO Auto-generated method stub
            }
        })

        employeeFilterSelectAllCheckBox.setOnCheckedChangeListener { compoundButton: CompoundButton,
                                                                     isChecked: Boolean ->
            if (isChecked) {
                employeeFilterRecyclerView.forEach {
                    it.findViewById<CheckBox>(R.id.listItem_checkBox).isChecked = true
                }
            } else {
                employeeFilterRecyclerView.forEach {
                    it.findViewById<CheckBox>(R.id.listItem_checkBox).isChecked = false
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateSearch() {
        val s = employeeFilterSearchEditText.text

        if (s?.length == 0) {
            adapter.list = list
            employeeFilterSelectAllLinearLayout.visibility = View.VISIBLE

        } else {
            adapter.list = list.filter {
                it.name.startsWith(s.toString(), true) || it.name.contains(s.toString(), true)
            } as ArrayList
            employeeFilterSelectAllLinearLayout.visibility = View.GONE
        }
        adapter.notifyDataSetChanged()
    }

    private fun highlight() {
        val s: String = employeeFilterSearchEditText.text.toString()
        adapter.list.forEach { item ->

            item.name.getSpans(0, item.name.length, ForegroundColorSpan::class.java).forEach {
                item.name.removeSpan(it)
            }

            item.name.getSpans(0, item.name.length, ForegroundColorSpan::class.java).forEach {
                item.name.removeSpan(it)
            }

            if (item.name.startsWith(s, true)) {
                item.name.setSpan(spanHighlight, 0, s.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            if (item.name.contains(s, true)) {
                val index = item.name.toString().indexOf(s, 0, true)
                item.name.setSpan(
                    spanHighlight,
                    index,
                    index + s.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
    }
}