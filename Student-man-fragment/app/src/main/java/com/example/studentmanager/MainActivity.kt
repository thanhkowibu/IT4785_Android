package com.example.studentmanager
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher

import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(){
    lateinit var mainFragment: MainFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainFragment = MainFragment()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, mainFragment)
                .commit()
        }
    }

    fun AddText(s: String){
        val name = findViewById<TextView>(R.id.name)
        name.text = buildString {
            append(name.text.toString())
            append(s)
        };
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_student, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add -> {
                // Handle the add button click
                val fragment = AddFragment(mainFragment)
                supportFragmentManager.beginTransaction().add(R.id.fragment_container,
                    fragment, "stack")
                    .addToBackStack("stack")
                    .commit()
                //Toast.makeText(this, "Add button clicked", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}


