package com.example.ut5_playjuegos

import android.content.res.Configuration
import android.graphics.drawable.Icon
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ut4_playjuegosv2.Games
import com.example.ut4_playjuegosv2.MenuNewPlayer
import com.example.ut4_playjuegosv2.Types
import com.example.ut4_playjuegosv2.UsersView
import com.example.ut4_playjuegosv2.preferences
import com.example.ut5_playjuegos.ui.theme.TemaPersonalizado
import com.example.ut5_playjuegos.ui.theme.courgetteRegularFamily
import com.example.ut5_playjuegos.ui.theme.tealA100
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemaPersonalizado {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//MenuPlayJuegos()
                    //MenuNewPlayer()
                    //preferences()
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "PlayJuegos") {
                        composable("PlayJuegos") { MenuPlayJuegos(navController) }
                        composable("Play") { Games() }
                        composable("NewPlayer") { MenuNewPlayer() }
                        composable("Preferences") { preferences() }
                        composable("About") { UsersView() }
                        composable("Tipos") { Types() }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPlayJuegos(navController: NavHostController) {
    //Variable para la rotación de la pantalla
    val configuration = LocalConfiguration.current
    //var corutineScope = rememberCoroutineScope()
    val corutina = rememberCoroutineScope() //Corutina para el modal drawer
    val MydrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectecItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    var expanded by remember { mutableStateOf(false) }


    val itemDrawer = listOf<ModalDrawer>(
        ModalDrawer(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unslectecIcon = Icons.Outlined.Home,
        ),
        ModalDrawer(
            title = "Profile",
            selectedIcon = Icons.Filled.AccountCircle,
            unslectecIcon = Icons.Outlined.AccountCircle,
        ),
        ModalDrawer(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unslectecIcon = Icons.Outlined.Settings,
        )
    )
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                    Image(
                        painter = painterResource(id = R.drawable.googleplay),
                        contentDescription = "Movimiento"
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                itemDrawer.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = index == selectecItemIndex,
                        onClick = {
                            selectecItemIndex = index
                            corutina.launch {
                                MydrawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectecItemIndex) {
                                    item.selectedIcon
                                } else {
                                    item.unslectecIcon
                                }, contentDescription = item.title
                            )
                        },
                    )
                }
            }
        }, drawerState = MydrawerState
    ) {
        Scaffold(
            // Barra superior

            topBar = {

                CenterAlignedTopAppBar(// Título de la barra superior
                    title = {

                        Text(text = "Play Juegos")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            corutina.launch {
                                MydrawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = { expanded = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Add Items"
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Types") },
                                onClick = { navController.navigate("Tipos") },
                                leadingIcon = {
                                    Icon(
                                        Icons.Outlined.AccountBox,
                                        contentDescription = null
                                    )
                                })
                            DropdownMenuItem(
                                text = { Text("Share") },
                                onClick = { },
                                leadingIcon = {
                                    Icon(
                                        Icons.Outlined.Share,
                                        contentDescription = null
                                    )
                                })
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = tealA100
                    )
                )
            },
            // Barra inferior

            bottomBar = { },

            // Botón flotante personalizado
            floatingActionButton = { },

            //Snackbar
            snackbarHost = { },

            // Contenido principal
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.rasengan),
                            contentDescription = "Rasengan", Modifier.size(150.dp)
                        )
                        Text(
                            "Play Juegos", style = TextStyle(
                                fontSize = 50.sp,
                                fontFamily = courgetteRegularFamily,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                        when (configuration.orientation) {
                            Configuration.ORIENTATION_PORTRAIT -> {
                                Button(
                                    onClick = { navController.navigate("Play") },
                                    modifier = Modifier.width(200.dp)
                                ) {
                                    Text(text = "Play")
                                }
                                //Spacer(modifier = Modifier.size(20.dp))
                                Button(
                                    onClick = { navController.navigate("Preferences") },
                                    modifier = Modifier.width(200.dp)
                                ) {
                                    Text(text = "Preferences")
                                }
                                Button(
                                    onClick = { navController.navigate("NewPlayer") },
                                    modifier = Modifier.width(200.dp)
                                ) {
                                    Text(text = "New Player")
                                }
                                //Spacer(modifier = Modifier.size(20.dp))
                                //Button(onClick = { navController.navigate("Preferences") }, modifier = Modifier.width(200.dp)) {
                                Button(
                                    onClick = { navController.navigate("About") },
                                    modifier = Modifier.width(200.dp)
                                ) {
                                    Text(text = "About")
                                }/*
                            Button(
                                onClick = { navController.navigate("Tipos") },
                                modifier = Modifier.width(200.dp)
                            ) {
                                Text(text = "Types")
                            }*/
                            }

                            else -> {
                                Row() {
                                    Button(
                                        onClick = { navController.navigate("Play") },
                                        modifier = Modifier.width(200.dp)
                                    ) {
                                        Text(text = "Play")
                                    }

                                    //Spacer(modifier = Modifier.size(20.dp))
                                    Button(
                                        onClick = { navController.navigate("Preferences") },
                                        modifier = Modifier.width(200.dp)
                                    ) {
                                        Text(text = "Preferences")
                                    }
                                }
                                Row() {
                                    Button(
                                        onClick = { navController.navigate("NewPlayer") },
                                        modifier = Modifier.width(200.dp)
                                    ) {
                                        Text(text = "New Player")
                                    }
                                    //Spacer(modifier = Modifier.size(20.dp))
                                    //Button(onClick = { navController.navigate("Preferences") }, modifier = Modifier.width(200.dp)) {
                                    Button(
                                        onClick = { navController.navigate("About") },
                                        modifier = Modifier.width(200.dp)
                                    ) {
                                        Text(text = "About")
                                    }
                                }
                                /*
                            Row(horizontalArrangement = Arrangement.Center) {
                                Button(
                                    onClick = { navController.navigate("Types") },
                                    modifier = Modifier.width(200.dp)
                                ) {
                                    Text(text = "Types")
                                }
                            }*/
                            }
                        }
                    }
                }
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBarCenter(Texto: String, navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    val contextForToast = LocalContext.current.applicationContext

    CenterAlignedTopAppBar(// Título de la barra superior
        title = {

            Text(text = Texto)
        },
        navigationIcon = {
        },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Add Items")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Types") },
                    onClick = { navController.navigate("Tipos") },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.AccountBox,
                            contentDescription = null
                        )
                    })
                DropdownMenuItem(
                    text = { Text("Share") },
                    onClick = { },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Share,
                            contentDescription = null
                        )
                    })
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = tealA100
        )
    )
}
