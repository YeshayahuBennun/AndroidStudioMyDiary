package com.ybennun.mydiary

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.ybennun.mydiary.data.DatabaseManager.DiaryEntry.COLUMN_DATE
import com.ybennun.mydiary.data.DatabaseManager.DiaryEntry.COLUMN_DIARY
import com.ybennun.mydiary.data.DatabaseManager.DiaryEntry.COLUMN_TITLE
import com.ybennun.mydiary.data.DatabaseManager.DiaryEntry.TABLE_NAME
import com.ybennun.mydiary.data.DiaryDBHelper
import kotlinx.android.synthetic.main.activity_new_diary.*
import java.text.SimpleDateFormat
import java.util.*


class NewDiary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_diary)

        val currentDate = SimpleDateFormat("EEE,d MMM yyyy")

        current_date_diary.text = currentDate.format(Date())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.action_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_diary -> {
                insertDiary()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun insertDiary() {
        val dateString = current_date_diary.text.toString()
        val titleString = title_diary.text.toString().trim() { it <= ' '}
        val diaryString = diary_text.text.toString().trim() { it <= ' '}

        val mDBHelper = DiaryDBHelper(this)

        val db = mDBHelper.writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_DATE,dateString)
            put(COLUMN_TITLE,titleString)
            put(COLUMN_DIARY,diaryString)
        }

        val rowId = db.insert(TABLE_NAME,null,values)

        if(rowId.equals(-1)){
            Toast.makeText(this,"problem in inserting new diary",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"diary has been inserted $rowId",Toast.LENGTH_SHORT).show()
        }
    }
}