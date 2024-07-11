package com.example.test_room.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.test_room.room.bo.Userdata
import com.example.test_room.room.dao.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import java.util.concurrent.Flow

class defaultuserRepositiry (private val userdao : UserDao){

    val defaltuser : LiveData<List<Userdata>> = userdao.readAllData()


    @WorkerThread
    @Suppress("test")
    suspend fun adduser(userdata: Userdata){

        supervisorScope {
            val  adduser = launch {  userdao.addUser(userdata)}
            joinAll(adduser)
        }


    }

    /*fun getAlldataStream(): Flow<List<defalutUser>> =


        suspend fun insertDefaltdata(defalutUser: defalutUser)

    suspend fun deleteDefaltdata(defalutUser: defalutUser)

    suspend fun updateDefaltdata(defalutUser: defalutUser)*/
}