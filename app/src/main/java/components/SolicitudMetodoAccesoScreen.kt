package components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SolicitudMetodoAccesoScreen(
    onCancelarClick: () -> Unit,
    onHuellaSelected: () -> Unit,
    onCuentaContraseñaSelected: () -> Unit
) {
    var selectedMethod by remember { mutableStateOf("Huella") }
    var showConfirmationDialog by remember { mutableStateOf(false) }

    val methodOptions = listOf("Huella", "Cuenta y contraseña")

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
                text = "Solicitud metodo acceso",
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
                    // Opciones de método de acceso
                    methodOptions.forEach { method ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Checkbox(
                                checked = selectedMethod == method,
                                onCheckedChange = {
                                    if (it) {
                                        selectedMethod = method
                                    }
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color.Black,
                                    uncheckedColor = Color.Black
                                )
                            )
                            Text(
                                text = method,
                                modifier = Modifier.padding(start = 8.dp),
                                color = Color.Black
                            )
                        }
                    }
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
                    onClick = {
                        if (selectedMethod == "Huella") {
                            onHuellaSelected()
                        } else {
                            onCuentaContraseñaSelected()
                        }
                    },
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
                Text("Se ha almacenado su método de acceso para ser enviado y aprobado por el administrador")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showConfirmationDialog = false
                        if (selectedMethod == "Huella") {
                            onHuellaSelected()
                        } else {
                            onCuentaContraseñaSelected()
                        }
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