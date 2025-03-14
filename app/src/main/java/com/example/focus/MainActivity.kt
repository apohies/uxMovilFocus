package com.example.focus

import CustomModal
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.focus.ui.theme.FocusTheme
import components.AccountLogin
import components.BlueprintLogin
import components.ChangeBlueprint
import components.ChangePassword
import components.ConfiguracionRolesPermisosScreen
import components.ConfiguracionScreen
import components.CreateAlarm
import components.Dashboard
import components.EditAlarm
import components.LocationScreen
import components.RegisterPassword
import components.SolicitudMetodoAccesoScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FocusTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "main"
                ) {
                    composable("main") {
                        LoginScreen(
                            onNavigateToRegister = {
                                navController.navigate("register")
                            },
                            onNavigateToBlueprint = {
                                navController.navigate("blueprintlogin")
                            },

                            onNavigatetoAccount = {
                                navController.navigate("accountlogin")
                            }
                        )
                    }
                    composable("second") {
                        SecondScreen(
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable("blueprint"){
                        Blueprint(
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable("register") {
                        Register(onClick = {
                            navController.navigate("registerBlueprint")

                        },
                            onregisterEmail = {
                                navController.navigate("registrerEmail")

                            },
                            onBackClick = {
                            navController.popBackStack()
                        })

                    }

                    composable("registerBlueprint")
                    {
                        RegisterBlueprint(onClick = {
                            navController.navigate("main")

                        })
                    }


                    composable("registerConfirmation"){

                        RegisterConfirmation()
                    }

                    composable("registrerEmail")
                    {
                        RegisterPassword(onClick = {
                            navController.navigate("main")

                        }, onBackClick = {
                            navController.popBackStack()
                        }
                        )
                    }

                    composable("blueprintlogin"){

                        BlueprintLogin(onClick = {
                            navController.navigate("dashboard")

                        })

                    }

                    composable("accountlogin"){

                        AccountLogin(onClick = {
                            navController.navigate("dashboard")

                        }, onBackClick = {
                            navController.popBackStack()
                        }
                        )
                    }

                    composable("dashboard"){
                        Dashboard(onNavigateToCreateAlarm={
                            navController.navigate("createAlarm")
                        },
                            onedit = {
                                navController.navigate("editAlarm")
                            },
                            onconfig = {
                                navController.navigate("configuration")
                            }

                        )
                    }

                    composable("createAlarm"){
                        CreateAlarm(onCancelar = {
                            navController.navigate("dashboard")

                        }, onAceptar = {
                            navController.popBackStack()
                        }, onLocation ={
                            navController.navigate("locationScreen")
                        }
                        )
                    }

                    composable("locationScreen"){
                        LocationScreen(onCancelar = {
                            navController.popBackStack()

                        }, onInicio = {
                            navController.popBackStack()
                        })


                    }

                    composable("editAlarm"){
                        EditAlarm(onCancelar = {
                            navController.navigate("dashboard")

                        }, onAceptar = {
                            navController.popBackStack()
                        }, onLocation ={
                            navController.navigate("locationScreen")
                        }
                        )

                    }

                    composable("configuration"){
                        ConfiguracionScreen(
                            onRolesPermisosClick = {
                                    navController.navigate("role")
                            },
                            onSolicitudMetodoAccesoClick = {
                                navController.navigate("method")
                            },
                            onBackClick = {

                                navController.navigate("dashboard")
                            }
                        )

                    }

                    composable("role"){

                        ConfiguracionRolesPermisosScreen(
                            onCancelarClick = {
                                navController.popBackStack()
                            },

                            onNavigateToInicio ={
                                navController.navigate("dashboard")
                            })
                    }


                    composable("method"){

                        SolicitudMetodoAccesoScreen(
                            onCancelarClick ={

                            },
                            onHuellaSelected={
                                navController.navigate("ChangeBlue")
                            },
                            onCuentaContraseñaSelected ={
                                navController.navigate("changePass")
                            }
                        )
                    }

                    composable("changePass"){

                        ChangePassword(onClick ={
                            navController.navigate("dashboard")
                        },
                            onBackClick ={
                                navController.navigate("dashboard")
                            })
                    }

                    composable("ChangeBlue"){
                        ChangeBlueprint(onClick ={
                            navController.navigate("dashboard")
                        })
                    }







                }
            }
        }
    }
}

@Composable
fun FullScreenCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF9F8F2)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}

