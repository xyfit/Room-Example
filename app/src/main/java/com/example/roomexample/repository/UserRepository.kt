package com.example.roomexample.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.roomexample.room.AppDatabase
import com.example.roomexample.UsersModel
import com.example.roomexample.room.UserDao

class UserRepository(applicationContext: Application) {

    var db: UserDao = AppDatabase.getDataseClient(applicationContext).userDao()

    // Insert new user
    suspend fun insertUser(user: UsersModel) {
        db.insertUser(user)
    }

    // update user
    suspend fun updateUser(user: UsersModel) {
        db.updateUser(user)
    }

    // Delete user
    suspend fun deleteUser(user: UsersModel) {
        db.deleteUser(user)
    }

    // Delete all
    suspend fun deleteAll() {
        db.deleteAll()
    }

    // One user
    fun getOneUser(userNikName: String): LiveData<UsersModel>{
       return db.getOneUser(userNikName)
    }

    // get all data
    val getAllUsers: LiveData<List<UsersModel>> = db.getAllData()



}