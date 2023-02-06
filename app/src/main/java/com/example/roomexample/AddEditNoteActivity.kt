package com.example.roomexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.roomexample.databinding.ActivityAddEditNoteBinding

class AddEditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditNoteBinding

    lateinit var viewModal: NoteViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)//back button ko'rsatiw uchun
        title = "New User"

        viewModal = ViewModelProvider(this)[NoteViewModal::class.java]

        initBtn()
    }

    private fun initBtn() {
        binding.saveBtn.setOnClickListener {
            val name =  binding.idEdtNoteName.text.toString()
            val desc = binding.idEdtNoteDesc.text.toString()
            viewModal.insertUser(UsersModel(name, desc))
            finish()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}