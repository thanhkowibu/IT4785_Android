package com.example.studentmanager

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment(), EditFragment.OnStudentUpdatedListener, AddFragment.OnStudentAddedListener {
    private lateinit var students: MutableList<StudentModel>
    private lateinit var studentAdapter: StudentAdapter
    lateinit var recyclerView: RecyclerView
    private var selectedStudentIndex: Int = 0
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = view.findViewById<TextView>(R.id.name)
        // Initialize student list
        students = mutableListOf(
            StudentModel("Nguyễn Văn An", "SV001"),
            StudentModel("Trần Thị Bảo", "SV002"),
            StudentModel("Lê Hoàng Cường", "SV003"),
            StudentModel("Phạm Thị Dung", "SV004"),
            StudentModel("Đỗ Minh Đức", "SV005"),
            StudentModel("Vũ Thị Hoa", "SV006"),
            StudentModel("Hoàng Văn Hải", "SV007"),
            StudentModel("Bùi Thị Hạnh", "SV008"),
            StudentModel("Đinh Văn Hùng", "SV009"),
            StudentModel("Nguyễn Thị Linh", "SV010"),
            StudentModel("Phạm Văn Long", "SV011"),
            StudentModel("Trần Thị Mai", "SV012"),
            StudentModel("Lê Thị Ngọc", "SV013"),
            StudentModel("Vũ Văn Nam", "SV014"),
            StudentModel("Hoàng Thị Phương", "SV015"),
            StudentModel("Đỗ Văn Quân", "SV016"),
            StudentModel("Nguyễn Thị Thu", "SV017"),
            StudentModel("Trần Văn Tài", "SV018"),
            StudentModel("Phạm Thị Tuyết", "SV019"),
            StudentModel("Lê Văn Vũ", "SV020")
        )

        // Initialize adapter
        studentAdapter = StudentAdapter(students)
        // Set up RecyclerView
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_students)
        recyclerView.adapter = studentAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        registerForContextMenu(recyclerView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        Log.d("MainFragment", "onCreateContextMenu called")
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.main_menu, menu)
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        // Retrieve selected student using adapter position
        val position = StudentAdapter.selectedPosition
        when (item.itemId) {
            R.id.action_edit -> {
                // Handle edit action
//                val fragment = AddAndEditFragment.newInstance(students[position].studentName,
//                    students[position].studentId, position)
                val fragment = EditFragment(this, position, students[position].studentName, students[position].studentId)
                requireActivity().supportFragmentManager.beginTransaction().add(R.id.fragment_container,
                    fragment, "stack")
                    .addToBackStack("stack")
                    .commit()
                //Toast.makeText(requireContext(), "Edit clicked", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_delete -> {
                // Handle delete action
                AlertDialog.Builder(requireContext()).setMessage("Are you sure to delete this student?")
                    .setPositiveButton("Yes") { _, _ ->
                        val student = students[position]
                        students.removeAt(position)
                        studentAdapter.notifyItemRemoved(position)
                        recyclerView.scrollToPosition(position)
                        Snackbar.make(requireView(), "Student deleted", Snackbar.LENGTH_SHORT).setAction("Undo") {
                            students.add(position, student)
                            studentAdapter.notifyItemInserted(position)
                            recyclerView.scrollToPosition(position)
                        }.show()
                    }
                    .setNegativeButton("No") { _, _ -> }
                    .create()
                    .show()
                //Toast.makeText(requireContext(), "Delete clicked", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onStudentUpdated(updatedName: String, updatedId: String, position: Int) {
        if (position != -1) {
            students[position] = StudentModel(updatedName, updatedId)
            studentAdapter.notifyItemChanged(position)
        }
    }

    override fun onStudentAdded(name: String, id: String) {
        students.add(0, StudentModel(name, id))
        studentAdapter.notifyItemInserted(0)
        recyclerView.scrollToPosition(0)
    }
}
