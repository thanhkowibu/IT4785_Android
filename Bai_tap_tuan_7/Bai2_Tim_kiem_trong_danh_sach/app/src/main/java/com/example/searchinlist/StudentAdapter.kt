package com.example.searchinlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private var students: List<Student>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullName: TextView = itemView.findViewById(R.id.tvFullName)
        val studentId: TextView = itemView.findViewById(R.id.tvStudentId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.fullName.text = student.fullName
        holder.studentId.text = student.studentId
    }

    override fun getItemCount(): Int {
        return students.size
    }

    fun updateList(newList: List<Student>) {
        students = newList
        notifyDataSetChanged()
    }
}