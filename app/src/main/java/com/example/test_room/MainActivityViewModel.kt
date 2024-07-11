package com.example.test_room

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.test_room.room.DefaltDatabase
import com.example.test_room.room.bo.Userdata
import com.example.test_room.room.defaultuserRepositiry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
     val readAllData : LiveData<List<Userdata>> // 전체 데이터
    private val repository : defaultuserRepositiry




    init{
        val userDao = DefaltDatabase.getDataabase(application).userdao()
        repository = defaultuserRepositiry(userDao)
        readAllData = repository.defaltuser


        Log.d("database " , "data ${readAllData.value}")

    }

    fun addUser(userdata:Userdata){
        viewModelScope.launch (Dispatchers.IO){
            repository.adduser(userdata)
        }
    }





    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text1: LiveData<String> = _text
}