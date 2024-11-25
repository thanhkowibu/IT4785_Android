package com.example.studentmanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddAndEditStudent: AppCompatActivity() {
    public companion object{
        const val STUDENT_NAME = "student_name"
        const val STUDENT_ID = "student_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_student)
        val editFullName = findViewById<EditText>(R.id.edit_fullname)
        val editStudentId = findViewById<EditText>(R.id.edit_student_id)
        val buttonCancel = findViewById<Button>(R.id.button_cancel)
        val buttonOk = findViewById<Button>(R.id.button_ok)
        editFullName.setText(intent.getStringExtra(STUDENT_NAME))
        editStudentId.setText(intent.getStringExtra(STUDENT_ID))
        setResult(Activity.RESULT_CANCELED)
        buttonOk.setOnClickListener {
            val canAddAndEditStudent = !editFullName.text.isNullOrEmpty() || !editStudentId.text.isNullOrEmpty()
            if (canAddAndEditStudent){
                val intent = intent
                intent.putExtra(STUDENT_NAME, editFullName.text.toString())
                intent.putExtra(STUDENT_ID, editStudentId.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else{
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
        buttonCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

    }
}