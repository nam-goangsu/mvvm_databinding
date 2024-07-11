package com.example.test_room.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.test_room.databinding.FragmentNotificationsBinding


class NotificationsFragment : Fragment() {
    private val httpurlstring = "http://www.opinet.co.kr/api/avgAllPrice.do?out=xml&code=F220207019"


    private val viewModel: NotificationsViewModel by viewModels()
    private var _binding: FragmentNotificationsBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        binding.apply {
            okhttp = viewModel
            lifecycleOwner = this@NotificationsFragment
        }



/*        val textView  = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return binding.root
  /*      CoroutineScope(Dispatchers.IO).launch {
            coroutine()
        }*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.responseText.observe(viewLifecycleOwner, Observer { response ->
            binding.textNotifications.text = response
        })
        viewModel.makeNetworkRequest(httpurlstring)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
/*

    fun coroutine() {

            val client = OkHttpClient.Builder().build()
            val req = Request.Builder().url(httpurlstring).build()
            client.newCall(req).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d("opinet",response.body!!.string())
                        CoroutineScope(Dispatchers.Main).launch {
                            val textView = binding.textNotifications
                            textView.text = response.body!!.string()
                            Log.d("opinet",response.body!!.string())
                        }
                }
            })
    }
*/


}