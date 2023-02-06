package com.example.roomexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.FileDescriptor

@Entity(tableName = "login_table")
data class UsersModel (

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "password")
    var description: String

) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

}