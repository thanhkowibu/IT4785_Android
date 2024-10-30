package com.example.searchlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchlist.R

class StudentAdapter(private var students: List<Student>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val mssvTextView: TextView = itemView.findViewById(R.id.mssvTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.nameTextView.text = students[position].name
        holder.mssvTextView.text = students[position].mssv
    }

    override fun getItemCount(): Int {
        return students.size
    }

    fun updateList(filteredStudents: List<Student>) {
        students = filteredStudents
        notifyDataSetChanged()
    }
}