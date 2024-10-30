package com.example.searchinlist

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var students: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val students = listOf(
            Student("Nguyen Van An", "20210001"),
            Student("Tran Thi Binh", "20210002"),
            Student("Le Quoc Cuong", "20210003"),
            Student("Pham Thi Dao", "20210004"),
            Student("Vo Van Dung", "20210005"),
            Student("Nguyen Thi Ha", "20210006"),
            Student("Pham Hoang Hai", "20210007"),
            Student("Tran Ngoc Hien", "20210008"),
            Student("Le Thi Hong", "20210009"),
            Student("Do Van Hung", "20210010"),
            Student("Nguyen Thi Lan", "20220011"),
            Student("Tran Van Long", "20220012"),
            Student("Le Thi Mai", "20220013"),
            Student("Pham Hoang Nam", "20220014"),
            Student("Nguyen Thi Ngoc", "20220015"),
            Student("Vo Thi Phuong", "20220016"),
            Student("Tran Van Quoc", "20220017"),
            Student("Le Thi Thanh", "20220018"),
            Student("Nguyen Quoc Tuan", "20220019"),
            Student("Pham Thi Xuan", "20220020")
        )


        studentAdapter = StudentAdapter(students)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = students.filter {
                    it.fullName.contains(newText ?: "", ignoreCase = true) ||
                            it.studentId.contains(newText ?: "", ignoreCase = true)
                }
                studentAdapter.updateList(filteredList)
                return true
            }
        })
    }
}