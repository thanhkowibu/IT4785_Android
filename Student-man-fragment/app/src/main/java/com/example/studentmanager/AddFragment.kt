package com.example.studentmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class AddFragment(private val listener: OnStudentAddedListener): Fragment() {
    interface OnStudentAddedListener{
        fun onStudentAdded(name: String, id: String)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonCancel = view.findViewById<Button>(R.id.button_cancel)
        val sid = view.findViewById<TextView>(R.id.id)
        AddAndEditStudent.mark(sid, "3225", "1203")
        val buttonOk = view.findViewById<Button>(R.id.button_ok)
        val editFullName = view.findViewById<EditText>(R.id.edit_fullname)
        val editStudentId = view.findViewById<EditText>(R.id.edit_student_id)
        buttonCancel.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        buttonOk.setOnClickListener {
            val name = editFullName.text.toString()
            val id = editStudentId.text.toString()
            if (name.isNotEmpty() || id.isNotEmpty()){
                listener.onStudentAdded(name, id)
                Toast.makeText(requireContext(), "Student added", Toast.LENGTH_SHORT).show()
            }
            requireActivity().supportFragmentManager.popBackStack()
        }
        AddAndEditStudent.mark(sid, "7733", "2024")
    }
}