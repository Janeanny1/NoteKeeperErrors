package com.example.notekeepererrors

import DataManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.notekeepererrors.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private var notePosition = POSITION_NOTE_SET

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // setContentView(R.layout.activity_main)

        val view = binding.root
        setContentView(view)


        val adapterCourses = ArrayAdapter<CourseInfo>(this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.includedContent.spinnerCourses.adapter = adapterCourses

        notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOTE_SET) ?:
            intent.getIntExtra(NOTE_POSITION, POSITION_NOTE_SET)

        if (notePosition != POSITION_NOTE_SET)
            displayNote()
        else {
            DataManager.notes.add(NoteInfo())
            notePosition = DataManager.notes.lastIndex
        }

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(NOTE_POSITION, notePosition)
    }

    private fun displayNote() {

        val note = DataManager.notes[notePosition]
        binding.includedContent.textNoteTitle.setText(note.title)
        binding.includedContent.textNoteText.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        binding.includedContent.spinnerCourses.setSelection(coursePosition)
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
            R.id.action_settings -> {
                true
            }
            R.id.action_next -> {
                moveNext()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun moveNext() {
        ++notePosition
        displayNote()
        invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (notePosition >= DataManager.notes.lastIndex) {
            val menuItem = menu.findItem(R.id.action_next)
            if (menuItem != null) {
                menuItem.icon = getDrawable(R.drawable.ic__block_white_24)
                menuItem.isEnabled = false
            }
            }


            return super.onPrepareOptionsMenu(menu)
        }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val note = DataManager.notes [notePosition]
        note.title = binding.includedContent.textNoteTitle.text.toString()
        note.text = binding.includedContent.textNoteText.text.toString()
        note.course = binding.includedContent.spinnerCourses.selectedItem as CourseInfo
    }

}
