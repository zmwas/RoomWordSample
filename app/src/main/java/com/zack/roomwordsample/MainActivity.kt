package com.zack.roomwordsample

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
import com.zack.roomwordsample.adapter.WordListAdapter
import android.support.v7.widget.RecyclerView
import com.zack.roomwordsample.data.Word
import com.zack.roomwordsample.viewmodel.WordViewModel
import android.widget.Toast
import android.content.Intent


class MainActivity : AppCompatActivity() {
    private var mWordViewModel: WordViewModel? = null
    private var recyclerView:RecyclerView? = null
    private var wordListAdapter:WordListAdapter? = null
    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        recyclerView = findViewById(R.id.recyclerview)
        wordListAdapter = WordListAdapter(this, arrayListOf())
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = wordListAdapter
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        mWordViewModel!!.allWords().observe(this, Observer { words -> wordListAdapter!!.setWords(words!!) })
        val intent = Intent(this@MainActivity, NewWordActivity::class.java)

        fab.setOnClickListener{startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)}



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val word = Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY))
            mWordViewModel?.insert(word)
        } else {
            Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show()
        }
    }

}

