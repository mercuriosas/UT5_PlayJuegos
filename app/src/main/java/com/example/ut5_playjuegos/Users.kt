package com.example.ut4_playjuegosv2.ui.theme

import androidx.annotation.DrawableRes

data class Users(
    var nombre: String,
    var puntuacion: Int,
    @DrawableRes var photo: Int
)