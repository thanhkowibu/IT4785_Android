package com.example.searchlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchlist.R

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText

    private val students = listOf(
        Student("Nguyen Van A", "20123456"),
        Student("Le Thi B", "20123457"),
        Student("Tran Van C", "20123458")
        // Thêm sinh viên khác nếu cần
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchEditText = findViewById(R.id.searchEditText)
        recyclerView = findViewById(R.id.recyclerView)

        studentAdapter = StudentAdapter(students)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val keyword = s.toString()
                if (keyword.length > 2) {
                    val filteredList = students.filter {
                        it.name.contains(keyword, ignoreCase = true) ||
                        it.mssv.contains(keyword, ignoreCase = true)
                    }
                    studentAdapter.updateList(filteredList)
                } else {
                    studentAdapter.updateList(students)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}