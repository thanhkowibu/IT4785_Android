package com.example.emailuisimulator

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var studentID: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize the RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.itemRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Generate a list of random items
        val items = generateRandomItems()

        // Set the adapter with the generated items
        val adapter = ItemAdapter(items)
        recyclerView.adapter = adapter
    }

    private fun generateRandomItems(): List<ItemData> {
        val items = mutableListOf<ItemData>()
        val itemCount = Random.nextInt(20, 60)

        for (i in 0 until itemCount) {
            // Generate random sender name, summary, and time
            val senderName = senderNameArray.random()
            val summary = contentArray.random()

            sendTimeNum -= delayTime
            val hour = sendTimeNum / 60
            val minute = sendTimeNum % 60
            val sendTime = String.format("%02d:%02d", hour, minute)

            items.add(ItemData(senderName, sendTime, summary))
        }
        return items
    }

    companion object {
        private val senderNameArray = arrayOf("Huy Tran", "Minh Le", "Quan Le", "An Nguyen", "Hoang Nguyen", "Peter");
        private val contentArray = arrayOf(
            "What's your schedule like today?",
            "Are you up for a quick chat?",
            "Could you give me a hand with something?",
            "Did you finish that report?",
            "Do you want to catch up later?",
            "Any ideas for the project deadline?",
            "Did you see the latest updates?",
            "Are we still on for the weekend?",
            "Need any help with your assignment?",
            "Got any good plans for the holidays?"
        )
        var sendTimeNum: Int = 1380
        private const val delayTime = 5
    }
}
