package components
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp

import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
@Composable
fun DeleteAlarmModal(
    isVisible: Boolean,
    alarmName: String = "esta alarma", // Nombre o descripción de la alarma
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (isVisible) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = Color(0xFF87CEEB),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
            ) {
                // Barra superior
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Flecha atrás
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .clickable { onDismiss() }
                    )

                    // Título
                    Text(
                        text = "Eliminar Alarma",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    // X para cerrar
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Cerrar",
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable { onDismiss() }
                    )
                }

                // Contenido del modal - Mensaje de confirmación
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF9F8F2) // Color beige claro
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "¿Está seguro que desea eliminar $alarmName?",
                            style = MaterialTheme.typography.bodyLarge,

                        )
                    }
                }

                // Botones inferiores
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF3A5A40) // Verde oscuro
                        ),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier.width(120.dp)
                    ) {
                        Text("Cancelar")
                    }

                    Button(
                        onClick = onConfirm,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0B6DA2) // Azul oscuro
                        ),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier.width(120.dp)
                    ) {
                        Text("Aceptar")
                    }
                }
            }
        }
    }
}

// Ejemplo de uso:
@Composable
fun DeleteAlarmExample() {
    var showDeleteAlarmDialog by remember { mutableStateOf(false) }
    var selectedAlarm by remember { mutableStateOf("Droguería") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { showDeleteAlarmDialog = true }
        ) {
            Text("Mostrar diálogo para eliminar alarma")
        }
    }

    // Modal para eliminar alarma
    DeleteAlarmModal(
        isVisible = showDeleteAlarmDialog,
        alarmName = selectedAlarm,
        onDismiss = { showDeleteAlarmDialog = false },
        onConfirm = {
            // Aquí iría el código para eliminar la alarma si fuera necesario
            showDeleteAlarmDialog = false
        }
    )
}