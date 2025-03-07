package com.example.focus

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.focus.ui.theme.FocusTheme

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
                            onNavigateToSecond = {
                                navController.navigate("second")
                            },
                            onNavigateToBlueprint = {
                                navController.navigate("blueprint")
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
            containerColor = MaterialTheme.colorScheme.surface
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
fun LoginScreen(onNavigateToSecond: () -> Unit , onNavigateToBlueprint:()-> Unit) {
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
                    onNavigateClick = onNavigateToSecond,
                    onNavigateToBlueprint = onNavigateToBlueprint
                )
            }
        }
    }
}

@Composable
fun LoginContent(title: String, description: String, onNavigateClick: () -> Unit ,onNavigateToBlueprint:()-> Unit ) {
    var localTitle by remember { mutableStateOf("hola") }
    var localDescription by remember { mutableStateOf(description) }

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

    OutlinedTextField(
        value = localTitle,
        onValueChange = { localTitle = it },
        label = { Text("Título") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()  // Puedes mantenerlo o quitarlo dependiendo de tus necesidades
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,  // Esto centrará los elementos
        verticalAlignment = Alignment.CenterVertically  // Asegura alineación vertical
    ) {
        IconButton(
            onClick = onNavigateClick,
            modifier = Modifier.size(48.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "Navegar a segunda pantalla",
                modifier = Modifier.size(80.dp)  // Nota: este tamaño es mayor que el área clickeable
            )
        }

        Spacer(modifier = Modifier.width(24.dp))  // Aumenté el espacio para separar más los iconos

        IconButton(
            onClick = onNavigateToBlueprint,
            modifier = Modifier.size(48.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bluprint),
                contentDescription = "Navegar a segunda pantalla",
                modifier = Modifier.size(80.dp)
            )
        }
    }

    RoundedButton(onClick = onNavigateClick)
}

@Composable
fun RoundedButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(100.dp)
            .padding(vertical = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text = "Aceptar")
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

