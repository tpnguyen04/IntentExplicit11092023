package com.example.intentexplicit11092023

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.intentexplicit11092023.DrawableUtils.Companion.randomImageResource
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var imgRandom: ImageView
    private lateinit var imgUserSelect: ImageView
    private lateinit var tvScore: TextView
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

    }

    private fun makeImageRandom(imageView: ImageView, arrayName: Array<String>) {
        // lấy index là 1 số random trong size của array
        val index = Random.nextInt(arrayName.size)
        Log.d("ntp", index.toString())
        // lấy tên (nameDrawable) tại vị trí index của array
        val nameDrawable = arrayName[index]
        Log.d("ntp", nameDrawable.toString())
        // lấy imageResource gốc trong Drawable từ nameDrawable
        val imageResource = DrawableUtils.randomImageResource(nameDrawable, this@MainActivity)
        Log.d("ntp", imageResource.toString())
        // set lại imageResource của imageView
        imageView.setImageResource(imageResource)
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