package com.example.bitfit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity() {

    private val newFoodActivityRequestCode = 1
    private lateinit var itemViewModel: ItemViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rvFood)
        val adapter = FoodItemAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

        itemViewModel.allItems.observe(this, Observer { food ->
            food?.let { adapter.setFood(it) }
        })

        findViewById<Button>(R.id.addNewRecord).setOnClickListener{
            val intent = Intent(this, AddFood::class.java)
            startActivityForResult(intent, newFoodActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newFoodActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val food = FoodItem(0,data.getStringExtra(AddFood.EXTRA_FOOD), data.getStringExtra(AddFood.EXTRA_CALORIES)?.toInt())
                itemViewModel.insert(food)
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Not saved",
                Toast.LENGTH_LONG
            ).show()
        }
    }



}