package components

import CustomModal
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.focus.FullScreenCard
import com.example.focus.R

@Composable
fun RegisterPassword(onClick: () -> Unit ,onBackClick: () -> Unit)
{
    var localTitle = "user"
    val showModal = remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("ma.moreno2@uniandes.edu.co") }
    var password by remember { mutableStateOf("**********") }

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
                ) {

                    Text(
                        text = "Focus",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontSize = 50.sp
                    )

                    Text(
                        text = "Registro Usuario Correo",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp),
                        fontSize = 25.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier.height(24.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier.height(24.dp))


                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("password") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier.height(24.dp))



                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp, top = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        com.example.focus.RoundedButton(
                            onClick = { showModal.value = true },
                            title = "Aceptar",
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

}