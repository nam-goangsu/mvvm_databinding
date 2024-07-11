package com.example.test_room.ui.home

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

class HomeViewModel(application: Application) : AndroidViewModel(application) {
     val readAllData : LiveData<List<Userdata>> // 전체 데이터
    private val repository : defaultuserRepositiry
    val setInput_model_text :String = "test"

    private val _text1 = MutableLiveData("Hello from include 1")
    val text1: LiveData<String> = _text1

    private val _text2 = MutableLiveData("Hello from include 2")
    val text2: LiveData<String> = _text2

    private val _text3 = MutableLiveData("Hello from include 3")
    val text3: LiveData<String> = _text3


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

    fun getList() :LiveData<List<Userdata>>{
        return readAllData
    }



    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}