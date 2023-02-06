package com.example.roomexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomexample.repository.UserRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NoteViewModal(application: Application): AndroidViewModel(application) {

    private val repository: UserRepository

    init {
         repository = UserRepository(application)
    }

    fun insertUser(user: UsersModel) = viewModelScope.launch(IO) {
        repository.insertUser(user)
    }

    fun updateUser(user: UsersModel) = viewModelScope.launch(IO) {
        repository.updateUser(user)
    }

    fun deleteUser(user: UsersModel) = viewModelScope.launch(IO) {
        repository.deleteUser(user)
    }

    fun deleteAll() = viewModelScope.launch(IO) {
        repository.deleteAll()
    }

    fun getOneUser(userNikName: String): LiveData<UsersModel>{//wu fun chaqirilganda davomidan obserw ewitibketiw mumkin
        return repository.getOneUser(userNikName)
    }

    val getAllDb = repository.getAllUsers
}