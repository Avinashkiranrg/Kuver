package com.example.kuver.ui.main.home

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kuver.data.model.Categories
import com.example.kuver.databinding.CatLayoutBinding

class CatAdapter(

    //  var context: Context,
    var items: List<Categories>?,
    private var onCategoryClick: OnCategoryClick,

    var value: Int = 0,
    var valueInt: Int = 0

) : RecyclerView.Adapter<CatAdapter.ViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = CatLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindView(items!!.get(position), position, value)

        Log.e("position", position.toString())
        if (position == 0) {
            onCategoryClick.OnItemClick(position, items!!.get(position).id)
        }
        holder.itemView.setOnClickListener {
            if (items != null) {
                value = position
                onCategoryClick.OnItemClick(position, items!!.get(position).id)
                notifyDataSetChanged()
            }
        }

        if (valueInt == 0) {
            valueInt = 1
            onCategoryClick.OnItemClick(position, items!!.get(position).id)
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    class ViewHolder(itemView: CatLayoutBinding) : RecyclerView.ViewHolder(itemView.root) {

        private var binding = itemView

        fun bindView(items: Categories, position: Int, value: Int) {

            binding.name.setText(items.category_name)

            if (position == 0) {
                binding.name.setTextColor(Color.parseColor("#FF03DAC5"))
            }
            if (value == position) {
                binding.name.setTextColor(Color.parseColor("#FF03DAC5"))
            } else {
                binding.name.setTextColor(Color.parseColor("#000000"))
            }

        }
    }

    fun setList(items: List<Categories>?) {
        this.items = items
        notifyDataSetChanged()
    }

    interface OnCategoryClick {
        fun OnItemClick(pos: Int, categoryId: String)
    }
}


