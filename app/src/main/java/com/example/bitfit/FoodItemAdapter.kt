package com.example.bitfit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodItemAdapter internal constructor(context: Context) : RecyclerView.Adapter<FoodItemAdapter.FoodViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var food = emptyList<FoodItem>()

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodItemView: TextView = itemView.findViewById(R.id.tvFood)
        val numCalView: TextView = itemView.findViewById(R.id.numberOfCalories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemView = inflater.inflate(R.layout.food_item, parent, false)
        return FoodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val current = food[position]
        holder.foodItemView.text = current.foodname
        holder.numCalView.text = current.calories.toString()
    }

    internal fun setFood(food: List<FoodItem>) {
        this.food = food
        notifyDataSetChanged()
    }

    override fun getItemCount() = food.size
}