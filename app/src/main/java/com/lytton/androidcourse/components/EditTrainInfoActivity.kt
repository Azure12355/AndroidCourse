package com.lytton.androidcourse.components

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lytton.androidcourse.R
import com.lytton.androidcourse.base.activity.BaseViewModelActivity
import com.lytton.androidcourse.databinding.ActivityEditTrainInfoBinding
import com.lytton.androidcourse.entity.TrainInfo

class EditTrainInfoActivity : BaseViewModelActivity<ActivityEditTrainInfoBinding>() {
    private lateinit var editTrainName: EditText
    private lateinit var editTrainStart: EditText
    private lateinit var editTrainEnd: EditText
    private lateinit var editTrainPrice: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        editTrainName = binding.editTrainName
        editTrainStart = binding.editTrainStart
        editTrainEnd = binding.editTrainEnd
        editTrainPrice = binding.editTrainPrice
        
        val buttonSave: Button = binding.buttonSave

        // 获取传递的数据
        val trainInfo: TrainInfo? = intent.getParcelableExtra("trainInfo")
        trainInfo?.let {
            editTrainName.setText(it.name)
            editTrainStart.setText(it.start)
            editTrainEnd.setText(it.end)
            editTrainPrice.setText(it.price.toString())
        }

        buttonSave.setOnClickListener {
            val updatedTrainInfo = TrainInfo(
                editTrainName.text.toString(),
                editTrainStart.text.toString(),
                editTrainEnd.text.toString(),
                editTrainPrice.text.toString().toDoubleOrNull() ?: 0.0
            )

            val resultIntent = Intent().apply {
                putExtra("updatedTrainInfo", updatedTrainInfo)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}