@Composable
fun LoginScreen(onNavigateToRegister: () -> Unit , onNavigateToBlueprint:()-> Unit , onNavigatetoAccount: ()-> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            FullScreenCard {
                LoginContent(
                    title = "Focus",
                    description = "Inicio de sesion",
                    onNavigateClick = onNavigateToRegister,
                    onNavigateToBlueprint = onNavigateToBlueprint,
                    onNavigatetoAccount = onNavigatetoAccount
                )
            }
        }
    }
}

@Composable
fun LoginContent(title: String, description: String, onNavigateClick: () -> Unit ,onNavigateToBlueprint:()-> Unit , onNavigatetoAccount: ()-> Unit ) {


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){


    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
        fontSize = 50.sp
    )

    Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(top = 8.dp),
        fontSize = 25.sp
    )




        Spacer(modifier = Modifier.weight(1f))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onNavigateToBlueprint,
            modifier = Modifier.size(90.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bluprint),
                contentDescription = "Navegar a segunda pantalla",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.width(100.dp))

        IconButton(
            onClick = onNavigatetoAccount,
            modifier = Modifier.size(90.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "Navegar a segunda pantalla",
                modifier = Modifier.size(100.dp)
            )
        }


    }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            RoundedButton(onClick = onNavigateClick,"Registro",false)
            Spacer(modifier = Modifier.width(100.dp))
            RoundedButton(onClick = onNavigateClick,"Inicio",true)
        }

}
}



@Composable
fun RoundedButton(onClick: () -> Unit , title: String , color : Boolean) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(100.dp)
            .padding(vertical = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor =  if (color)  { Color(0xFF0B6DA2)} else {Color(0xFF3A5A40) } ,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text = title)
    }
}



@Composable
fun SecondScreen(onBackClick: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            FullScreenCard {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Segunda Pantalla",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = onBackClick,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Volver")
                    }
                }
            }
        }
    }
}


@Composable
fun Blueprint(onBackClick: () -> Unit){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            FullScreenCard {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Segunda Pantalla",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 100.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = onBackClick,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Returnal")
                    }
                }
            }
        }
    }

}

@Composable
fun Register(onClick: () -> Unit , onregisterEmail :() -> Unit,onBackClick: () -> Unit)
{
    var localTitle = "user"
    var username by remember { mutableStateOf("ma.moreno2") }
    var fullName by remember { mutableStateOf("Miguel Angel Moreno") }
    var email by remember { mutableStateOf("ma.moreno2@uniandes.edu.co") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            FullScreenCard {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    //verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Focus",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontSize = 50.sp
                    )

                    Text(
                        text = "Registro Usuario",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp),
                        fontSize = 25.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    OutlinedTextField(
                            value = username,
                            onValueChange = { username = it  },
                            label = { Text("usuario") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    Spacer(modifier = Modifier.height(24.dp))


                    OutlinedTextField(
                        value = fullName,
                        onValueChange = { fullName = it },
                        label = { Text("Nombres") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )


                    Spacer(modifier = Modifier.height(24.dp))


                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onClick,
                            modifier = Modifier.size(90.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.bluprint),
                                contentDescription = "Navegar a segunda pantalla",
                                modifier = Modifier.size(100.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(100.dp))

                        IconButton(
                            onClick = onregisterEmail,
                            modifier = Modifier.size(90.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.user),
                                contentDescription = "Navegar a segunda pantalla",
                                modifier = Modifier.size(100.dp)
                            )
                        }


                    }



                }
            }
        }
    }

}

@Composable
fun RegisterBlueprint(onClick: () -> Unit){
    val showModal = remember { mutableStateOf(false) }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
        }
        FullScreenCard {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                //verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Focus",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    fontSize = 50.sp
                )

                Text(
                    text = "Registro Usuario",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp),
                    fontSize = 25.sp
                )


                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.blueprinu),
                        contentDescription = "Descripcion",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(120.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    RoundedButton(
                        onClick = { showModal.value = true },
                        title = "Registro",
                        color = true
                    )

                }

                if (showModal.value) {
                    CustomModal(
                        onDismiss = { showModal.value = false },
                        onConfirm = {

                            showModal.value = false
                            onClick()
                        },
                        title = "Usuario Creado",
                        message = "Felicidades tu cuenta ha sido creada en pocos minutos recibiras una notificacion via mail",
                        confirmText = "Aceptar",
                        dismissText = "Cancelar"
                    )

                }




            }
        }


    }
}


@Composable
fun RegisterConfirmation(){

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
        }
        FullScreenCard {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                //verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Focus",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    fontSize = 50.sp
                )
            }
        }


    }
}

