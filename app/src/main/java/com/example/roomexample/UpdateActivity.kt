package com.example.roomexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.roomexample.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    lateinit var viewModal: NoteViewModal
    private var getIntent: String? = null
    private var oneUserModel: UsersModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntent = intent.getStringExtra("update_key")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)//back button ko'rsatiw uchun
        title = "Update"

        viewModal = ViewModelProvider(this)[NoteViewModal::class.java]
        setEditextDbData()
        initBtn()
    }

    private fun setEditextDbData() {
        getIntent?.let { userName->
            viewModal.getOneUser(userName).observe(this){
                oneUserModel = it
                binding.idEdtNoteName.setText(it.username)
                binding.idEdtNoteDesc.setText(it.description)
            }
        }
    }

    private fun initBtn() {
        binding.updateBtn.setOnClickListener {
            val name =  binding.idEdtNoteName.text.toString()
            val desc = binding.idEdtNoteDesc.text.toString()
            oneUserModel?.let {
                val updateModel = UsersModel(name, desc)
                updateModel.Id =  it.Id /*id orqali qaysibiri ekanini aniqledi*/
                viewModal.updateUser(updateModel)/*update*/
            }
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