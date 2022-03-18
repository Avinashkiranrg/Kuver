package com.example.kuver.data.model

import com.squareup.moshi.Json

data class SubCatResponseModel(

    @Json(name = "status")
    val status: String = "",

    @Json(name = "category")
    val category: Category? = null,

    @Json(name = "base_path")
    val base_path: String = "",

)

data class Category(

    @Json(name = "category_id")
    val category_id: String = "",

    @Json(name = "category_name")
    val category_name: String = "",

    @Json(name = "subcategories")
    var subcategories: ArrayList<SubCategories>? = null

)

data class SubCategories(

    @Json(name = "sub_category_id")
    val sub_category_id: String = "",

    @Json(name = "sub_categorie_name")
    val sub_categorie_name: String = "",

    @Json(name = "image")
    val image: String = "",

)