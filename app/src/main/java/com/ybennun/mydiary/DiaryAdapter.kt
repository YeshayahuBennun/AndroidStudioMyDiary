package com.ybennun.mydiary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ybennun.mydiary.data.Diary


class DiaryAdapter(private var diaryList: MutableList<Diary>) :
    RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): DiaryViewHolder {
        val context = viewGroup.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false

        val view = inflater.inflate(
            R.layout.recycler_diary_item,
            viewGroup,
            shouldAttachToParentImmediately
        )

        return DiaryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diaryList.size
    }

    override fun onBindViewHolder(holder: DiaryAdapter.DiaryViewHolder, position: Int) {
        val item = diaryList[position]
        holder.bindDiary(item)
    }

    class DiaryViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View
        private lateinit var diary: Diary
        private var date: TextView
        private var title: TextView

        override fun onClick(v: View?) {

        }

        init {
            view = v

            date = view.findViewById(R.id.date_recycler_item)
            title = view.findViewById(R.id.title_recycler_item)
            v.setOnClickListener(this)
        }

        fun bindDiary(diary: Diary) {
            this.diary = diary
            date.text = diary.date
            title.text = diary.title
        }
    }
}