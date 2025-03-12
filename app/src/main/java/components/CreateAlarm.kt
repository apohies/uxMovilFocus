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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.focus.FullScreenCard


@Composable
fun CreateAlarm(
    onCancelar: () -> Unit,
    onAceptar: () -> Unit,
    onLocation:() -> Unit
) {
    var descripcion by remember { mutableStateOf("") }
    var isOneTime by remember { mutableStateOf(true) }
    var isSeries by remember { mutableStateOf(false) }

    var showTimePicker by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf("Ingresar hora") }

    var showDaysPicker by remember { mutableStateOf(false) }
    var selectedDays by remember { mutableStateOf<List<String>>(emptyList()) }

    val showAddItemModal = remember { mutableStateOf(false) }
    val itemsList = remember { mutableStateListOf<String>() }


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
                text = "Creación Alarma",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )


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


                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.Green, CircleShape)
                        .clickable { showTimePicker = true }
                )
            }


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
                            showDaysPicker = true
                        }
                    )
                    Text(text = "serie")
                }
            }


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


                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.Green, CircleShape)
                        .clickable { onLocation() }
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
                        .clickable { showAddItemModal.value = true }
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



            if (showTimePicker)
            {
                TimePickerModal(
                    isVisible = showTimePicker,
                    onDismiss = { showTimePicker = false },
                    onTimeSelected = { hour, minute, isAm ->
                        hour
                        minute
                        isAm

                        val period = if (isAm) "AM" else "PM"
                        selectedTime = "${hour.toString().padStart(2, '0')}:${
                            minute.toString().padStart(2, '0')
                        } $period"
                    }
                )
            }

            DaysPickerModal(
                isVisible = showDaysPicker,
                onDismiss = { showDaysPicker = false },
                onDaysSelected = { days ->
                    selectedDays = days
                }
            )

            AddItemModal(
                isVisible = showAddItemModal.value,
                onDismiss = { showAddItemModal.value = false },
                onAddItem = { newItem ->
                    itemsList.add(newItem)
                }
            )

        }




    }


}



