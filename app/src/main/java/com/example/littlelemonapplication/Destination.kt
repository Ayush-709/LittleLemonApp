package com.example.littlelemonapplication

interface Destination {
    val route:String
}

object Home:Destination{
    override val route= "Home"
}

object Profile:Destination{
    override val route= "Profile"
}

object OnBoard:Destination{
    override val route= "onBoard"
}