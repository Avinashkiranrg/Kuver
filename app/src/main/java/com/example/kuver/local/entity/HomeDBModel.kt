package com.example.kuver.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HomeDBModel(

    @PrimaryKey val id: String,
    @ColumnInfo(name = "user_id") val user_id: String,

    @ColumnInfo(name = "category_id")
    val category_id: String = "",

    @ColumnInfo(name = "sub_cat_id")
    val sub_cat_id: String = "",

    @ColumnInfo(name = "post_type")
    val post_type: String = "",

    @ColumnInfo(name = "item_condition_id")
    val item_condition_id: String = "",

    @ColumnInfo(name = "color_id")
    val color_id: String = "",

    @ColumnInfo(name = "size_id")
    val size_id: String = "",

    @ColumnInfo(name = "height")
    val height: String = "",

    @ColumnInfo(name = "brest")
    val brest: String = "",

    @ColumnInfo(name = "waist")
    val waist: String = "",

    @ColumnInfo(name = "tight")
    val tight: String = "",

    @ColumnInfo(name = "sales_rental")
    val sales_rental: String = "",

    @ColumnInfo(name = "city_id")
    val city_id: String = "",

    @ColumnInfo(name = "latitude")
    val latitude: String = "",

    @ColumnInfo(name = "longitude")
    val longitude: String = "",

    @ColumnInfo(name = "location")
    val location: String = "",

    @ColumnInfo(name = "note")
    val note: String = "",

    @ColumnInfo(name = "cover_image")
    val cover_image: String = "",

    @ColumnInfo(name = "posting_status")
    val posting_status: String = "",

    @ColumnInfo(name = "status")
    val status: String = "",

    @ColumnInfo(name = "delete_status")
    val delete_status: String = "",

    @ColumnInfo(name = "admin_commission")
    val admin_commission: String = "",

    @ColumnInfo(name = "date")
    val date: String = "",

    @ColumnInfo(name = "created_date")
    val created_date: String = "",

    @ColumnInfo(name = "item_date")
    val item_date: String = "",

    @ColumnInfo(name = "category_name")
    val category_name: String = "",

    @ColumnInfo(name = "sub_category_name")
    val sub_category_name: String = "",

    @ColumnInfo(name = "city_name")
    val city_name: String = "",

    @ColumnInfo(name = "username")
    val username: String = "",

    @ColumnInfo(name = "profile_pic")
    val profile_pic: String = "",

    @ColumnInfo(name = "item_condition")
    val item_condition: String = "",

    @ColumnInfo(name = "time")
    val time: String = "",

    @ColumnInfo(name = "product_images")
    val product_images: List<ProductImages>? = null


)

data class ProductImages(
    @ColumnInfo(name = "product_id")
    val product_id: String = "",

    @ColumnInfo(name = "image_id")
    val image_id: String = "",

    @ColumnInfo(name = "images")
    val images: String,
)

