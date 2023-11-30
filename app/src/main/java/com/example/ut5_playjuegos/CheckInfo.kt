package com.example.ut4_playjuegosv2

import androidx.annotation.DrawableRes

data class CheckInfo(
    var title: String, //Nombre a mostrar
    var selected: Boolean,  //Si está o no seleccionado
    var onCheckedChange: (Boolean) -> Unit, //Función al clicar
    @DrawableRes var imagen: Int //Añadimos la foto
){

}
