package com.example.roomexample.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomexample.UsersModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(loginTableModel: UsersModel)/*malumot kiritiw*/

    @Update
    suspend fun updateUser(users: UsersModel)/*yangilash*/

    @Delete
    suspend fun deleteUser(users: UsersModel)/*bir dona userni o'chiriw*/

    @Query("DELETE FROM login_table")
    suspend fun deleteAll()/*table ni o'chirish (barcha malumotlar)*/


    @Query("SELECT * FROM login_table WHERE Username =:username")
    fun getOneUser(username: String?) : LiveData<UsersModel>/*birdona userni oliw */

    @Query("SELECT * FROM login_table")
    fun getAllData(): LiveData<List<UsersModel>>/*barcha userlarni oliw*/





}