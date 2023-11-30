package com.example.ut4_playjuegosv2

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ut5_playjuegos.R
import com.example.ut5_playjuegos.ui.theme.NaranjaSec

var datosCheck: MutableList<CheckInfo> = mutableListOf()

data class NombreImag(var nombre: String, var imagen: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Games() {
    //val scaffoldState = rememberScaffoldState()
    //var estadoRadio by rememberSaveable { mutableStateOf("No has pulsado ninguna opción") }
    //var puntuacion by remember { mutableStateOf(50f) }
    //var pulsado by remember { mutableStateOf("No has elegido ninguna opción ") }

    var context = LocalContext.current
    val myOptions = getOptions(
        listOf(
            "Angry Birds",
            "Dragon Fly",
            "Hill Climbing Racing",
            "Radiant Defense",
            "Pocket Soccer",
            "Ninja Jump",
            "Air Control"
        )
    )
    Scaffold(
        topBar = {},
        bottomBar = {},
        snackbarHost = {},
        floatingActionButton = { MyFABCheck(myOptions) },

        ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
        )//{, contentAlignment = Alignment.Center) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Selecciona los juegos:",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            //val myOptions = getOptions()
            //MyCheckBox(myOptions[6])
            myOptions.forEach {
                MyCheckBox(it)
            }
        }
    }
}

/*
Scaffold(
    //scaffoldState = scaffoldState,
    topBar = {
        // MyTopAppBar()
    },
    floatingActionButton = { MyFABCheck(myOptions) },
) {}
/*
Box(
    modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
)//{, contentAlignment = Alignment.Center) {

Column(
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.Start,
    modifier = Modifier
        .padding(horizontal = 15.dp)
        .verticalScroll(rememberScrollState())
) {
    Spacer(modifier = Modifier.height(10.dp))
    Spacer(modifier = Modifier.width(10.dp))


    //val myOptions = getOptions()
    //MyCheckBox(myOptions[6])
    myOptions.forEach {
        MyCheckBox(it)
    }
}

}
*/

 */
//@Preview(showBackground = true)
@Composable
fun MyCheckBox(checkInfo: CheckInfo) {
    var context = LocalContext.current
    var texto: String = ""
    Row(Modifier.padding(8.dp)) {
        when (checkInfo.title) {
            "Angry Birds" -> checkInfo.imagen = R.drawable.games_angrybirds

            "Dragon Fly" -> checkInfo.imagen = R.drawable.games_dragonfly

            "Hill Climbing Racing" -> checkInfo.imagen = R.drawable.games_hillclimbingracing

            "Radiant Defense" -> checkInfo.imagen = R.drawable.games_radiantdefense

            "Pocket Soccer" -> checkInfo.imagen = R.drawable.games_pocketsoccer

            "Ninja Jump" -> checkInfo.imagen = R.drawable.games_ninjump

            "Air Control" -> checkInfo.imagen = R.drawable.games_aircontrol
        }
        Image(
            painter = painterResource(id = checkInfo.imagen),
            contentDescription = checkInfo.title
        )
        Spacer(modifier = Modifier.size(10.dp))
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = {
                checkInfo.onCheckedChange(!checkInfo.selected)
            })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = checkInfo.title, modifier = Modifier.padding(top = 16.dp))
    }
}


@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var estadoCheck by rememberSaveable { mutableStateOf(false) }
        CheckInfo(
            title = it,
            selected = estadoCheck,
            onCheckedChange = { estadoCheck = it },
            imagen = 0
        )
    }
    /*
    Otra forma
     onCheckedChange = {MyNewStatus -> estadoCheck = MyNewStatus}
    */
}

@Composable
fun MyFABCheck(opciones: List<CheckInfo>) {
    var context = LocalContext.current
    var muestra: String = ""
    var pulsado: ArrayList<String> = arrayListOf()
    FloatingActionButton(
        onClick = {
            pulsado.clear()
            opciones.forEach {
                if (it.selected) {
                    pulsado += it.title
                }
            }
            if (pulsado.isEmpty()) {
                muestra = "No has seleccionado nigún juego "
            } else {
                muestra = "Has seleccionado "
                for (i in pulsado.indices) {
                    when (i) {
                        0 -> muestra += opciones.get(0).title
                        pulsado.size - 1 -> muestra += " y " + opciones.get(i).title
                        else -> muestra += ", " + opciones.get(i).title
                    }

                }

            }
            Toast.makeText(context, muestra, Toast.LENGTH_LONG).show()
        },
        containerColor = NaranjaSec,
        shape = CircleShape
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
    }
}
