package com.example.test_room.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException

class NotificationsViewModel : ViewModel() {

    private val client = OkHttpClient()

    private val _responseText = MutableLiveData<String>()
    val responseText: LiveData<String> get() = _responseText

    fun makeNetworkRequest(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                _responseText.postValue("Network Error: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    _responseText.postValue("Unexpected code $response")
                    return
                }

                val responseData = response.body?.string()?.trim()
                _responseText.postValue(responseData ?: "No Response Data")
            }
        })
    }


 /*   private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text*/
}