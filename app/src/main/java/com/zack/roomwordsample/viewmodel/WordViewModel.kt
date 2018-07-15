package com.zack.roomwordsample.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.zack.roomwordsample.data.Word
import com.zack.roomwordsample.data.WordRepository

/**
 * Created by zack on 7/15/18.
 */
class WordViewModel internal constructor(
        application: Application
): AndroidViewModel(application) {
    private val wordRepository: WordRepository
    private val allWords:LiveData<List<Word>>


    init {
        wordRepository = WordRepository(application)
        allWords = wordRepository.getAllWords()
    }

    fun allWords():LiveData<List<Word>>{
        return allWords
    }

    fun insert(word: Word){
        wordRepository.insertWords(word)
    }
}