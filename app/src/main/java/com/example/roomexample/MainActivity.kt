package com.example.roomexample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.adapter.ListAdapter
import com.example.roomexample.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var viewModal: NoteViewModal

    private val listAdapter by lazy { ListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModal = ViewModelProvider(this)[NoteViewModal::class.java]

        initRec()
        initBtn()
        initLivedata()

    }

    private fun initLivedata() {
        viewModal.getAllDb.observe(this){
            listAdapter.baseList = it
            Log.d("Sdfsdg", "$it")
        }
    }

    private fun initBtn() {
        binding.idFAB.setOnClickListener {
            startActivity(Intent(this, AddEditNoteActivity::class.java))
        }
    }

    private fun initRec() {
        binding.notesRV.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listAdapter
        }
        listAdapter.setOnItemClickListener{
            viewModal.deleteUser(it)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.delete_all){
            viewModal.deleteAll()
            true
        }else{
            super.onOptionsItemSelected(item)
        }

    }
}