package com.example.littlelemonapplication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetworkData(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("price")
    val price: Int,

    @SerialName("description")
    val description:String,

    @SerialName("category")
    val category:String,

    @SerialName("image")
    val image:String
) {
    fun toMenuItemRoom() = MenuItemRoom(
        id, title, price, description,category,image
    )
}