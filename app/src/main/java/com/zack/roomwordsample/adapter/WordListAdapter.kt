package com.zack.roomwordsample.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zack.roomwordsample.R
import com.zack.roomwordsample.data.Word
import kotlinx.android.synthetic.main.recycler_view_item.view.*



/**
 * Created by zack on 7/15/18.
 */
class WordListAdapter(val context: Context, val words: List<Word>) : RecyclerView.Adapter<WordListAdapter.ViewHolder>() {

    var mWords:List<Word> = words

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var currentWord:Word = mWords[position]
            holder.wordText.text=currentWord.word
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context)
                .inflate(R.layout.recycler_view_item,parent,false))
    }

    override fun getItemCount(): Int {
        return mWords.size
    }

    fun setWords(words: List<Word>) {
        mWords=words
        notifyDataSetChanged()
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val wordText = itemView.textView
    }
}