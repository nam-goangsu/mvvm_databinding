package com.example.test_room.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.test_room.room.bo.CountGas
import kotlinx.coroutines.flow.Flow

@Dao
interface gasinfodao {
    @Insert
    suspend fun insert(countgas: CountGas)
    @Update
    suspend fun update(countgas: CountGas)
    @Delete
    suspend fun delete(countgas: CountGas)

    @Query("SELECT * FROM CountGas WHERE bikename ORDER BY dataDay DESC")
    fun get_gaslist(bikename: String): Flow<MutableList<CountGas>>

    @Query("select afterOdd from CountGas WHERE bikename ORDER BY dataDay DESC LIMIT 1")
    fun get_lastfule(bikename: String): Flow<MutableList<Int>>
    // 특정 ID로 저정된 주유 기록 중에 가장마지막에 넣은

    @Query("DELETE FROM CountGas WHERE uid")
    fun del_inputgas(uid: Int )


    @Query(
        " SELECT CountGas.afterOdd FROM Userdata a  LEFT JOIN CountGas ON  a.bikename = CountGas.bikename WHERE " +
                "( CountGas.beforeOdd IS NULL AND CountGas.bikename = :bikename AND " +

                " ( CountGas.dataDay > convert(char(7),getdate(),120)+'-01') AND" +
                " ( convert(char(8),DATEADD(month,+1,getdate()),121)+'-01' >  CountGas.dataDay )"+
                ") ORDER BY CountGas.dataDay DESC"
    )
// 이번달 정보
    fun get_defauladdtlist(bikename: String): Flow<Int>

}