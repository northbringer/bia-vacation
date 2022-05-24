package ru.northbringer.bia_vacation.android.presentation.employeeFilterScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.northbringer.bia_vacation.android.R

class EmployeeFilterAdapter(var list: ArrayList<Employee>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
            return MyViewHolder(
                LayoutInflater.from(p0.context).inflate(R.layout.list_item_employee_filter, p0, false)
            )
        }

        override fun getItemCount() = list.size

        override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
            (p0 as MyViewHolder).itemView.apply {
                this.findViewById<TextView>(R.id.listItem_textVew_name).text = list[p1].name
            }
        }
}