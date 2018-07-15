package com.zack.roomwordsample.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import android.arch.persistence.db.SupportSQLiteDatabase
import android.support.annotation.NonNull



/**
 * Created by zack on 7/15/18.
 */
@Database(entities = arrayOf(Word::class) , version = 1)
abstract class WordRoomDatabase: RoomDatabase() {
    abstract fun wordDao():WordDao

    companion object {
        private var instance:WordRoomDatabase ?= null

         fun getInstance(context: Context):WordRoomDatabase? {
            return instance ?: synchronized(this){
                instance ?:buildDatabase(context)
            }
        }

        private fun buildDatabase(context: Context):WordRoomDatabase{
            return Room.databaseBuilder(context,
                    WordRoomDatabase::class.java, "word_database" )
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback)
                    .build()
        }

        /**
         * Override the onOpen method to populate the database.
         * For this sample, we clear the database every time it is created or opened.
         */
        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                PopulateDbAsync(instance).execute()
            }
        }


    }


    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private class PopulateDbAsync internal constructor(db: WordRoomDatabase?) : AsyncTask<Void, Void, Void>() {

        private var mDao: WordDao? = null

        init {
            if (db != null) {
                mDao = db.wordDao()
            }
        }

        override fun doInBackground(vararg params: Void): Void? {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao?.deleteAll()

            var word = Word("Hello")
            mDao?.insert(word)
            word = Word("World")
            mDao?.insert(word)
            return null
        }
    }


}