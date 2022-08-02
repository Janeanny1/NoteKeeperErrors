package com.example.notekeepererrors

import DataManager
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notekeepererrors.databinding.ActivityNoteListBinding


class NoteListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteListBinding


    val notesAdapter by lazy {
        ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            DataManager.notes,
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(binding.toolbar)


        binding.fab.setOnClickListener { view ->
            val activityIntent = intent(this, NoteActivity::class.java)
            startActivity(activityIntent)
        }


        listItems.layoutManager = LinearLayoutManager (this)

        listItems.adapter = NoteRecyclerAdapter(this, DataManager.notes)
    }

    private fun startActivity(activityIntent: Unit) = Unit

    protected fun intent(packageContext: NoteListActivity, java: Class<NoteActivity>) {

    }

    override fun onResume() {
        super.onResume()
        listItems.adapter.notifyDataSetChanged()

    }


}

fun ListAdapter.notifyDataSetChanged() {
    TODO("Not yet implemented")
}

