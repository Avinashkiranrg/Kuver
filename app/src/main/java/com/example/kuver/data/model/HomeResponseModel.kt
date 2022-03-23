package com.example.kuver.data.model

import com.squareup.moshi.Json

data class HomeResponseModel(

    @Json(name = "status")
    val status: String = "",

    @Json(name = "message")
    val message: String = "",

    @Json(name = "total_records")
    val total_records: String = "",

    @Json(name = "total_pages")
    val total_pages: String = "",


    @Json(name = "home_page")
    val categories: ArrayList<HomePage>? = null,

    @Json(name = "base_path")
    val base_path: String = ""

)

data class HomePage(

    @Json(name = "id")
    val id: String = "",

    @Json(name = "user_id")
    val user_id: String = "",

    @Json(name = "category_id")
    val category_id: String = "",

    @Json(name = "sub_cat_id")
    val sub_cat_id: String = "",

    @Json(name = "post_type")
    val post_type: String = "",

    @Json(name = "item_condition_id")
    val item_condition_id: String = "",

    @Json(name = "color_id")
    val color_id: String = "",

    @Json(name = "size_id")
    val size_id: String = "",

    @Json(name = "height")
    val height: String = "",

    @Json(name = "brest")
    val brest: String = "",

    @Json(name = "waist")
    val waist: String = "",

    @Json(name = "tight")
    val tight: String = "",

    @Json(name = "sales_rental")
    val sales_rental: String = "",

    @Json(name = "city_id")
    val city_id: String = "",

    @Json(name = "latitude")
    val latitude: String = "",

    @Json(name = "longitude")
    val longitude: String = "",

    @Json(name = "location")
    val location: String = "",

    @Json(name = "note")
    val note: String = "",

    @Json(name = "cover_image")
    val cover_image: String = "",

    @Json(name = "posting_status")
    val posting_status: String = "",

    @Json(name = "status")
    val status: String = "",

    @Json(name = "delete_status")
    val delete_status: String = "",

    @Json(name = "admin_commission")
    val admin_commission: String = "",

    @Json(name = "date")
    val date: String = "",

    @Json(name = "created_date")
    val created_date: String = "",

    @Json(name = "item_date")
    val item_date: String = "",

    @Json(name = "category_name")
    val category_name: String = "",

    @Json(name = "sub_category_name")
    val sub_category_name: String = "",

    @Json(name = "city_name")
    val city_name: String = "",

    @Json(name = "username")
    val username: String = "",

    @Json(name = "profile_pic")
    val profile_pic: String = "",

    @Json(name = "item_condition")
    val item_condition: String = "",

    @Json(name = "time")
    val time: String = "",

    @Json(name = "product_images")
    val product_images: ArrayList<ProductImages>? = null


)

data class ProductImages(
    @Json(name = "product_id")
    val product_id: String = "",

    @Json(name = "image_id")
    val image_id: String = "",

    @Json(name = "images")
    val images: String = "",
)




