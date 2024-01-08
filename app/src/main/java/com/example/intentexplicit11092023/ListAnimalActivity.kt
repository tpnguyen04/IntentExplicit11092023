package com.example.intentexplicit11092023

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TableRow.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil
import kotlin.math.roundToInt


class ListAnimalActivity : AppCompatActivity() {

    private lateinit var tableLayout: TableLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_animal)

        tableLayout = findViewById(R.id.table_layout)

        if (intent != null) {
            val arrAnimals = intent.getStringArrayExtra("array_animals")

            if (arrAnimals.isNullOrEmpty()) return

            // shuffle cho arrAnimals
            arrAnimals.shuffle()

            val colum = 3
            var row = ceil(arrAnimals.size.toDouble() / colum)
            Log.d("ntp1", arrAnimals.size.toDouble().toString())
            Log.d("ntp1", row.toString())
            var index = 0
            var drawable = -1

            val imageSize = calculateImageSizeFromWidthScreen(colum)

            for (indexRow in 0 until row.toInt()) {
                val tableRow = TableRow(this)
                for (indexColumn in 0 until colum) {
//                    index = indexRow * colum + indexColumn
                    drawable = DrawableUtils.getImageResource(arrAnimals[index], this)
                    val imageView = ImageView(this).apply {
                        setImageResource(drawable)
                        tag = drawable
                        layoutParams = LayoutParams(imageSize, imageSize)

                    }
                    imageView.setOnClickListener {
                            val intent = Intent(this@ListAnimalActivity, MainActivity::class.java)
                            intent.putExtra("drawable", it.tag.toString().toInt())
                            setResult(RESULT_OK, intent)
                            finish()
                    }
                    tableRow.addView(imageView)
                    if (index == (arrAnimals.size - 1)) {
                        tableLayout.addView(tableRow)
                        return
                    } else {
                        index ++
                    }
                }
                tableLayout.addView(tableRow)
            }
        }
    }

    private fun calculateImageSizeFromWidthScreen(numberColum: Int): Int {
        val displayMetrics = resources.displayMetrics
        return displayMetrics.widthPixels.div(numberColum)
    }

    private fun getDataIntent() {
        val text = intent.getStringExtra("text")
        val number = intent.getIntExtra("number", -1)
        val arrayString = intent.getStringArrayExtra("array_string")
        val arrayListString = intent.getStringArrayListExtra("array_list_string")
//            val person: Person = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                intent.getSerializableExtra("object", Person("").javaClass) as Person
//            } else {
//                intent.getSerializableExtra("object") as Person
//            }
//
//            val listPerson: ArrayList<Person> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                intent.getSerializableExtra("list_object", arrayListOf(Person("")).javaClass) as ArrayList<Person>
//            } else {
//                intent.getSerializableExtra("list_object") as ArrayList<Person>
//            }

        val person: Person? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("object", Person::class.java)
        } else {
            intent.getParcelableExtra("object")
        }

        val listPerson: ArrayList<Person>? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayListExtra("list_object", Person::class.java)
        } else {
            intent.getParcelableArrayListExtra("list_object")
        }

        val bundleFromIntent = intent.getBundleExtra("bundle")
        val textFromBundle = bundleFromIntent?.getString("text_bundle")

        Log.d("pphat", "Text: $text")
        Log.d("pphat", "Number: $number")
        Log.d("pphat", "Array String: ${arrayString?.get(0)}")
        Log.d("pphat", "Array List String: ${arrayListString?.get(0)}")
        Log.d("pphat", "Object: ${person?.name}")
        Log.d("pphat", "ArrayList Object: ${listPerson?.size}")
        Log.d("pphat", "Bundle: $textFromBundle")
    }
}