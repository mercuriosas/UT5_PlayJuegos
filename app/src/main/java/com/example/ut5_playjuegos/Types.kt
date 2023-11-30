package com.example.ut4_playjuegosv2

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ut5_playjuegos.R
import com.example.ut5_playjuegos.ui.theme.NaranjaLight
import com.example.ut5_playjuegos.ui.theme.teal400


@Preview(showBackground = true)
@Composable
fun Types() {
    Surface {
        Column {
            Text(text = "Plataformas:", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            ChipGroupCompose()
            Text(text = "Géneros:", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            MyTypesList()
        }

        /* Scaffold(
             topBar = { MyTopAppBar(nombre = "Juegos") },
             bottomBar = {},
             snackbarHost = {},
             floatingActionButton = { },

             ) {
             Box(
                 modifier = Modifier
                     .fillMaxSize()
                     .padding(it)
                     .verticalScroll(rememberScrollState())
             ) {//{, contentAlignment = Alignment.Center) {

                 Column(
                     verticalArrangement = Arrangement.Top,
                     horizontalAlignment = Alignment.Start,
                     modifier = Modifier
                         .padding(horizontal = 15.dp)
                         .verticalScroll(rememberScrollState())
                 ) {
                     Spacer(modifier = Modifier.height(10.dp))

                 }
                 Box() {
                     Column {
                         //botones()
                         MyTypesList()
                     }
                 }
             }
         }*/
    }
}


@Composable
fun MyTypesList() {
    val tipos = listOf(
        "Aventura",
        "Acción",
        "RPG",
        "Deportes",
        "Shooter",
        "Estrategia",
        "Lucha",
        "Simulación"
    )
    Column() {
        LazyColumn(modifier = Modifier.padding(10.dp)) {
            items(tipos) {
                /*Surface(modifier = Modifier.fillMaxWidth()) {
                Text(text = "$it")
            }*/
                CreaCard(nombre = "$it")
            }
        }
    }
}

@Composable
fun CreaCard(nombre: String) {
    var imagen: Int = 0
    var descripcion: String = ""
    var context = LocalContext.current

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp).clickable {
                Toast.makeText(context, nombre, Toast.LENGTH_LONG).show()
            }
        //.height(60.dp),
        , shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = NaranjaLight)
    ) {
        Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = nombre,
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
            )
            when (nombre) {
                "Aventura" -> {
                    imagen = R.drawable.zelda
                    descripcion = "Zelda"
                }

                "Acción" -> {
                    imagen = R.drawable.spider
                    descripcion = "Marvel`s Spider-man 2"
                }

                "RPG" -> {
                    imagen = R.drawable.baldurs
                    descripcion = "Baldur's Gate 3"
                }

                "Deportes" -> {
                    imagen = R.drawable.fc24
                    descripcion = "FC 24"
                }

                "Shooter" -> {
                    imagen = R.drawable.cod
                    descripcion = "Call of Duty"
                }

                "Estrategia" -> {
                    imagen = R.drawable.civilization
                    descripcion = "Civilization"
                }

                "Lucha" -> {
                    imagen = R.drawable.foz
                    descripcion = "Dragon Ball Figther of Z"
                }

                "Simulación" -> {
                    imagen = R.drawable.flight
                    descripcion = "Microsoft Flight Simulator"
                }
            }
            Image(
                painter = painterResource(id = imagen),
                contentDescription = descripcion, Modifier.fillMaxHeight().width(180.dp)
            )
        }
    }
    Spacer(modifier = Modifier.size(5.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun FilterChipExample(opcion: String) {
    var selected by remember { mutableStateOf(false) }
    var colores: Color = NaranjaLight

    FilterChip(
        onClick = {
            selected = !selected

        },
        label = {
            Text(opcion)
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = teal400,
            labelColor = Color.White,
            selectedContainerColor = colores
        ),
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}

@Preview(showBackground = true)
@Composable
fun ChipGroupCompose() {
    val nombre: String

    val plataformas: List<String> = listOf(
        "PS5",
        "XBox X/S",
        "Switch",
        "PC",
        "Steam",
        "GeForce now"
    )

    var selectec by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            for (opcion in plataformas) {
                item {
                    FilterChipExample(opcion)
                    Spacer(Modifier.size(5.dp))
                }
            }
        }
    }
}

/*
@Composable
fun botones() {

    var colores by remember { mutableStateOf(NaranjaLight) }
    val plataformas = listOf(
        "PS5",
        "XBox X/S",
        "Switch",
        "PC",
        "Steam",
        "GeForce now"
    )
    // var colores:Color = NaranjaLight

    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(
            onClick = {
                if (colores == NaranjaLight) {
                    colores = AzulPrim
                } else {
                    colores = NaranjaLight
                }
            }, colors = ButtonDefaults.buttonColors(
                containerColor = colores,
                //contentColor = Color.Blue
            )
        ) {
            Text(text = "PS5")
        }
        Spacer(modifier = Modifier.size(5.dp))
        Button(
            onClick = {
                if (colores == NaranjaLight) {
                    colores = AzulPrim
                } else {
                    colores = NaranjaLight
                }
            }, colors = ButtonDefaults.buttonColors(
                // containerColor = AzulDark,
                //contentColor = Color.Blue
            )
        ) {
            Text(text = "XBox X/S")
        }
        Spacer(modifier = Modifier.size(5.dp))
        Button(
            onClick = {
                if (colores == NaranjaLight) {
                    colores = AzulPrim
                } else {
                    colores = NaranjaLight
                }
            }, colors = ButtonDefaults.buttonColors(
                containerColor = AzulDark,
                //contentColor = Color.Blue
            )
        ) {
            Text(text = "Switch")
        }
        Spacer(modifier = Modifier.size(5.dp))
        Button(
            onClick = {
                if (colores == NaranjaLight) {
                    colores = AzulPrim
                } else {
                    colores = NaranjaLight
                }
            }, colors = ButtonDefaults.buttonColors(
                containerColor = AzulDark,
                //contentColor = Color.Blue
            )
        ) {
            Text(text = "PC")
        }
    }
    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(
            onClick = {
                if (colores == NaranjaLight) {
                    colores = AzulPrim
                } else {
                    colores = NaranjaLight
                }
            }, colors = ButtonDefaults.buttonColors(
                containerColor = AzulDark,
                // contentColor = Color.Blue
            )
        ) {
            Text(text = "Steam")
        }
        Spacer(modifier = Modifier.size(5.dp))
        Button(
            onClick = {
                if (colores == NaranjaLight) {
                    colores = AzulPrim
                } else {
                    colores = NaranjaLight
                }
            }, colors = ButtonDefaults.buttonColors(
                containerColor = AzulDark,
                //contentColor = Color.Blue
            )
        ) {
            Text(text = "GeForce Now")
        }
    }
}
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(nombre: String) {
    TopAppBar(title = { Text(text = nombre) })
}
/*
@Composable
fun botonesAuto() {
    var i = ""
    val plataformas = listOf(
        "PS5",
        "XBox X/S",
        "Switch",
        "PC",
        "Steam",
        "GeForce now"
    )
    //Column() {
    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        while(i !="Steam"){

        }
        /*for (i in plataformas) {
            if (i != "Steam" || i != "GeForce now") {
                //Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(
                    onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Blue
                    )
                ) {
                    Text(text = i)
                }
            }
        }*/
    }
    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        for (i in plataformas) {
            if (i == "Steam" || i == "GeForce now") {
                //Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(
                    onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Blue
                    )
                ) {
                    Text(text = i)
                }
            }

        }
        /* Row(horizontalArrangement = Arrangement.SpaceEvenly) {
              Button(
                  onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                      containerColor = Color.Cyan,
                      contentColor = Color.Blue
                  )
              ) {
                  Text(text = i)
              }
          }
     */
    }
}

 */