package com.example.intentexplicit11092023

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var imgRandom: ImageView
    private lateinit var imgUserSelect: ImageView
    private lateinit var tvScore: TextView
    private var drawableRandom: Int = 0
    private val arrAnimals by lazy {
        resources.getStringArray(R.array.array_animals)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ánh xạ view vào biến
        initView()
        event()
        // hàm tạo hình random
        makeImageRandom(imgRandom, arrAnimals)
    }

    private fun event() {
        imgUserSelect.setOnClickListener {
            var intent = Intent(this@MainActivity, ListAnimalActivity::class.java)
            intent.putExtra("array_animals", arrAnimals)
            activityResultLauncher.launch(intent)
        }
    }

    // function để get data về sau khi send
    private val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK && result.data != null) {
            val drawableUserSelect = result.data?.getIntExtra("drawable", -1 ) ?: return@registerForActivityResult

            if (drawableUserSelect != -1) {
                imgUserSelect.setImageResource(drawableUserSelect)
            }

            if (drawableUserSelect == drawableRandom) {
                Toast.makeText(this@MainActivity, "Bạn đã chọn chính xác", Toast.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed({
                    makeImageRandom(imgRandom, arrAnimals)
                }, 1000)
            } else {
                Toast.makeText(this@MainActivity, "Bạn đã chọn sai", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun makeImageRandom(imageView: ImageView, arrayName: Array<String>) {
        // lấy index là 1 số random trong size của array
        val index = Random.nextInt(arrayName.size)
        // lấy tên (nameDrawable) tại vị trí index của array
        val nameDrawable = arrayName[index]
        // lấy imageResource gốc trong Drawable từ nameDrawable
        drawableRandom = DrawableUtils.getImageResource(nameDrawable, this@MainActivity)
        // set lại imageResource của imageView
        imageView.setImageResource(drawableRandom)
    }



    private fun initView() {
        imgRandom = findViewById(R.id.image_view_random)
        imgUserSelect = findViewById(R.id.image_view_user_select)
        tvScore = findViewById(R.id.text_view_score)
    }

    private fun sendDataViaIntent() {
        val intent = Intent(this@MainActivity, ListAnimalActivity::class.java)
        intent.putExtra("text", "Hello")
        intent.putExtra("number", 10)
        intent.putExtra("string_array", arrayOf("Hello", "world"))
        intent.putExtra("string_array_list", arrayListOf("world", "hello"))
//            intent.putStringArrayListExtra("string_mutable_list", mutableListOf("android", "kotlin"))
        startActivity(intent)
    }
}