package com.example.ut4_playjuegosv2

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ut5_playjuegos.ui.theme.NaranjaDark

var puntuaJuego: String = ""

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun preferences() {
    //val scaffoldState = rememberScaffoldState()
    var estadoRadio by rememberSaveable { mutableStateOf("No has pulsado ninguna opción") }
    var puntuacion by remember { mutableStateOf(50f) }
    var corutineScope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = { MyFAB(estadoRadio) },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )//{, contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                "Elige una opción:"
            )
            Spacer(modifier = Modifier.size(20.dp))
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    //var estadoRadio by rememberSaveable { mutableStateOf("opción 1") }
                    MyRadioButton(estadoRadio) { estadoRadio = it }
                }
            }
        }
    }
}


@Composable
fun MyRadioButton(name: String, onItemSelected: (String) -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .height(20.dp)
    ) {
        Row() {
            RadioButton(
                selected = name == "Angry Birds",
                onClick = {onItemSelected("Angry Birds") }
            )
            Text(text = "Angry Birds", Modifier.padding(top = 12.dp))
        }
        Row() {
            RadioButton(
                selected = name == "Dragon Fly",
                onClick = { onItemSelected("Dragon Fly") })
            Text(text = "Dragon Fly", Modifier.padding(top = 12.dp))
        }
        Row() {
            RadioButton(
                selected = name == "Hill Climbing Racing",
                onClick = { onItemSelected("Hill Climbing Racing") })
            Text(text = "Hill Climbing Racing", Modifier.padding(top = 12.dp))
        }
        Row() {
            RadioButton(
                selected = name == "Pocket Soccer",
                onClick = { onItemSelected("Pocket Soccer") })
            Text(text = "Pocket Soccer", Modifier.padding(top = 12.dp))
        }
        Row() {
            RadioButton(
                selected = name == "Radiant Defense",
                onClick = { onItemSelected("Radiant Defense") })
            Text(text = "Radiant Defense", Modifier.padding(top = 12.dp))
        }
        SimpleDiscreteSlider()
    }
}

@Composable
fun SimpleDiscreteSlider() {
    val range = 0f..10f
    val steps = 8
    var selection by remember { mutableStateOf(5f) }
    var completeValule by remember { mutableStateOf("5") }
    Column {
        Slider(
            value = selection,
            valueRange = range,
            steps = steps,
            onValueChange = { selection = it },
            onValueChangeFinished = {completeValule=selection.toInt().toString()}
        )
        puntuaJuego=completeValule
    }

}
@Composable
fun MyFAB(seleccion: String) {
    var context = LocalContext.current

    FloatingActionButton(
        onClick = {
            if (seleccion != "No has pulsado ninguna opción") {
                Toast.makeText(
                    context,
                    "Has seleccionado " + seleccion + " con una puntuación de " + puntuaJuego,
                    Toast.LENGTH_LONG
                )
                    .show()
            } else {
                Toast.makeText(context, seleccion, Toast.LENGTH_LONG)
                    .show()
            }
        },
        containerColor = NaranjaDark
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
    }
}