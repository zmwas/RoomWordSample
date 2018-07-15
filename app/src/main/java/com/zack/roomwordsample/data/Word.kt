package com.zack.roomwordsample.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

/**
 * Created by zack on 7/15/18.
 */
@Entity(tableName = "word_table")
data class Word(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "word")
        val word:String

){
}