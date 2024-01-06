package com.example.intentexplicit11092023

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import androidx.activity.result.contract.ActivityResultContracts
import kotlin.random.Random

class ListAnimalActivity : AppCompatActivity() {

    private lateinit var tableLayout: TableLayout
    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_animal)

        tableLayout = findViewById(R.id.table_layout)

        if (intent != null) {
//            val text = intent.getStringExtra("text")
//            val number = intent.getIntExtra("number", -1)
//            val stringArray = intent.getStringArrayExtra("string_array")
//            val stringArrayList = intent.getStringArrayListExtra("string_array_list")
//
//            Log.d("pphat", "Text: $text")
//            Log.d("pphat", "Number: $number")
//            Log.d("pphat", "Array: ${stringArray?.get(0)}")
//            Log.d("pphat", "Array: ${stringArrayList?.get(0)}")

            val arrAnimals = intent.getStringArrayExtra("array_animals")
            Log.d("pphat", arrAnimals?.size.toString() )
            val numberOfColumns = 3
            val numberOfRows = arrAnimals?.size?.div(numberOfColumns)

            for (i in 0 until numberOfRows!!) {
                val tableRow = TableRow(this)
                tableRow.layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )

                for (j in 0 until numberOfColumns) {
                    val imageView = ImageView(this)
                    imageView.layoutParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                    )
                    // lấy tên (nameDrawable) tại vị trí index của array
                    var nameDrawable = arrAnimals?.get(1)
                    // hàm getIdentifier để trả về imageResource gốc trong thư mục drawable
                    var imageDrawable = resources.getIdentifier(nameDrawable, "drawable", packageName)
                    // Set image resource
                    imageView.setImageResource(imageDrawable)
                    tableRow.addView(imageView)
                }
                tableLayout.addView(tableRow)
            }
        }
    }
}