package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConfiguracionRolesPermisosScreen(
    onCancelarClick: () -> Unit,
    onNavigateToInicio: () -> Unit
) {
    var selectedRole by remember { mutableStateOf("Administrador") }
    var justificacion by remember { mutableStateOf("") }
    var showConfirmationDialog by remember { mutableStateOf(false) }

    val roleOptions = listOf("Administrador", "Invitado", "Usuario")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF5F5F5) // Fondo gris claro similar a la imagen
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Título
            Text(
                text = "Focus",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtítulo
            Text(
                text = "Configuracion roles y permisos",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Tarjeta principal
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray.copy(alpha = 0.5f)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    // Opciones de rol
                    roleOptions.forEach { role ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            RadioButton(
                                selected = selectedRole == role,
                                onClick = { selectedRole = role },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Black,
                                    unselectedColor = Color.Black
                                )
                            )
                            Text(
                                text = role,
                                modifier = Modifier.padding(start = 8.dp),
                                color = Color.Black
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo de justificación
                    Text(
                        text = "Justifique su acceso",
                        color = Color(0xFF4CAF50), // Verde similar al de la imagen
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = justificacion,
                        onValueChange = { justificacion = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Gray,
                            unfocusedBorderColor = Color.Gray,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            }

            // Botones en la parte inferior
            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Botón Cancelar
                Button(
                    onClick = onCancelarClick,
                    modifier = Modifier
                        .width(120.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2E7D32) // Verde oscuro
                    )
                ) {
                    Text(
                        text = "Cancelar",
                        color = Color.White
                    )
                }

                // Botón Aceptar
                Button(
                    onClick = { showConfirmationDialog = true },
                    modifier = Modifier
                        .width(120.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0277BD) // Azul similar al de la imagen
                    )
                ) {
                    Text(
                        text = "Aceptar",
                        color = Color.White
                    )
                }
            }
        }
    }

    // Diálogo de confirmación
    if (showConfirmationDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmationDialog = false },
            title = { Text("Noticicación") },
            text = {
                Text("Tu solicitud fue notificada a administración")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showConfirmationDialog = false
                        onNavigateToInicio()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0277BD) // Azul oscuro
                    )
                ) {
                    Text("Aceptar", color = Color.White)
                }
            },
            containerColor = Color(0xFFB3E5FC), // Azul celeste claro
            titleContentColor = Color.Black,
            textContentColor = Color.Black
        )
    }
}