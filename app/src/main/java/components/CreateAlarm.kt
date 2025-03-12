package components
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CreateAlarm(
    onCancelar: () -> Unit,
    onAceptar: () -> Unit
) {
    var descripcion by remember { mutableStateOf("") }
    var isOneTime by remember { mutableStateOf(true) }
    var isSeries by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Creación Alarma",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Campo de descripción con botón X
        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            trailingIcon = {
                IconButton(onClick = { descripcion = "" }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Limpiar"
                    )
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(8.dp)
        )

        // Hora
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Hora",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Ingresar hora")
            }

            // Círculo verde para hora
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.Green, CircleShape)
                    .clickable { /* Abrir selector de hora */ }
            )
        }

        // Opciones de frecuencia
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isOneTime,
                    onCheckedChange = {
                        isOneTime = it
                        if (it) isSeries = false
                    }
                )
                Text(text = "una vez")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isSeries,
                    onCheckedChange = {
                        isSeries = it
                        if (it) isOneTime = false
                    }
                )
                Text(text = "serie")
            }
        }

        // Localización
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Ubicación",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Ingresar localización")
            }

            // Círculo verde para localización
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.Green, CircleShape)
                    .clickable { /* Abrir mapa */ }
            )
        }

        // Items
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Items",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Ingresar items")
            }

            // Círculo verde para items
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.Green, CircleShape)
                    .clickable { /* Abrir lista de items */ }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Tarjeta de pastillas
        Card(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFADD8E6) // Azul claro
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "pastillas",
                    style = MaterialTheme.typography.bodyMedium
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Botón editar (amarillo)
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color.Yellow, CircleShape)
                            .clickable { /* Editar */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar",
                            tint = Color.Black,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    // Botón eliminar (rojo)
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color.Red, CircleShape)
                            .clickable { /* Eliminar */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botones de acción
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onCancelar,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3A5A40) // Verde oscuro
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.width(120.dp)
            ) {
                Text("Cancelar")
            }

            Button(
                onClick = onAceptar,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0B6DA2) // Azul oscuro
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.width(120.dp)
            ) {
                Text("Aceptar")
            }
        }
    }

    // Botón flotante "S"
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        FloatingActionButton(
            onClick = { /* Acción */ },
            containerColor = Color(0xFF87CEEB),
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(48.dp),
            shape = CircleShape
        ) {
            Text(
                text = "S",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}