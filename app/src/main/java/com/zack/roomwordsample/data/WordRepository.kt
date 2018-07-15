package com.zack.roomwordsample.data

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.util.Log


/**
 * Created by zack on 7/15/18.
 */
class WordRepository (
        private val application: Application
) {
    private val wordDao: WordDao
    private val db:WordRoomDatabase
    private val allWords:LiveData<List<Word>>

    init {
        db = WordRoomDatabase.getInstance(application)!!
        wordDao = db.wordDao()
        allWords = wordDao.getAllWords();
    }


    fun insertWords(word: Word){
            insertAsyncTask(wordDao).execute(word)
    }

    fun getAllWords(): LiveData<List<Word>> {
        return allWords
    }


    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: WordDao) : AsyncTask<Word, Void, Void>() {

      override fun doInBackground(vararg params: Word): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

}