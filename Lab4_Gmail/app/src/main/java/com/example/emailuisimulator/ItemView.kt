package com.example.emailuisimulator

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

public class ItemView:AppCompatActivity() {
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
    private lateinit var senderName: TextView
    private lateinit var sendTime: TextView
    private lateinit var summary: TextView
    lateinit var avatar: TextView
    companion object {
        var sendTimeNum: Int = 1380
    }
    private val delayTime = 5;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.item_view)
        senderName = findViewById(R.id.SenderName)
        sendTime = findViewById(R.id.SendTime)
        summary = findViewById(R.id.Summary)
        avatar = findViewById(R.id.Avatar)
        senderName.text = senderNameArray[(0..senderNameArray.size).random()]
        sendTimeNum -= delayTime
        val hour = sendTimeNum / 60
        val minute = sendTimeNum % 60
        if (hour < 10){
            if (minute < 10){
                sendTime.text = "0$hour:0$minute"
            }else{
                sendTime.text = "0$hour:$minute"
            }
        }
        else{
            if (minute < 10){
                sendTime.text = "$hour:0$minute"
            }else{
                sendTime.text = "$hour:$minute"
            }
        }
        summary.text = contentArray[(0..senderNameArray.size).random()]
        avatar.text = senderName.text[0].toString()
    }
}


