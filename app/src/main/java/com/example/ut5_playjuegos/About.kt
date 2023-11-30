package com.example.ut4_playjuegosv2

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ut4_playjuegosv2.ui.theme.Users
import com.example.ut5_playjuegos.R
import com.example.ut5_playjuegos.ui.theme.tealA100

fun getUsers(): List<Users> {
    return listOf(
        Users("María Mata", 2014, R.drawable.photo),
        Users("Antonio Sanz", 2056, R.drawable.photo1),
        Users("Carlos Pérez", 5231, R.drawable.photo2),
        Users("Beatriz Martos", 1892, R.drawable.photo3),
        Users("Sandra Alegre", 3400, R.drawable.photo4),
        Users("Alex Serrat", 5874, R.drawable.photo5),
        Users("Ana Peris", 2238, R.drawable.photo6),
        Users("Miguel Beltrán", 3000, R.drawable.photo7)
    )
}

@Composable
fun UserItem(useritem: Users, onItemSelected: (Users) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth().background(color = tealA100)
            .clickable { onItemSelected(useritem) }) {
        Image(
            painter = painterResource(id = useritem.photo),
            contentDescription = useritem.nombre,
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 8.dp),
            contentScale = ContentScale.Inside
        )
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = useritem.nombre,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Puntos: " + useritem.puntuacion.toString())
        }
    }
}

@Composable
fun UsersView() {
    var context = LocalContext.current
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(top = 5.dp)
    ) {
        items(getUsers()) { useritem ->
            UserItem(useritem = useritem) {
                Toast.makeText(
                    context, useritem.nombre, Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
