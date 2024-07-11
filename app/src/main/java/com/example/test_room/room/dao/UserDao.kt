package com.example.test_room.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test_room.room.bo.Userdata

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)//같은 데이터가 있으면 무시
    suspend fun addUser(user:Userdata) //suspend:코루틴사용 해서 user추가

    @Query("SELECT * FROM Userdata ORDER BY uid DESC")
    fun readAllData(): LiveData<List<Userdata>>


}