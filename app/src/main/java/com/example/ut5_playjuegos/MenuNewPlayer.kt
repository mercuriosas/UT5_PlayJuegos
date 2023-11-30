package com.example.ut4_playjuegosv2

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ut5_playjuegos.MyTopAppBarCenter
import com.example.ut5_playjuegos.R
import com.example.ut5_playjuegos.ui.theme.AzulTransp
import com.example.ut5_playjuegos.ui.theme.NaranjaLight
import com.example.ut5_playjuegos.ui.theme.NaranjaSec
import com.example.ut5_playjuegos.ui.theme.tealA100


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MenuNewPlayer() {
    var EstadoNombre by remember { mutableStateOf("") }
    var EstadoApellido by remember { mutableStateOf("") }
    var EstadoNick by remember { mutableStateOf("") }
    var EstadoTelf by remember { mutableStateOf("") }
    var EstadoMail by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) } // Variable para el control de errores
    var pesoH = 3f
    var pesoV = 1f
    Scaffold(
        // Barra superior
        topBar = {MyTopAppBarSmall("New Player") },
        // Barra inferior

        bottomBar = { },

        // Botón flotante personalizado
        floatingActionButton = { ExtendedFloatingActionButton(
            onClick = { nameError = EstadoNombre.isBlank() },
            icon = { Icon(Icons.Filled.AccountCircle, "Add new player") },
            text = { Text(text = "Add new player") },
            containerColor = tealA100
        )},
        floatingActionButtonPosition = FabPosition.Center,

        //Snackbar
        snackbarHost = { },

        // Contenido principal
        content = { paddingValues ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        //.padding(paddingValues)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.size(10.dp))
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row() {
                            Image(
                                painter = painterResource(R.drawable.account),
                                contentDescription = "User",
                                Modifier
                                    .size(60.dp)
                                    .weight(pesoV)
                            )
                            //Spacer(modifier = Modifier.size(20.dp))
                            TextField(
                                value = EstadoNombre,
                                onValueChange = {
                                    EstadoNombre = it
                                    nameError = false
                                },
                                Modifier.weight(pesoH),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = AzulTransp,
                                    focusedIndicatorColor = NaranjaLight
                                ),
                                isError = nameError,
                                label = { Text(text = ("Nombre")) },
                                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                            )

                        }
                        Row {
                            val assistiveElementText =
                                if (nameError) "Error: Obligatorio" else "*Obligatorio" // 4
                            val assistiveElementColor = if (nameError) { // 5
                                MaterialTheme.colorScheme.error
                            } else {
                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                            }
                            Spacer(Modifier.weight(pesoV))
                            Text(
                                text = assistiveElementText,
                                color = assistiveElementColor,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.weight(pesoH)
                            )
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        Row() {
                            Spacer(
                                modifier = Modifier
                                    .weight(pesoV)
                                //.size(80.dp)
                            )
                            TextField(
                                value = EstadoApellido,
                                onValueChange = { EstadoApellido = it },
                                Modifier
                                    .weight(pesoH),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = AzulTransp,
                                    focusedIndicatorColor = NaranjaLight
                                ),
                                //.width(280.dp),
                                label = {
                                    Text("Apellidos")
                                },
                                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.size(20.dp))
                        Row() {
                            Spacer(
                                modifier = Modifier
                                    .weight(pesoV)
                            )
                            MyDropDownMenu(pesoH)
                        }
                        Spacer(Modifier.size(20.dp))
                        Row() {
                            Spacer(
                                modifier = Modifier
                                    .weight(1f)
                            )
                            Image(
                                painter = painterResource(R.drawable.android),
                                contentDescription = "Carga",
                                Modifier
                                    .size(100.dp)
                                    .weight(1f)
                                //.weight(pesoV)
                            )
                            Button(
                                onClick = { },
                                // colors = ButtonDefaults.buttonColors(background = NaranjaSec),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = NaranjaSec,
                                    contentColor = Color.Black
                                ),
                                modifier = Modifier
                                    .width(100.dp)
                                    .weight(1f)
                            ) {
                                Text(text = "Change")
                            }
                        }
                        Spacer(Modifier.size(20.dp))
                        Row() {
                            Image(
                                painter = painterResource(R.drawable.camera),
                                contentDescription = "Cámara",
                                Modifier
                                    .size(60.dp)
                                    .weight(pesoV)
                            )
                            // Spacer(modifier = Modifier.size(20.dp))
                            TextField(
                                value = EstadoTelf,
                                onValueChange = { EstadoTelf = it },
                                Modifier
                                    .weight(pesoH),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = AzulTransp,
                                    focusedIndicatorColor = NaranjaLight
                                ),
                                //.width(280.dp),
                                label = {
                                    Text("Teléfono")
                                },
                                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                            )
                        }
                        Spacer(Modifier.size(20.dp))
                        Row() {
                            Image(
                                painter = painterResource(R.drawable.email),
                                contentDescription = "Cámara",
                                Modifier
                                    .size(60.dp)
                                    .weight(pesoV)
                            )
                            TextField(
                                value = EstadoMail,
                                onValueChange = { EstadoMail = it },
                                Modifier
                                    .weight(pesoH),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = AzulTransp,
                                    focusedIndicatorColor = NaranjaLight
                                ),
                                //.width(280.dp),
                                label = {
                                    Text("E-mail")
                                },
                                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                            )
                        }
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropDownMenu(peso: Float) {
    var selectedText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val usuarios =
        listOf("Blizzard", "Ryu578", "Kinton22", "Sasuke547", "Messi788")

    Column() {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            TextField(
                // The `menuAnchor` modifier must be passed to the text field for correctness.
                modifier = Modifier
                    .menuAnchor()
                    .weight(peso),
                readOnly = true,
                value = selectedText,
                onValueChange = {},
                label = { Text("Nick") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    containerColor = AzulTransp,
                    focusedIndicatorColor = NaranjaLight
                ),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                usuarios.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedText = selectionOption
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBarSmall(Texto:String) {
    TopAppBar(
        // Título de la barra superior
        title = {Text(text = Texto)},
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = tealA100
        ),
        navigationIcon = {
            IconButton(onClick = {
                //MyDrawerState.open()
                //Toast.makeText(contextForToast, "Nav Icon Click", Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = {
            }) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Add Items")
            }
            IconButton(onClick = {
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Add Items")
            }
            IconButton(onClick = {
            }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Add Items")
            }
        },
    )
}
