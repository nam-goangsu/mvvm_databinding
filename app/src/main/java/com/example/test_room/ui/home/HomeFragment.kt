package com.example.test_room.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.test_room.databinding.FragmentHomeBinding
import com.example.test_room.room.bo.Userdata

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.apply {
            mainViewModel = viewModel
            lifecycleOwner = this@HomeFragment
        }
        val root: View = binding.root


        var tes : ArrayList<Userdata> = arrayListOf()

        val textView: TextView = binding.textHome


        homeViewModel.getList().observe(viewLifecycleOwner){
            if(it.size>0){
                textView.text ="1"
            }
            else{
                textView.text ="0"
            }
        }


/*        homeViewModel.readAllData.observe(viewLifecycleOwner){
             tes = it as ArrayList<Userdata>

            if(tes.size>0)
                textView.text = "!"
            else
                textView.text = "0"
            //textView.text = it.toString()
        }*/

        /*homeViewModel.text1.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}