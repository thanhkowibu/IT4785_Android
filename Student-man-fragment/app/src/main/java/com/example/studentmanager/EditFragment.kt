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

class EditFragment(private var listener: OnStudentUpdatedListener, var position: Int, var name: String, var id: String): Fragment() {
    public  interface OnStudentUpdatedListener{
        fun onStudentUpdated(updatedName: String, updatedId: String, position: Int)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editFullName = view.findViewById<EditText>(R.id.edit_fullname)
        val mark = view.findViewById<TextView>(R.id.mark)
        val editStudentId = view.findViewById<EditText>(R.id.edit_student_id)
        editFullName.setText(name)
        editStudentId.setText(id)
        val buttonCancel = view.findViewById<Button>(R.id.button_cancel)
        val buttonOk = view.findViewById<Button>(R.id.button_ok)
        buttonCancel.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager.findFragmentByTag("stack")
            if (fragmentManager != null) {
                requireActivity().supportFragmentManager.beginTransaction().remove(fragmentManager).commit()
            }
        }
        buttonOk.setOnClickListener {
            val updatedName = editFullName.text.toString()
            val updatedId = editStudentId.text.toString()
            if (updatedName.isNotEmpty() || updatedId.isNotEmpty()){
                listener.onStudentUpdated(updatedName, updatedId, position)
                Toast.makeText(requireContext(), "Student updated", Toast.LENGTH_SHORT).show()
            }
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}