package com.example.kuver.ui.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kuver.data.model.SubCatResponseModel
import com.example.kuver.data.model.SubCategories
import com.example.kuver.databinding.SubCatRowlayoutBinding

class SubCatAdapter(

    var context: Context?,
    var items: SubCatResponseModel,
) : RecyclerView.Adapter<SubCatAdapter.ViewHolder?>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =
            SubCatRowlayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindView(items.base_path, items!!.category!!.subcategories!!.get(position), context)
    }

    override fun getItemCount(): Int {
        return items.category?.subcategories?.size!! ?: 0
    }

    class ViewHolder(itemView: SubCatRowlayoutBinding) : RecyclerView.ViewHolder(itemView.root) {
        private var binding = itemView

        fun bindView(basePath: String, get: SubCategories, context: Context?) {

            Glide.with(context!!)
                .load(basePath + get.image)
                .into(binding.subCatIcon)

        }
    }

    fun setSubList(items: ArrayList<SubCategories>?) {
        this.items.category?.subcategories = items
        notifyDataSetChanged()
    }

}