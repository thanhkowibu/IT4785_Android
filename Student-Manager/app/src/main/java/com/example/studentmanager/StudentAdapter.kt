package com.example.studentmanager

import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(val students: MutableList<StudentModel>,
                     val listener: OnStudentItemClickListener): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnCreateContextMenuListener {

        val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
        val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)

        init {
            // Attach context menu listener to itemView
            itemView.setOnCreateContextMenuListener(this)
            itemView.setOnLongClickListener {
                // Set the adapter position in a static variable for retrieval
                selectedPosition = adapterPosition
                false // Return false to trigger context menu
            }
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item,
            parent, false)
        return StudentViewHolder(itemView)
    }

    override fun getItemCount(): Int = students.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]

        holder.textStudentName.text = student.studentName
        holder.textStudentId.text = student.studentId
    }
    companion object {
        // Static variable to track the currently selected position
        var selectedPosition: Int = -1
    }
}

interface OnStudentItemClickListener {
    fun onEditClick(student: StudentModel)
    fun onRemoveClick(student: StudentModel)
}

