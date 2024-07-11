package com.example.test_room

import android.app.Dialog
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.test_room.databinding.BasicDailogBinding

class dialog_input(private val context : AppCompatActivity) {

    lateinit var input_model_text : String

    private lateinit var binding : BasicDailogBinding

    private val dilog = Dialog(context)
    fun show(content : String){
        binding = BasicDailogBinding.inflate(context.layoutInflater)


        dilog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(binding.root)
            setCancelable(false)
        }




        //    binding.content.text = content



    }



    init {
        input_model_text = R.string.dialog_modelname.toString()

        binding.subSub1.haednameDialog.text = R.string.dialog_modelname.toString()
        binding.subSub2.haednameDialog.text = R.string.dialog_modelname.toString()
        binding.subSub3.haednameDialog.text = R.string.dialog_modelname.toString()
        binding.subSub4.haednameDialog.text = R.string.dialog_modelname.toString()

    }




    /*binding.imgbtnDate.setOnClickListener {
        var calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)
        context?.let { it1 ->
            DatePickerDialog(it1, { _, year, month, day ->
                run {
                    binding.editTvDate.setText(year.toString() + "." + (month + 1).toString() + "." + day.toString())
                }
            }, year, month, day)
        }?.show()
    }
*/
}