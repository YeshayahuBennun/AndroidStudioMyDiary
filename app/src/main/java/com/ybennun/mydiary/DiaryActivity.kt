package com.ybennun.mydiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_diary.*

class DiaryActivity : AppCompatActivity() {

    private var diaryList: ArrayList<Diary> = ArrayList()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: DiaryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        diaryList.add(Diary("2018", "first Diary", "my first diary "))
        diaryList.add(Diary("2017", "second Diary", "my second diary "))

        linearLayoutManager = LinearLayoutManager(this)
        recycle_view.layoutManager = linearLayoutManager

        adapter = DiaryAdapter(diaryList)

        recycle_view.adapter = adapter

    }

    fun createNewDiary(view: View) {
        val intent = Intent(
            this, NewDiary::class.java
        )
        startActivity(intent)
    }
}