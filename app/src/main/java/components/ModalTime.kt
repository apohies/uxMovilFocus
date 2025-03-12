package components
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun TimePickerModal(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onTimeSelected: (hour: Int, minute: Int, isAm: Boolean) -> Unit
) {
    var selectedHour by remember { mutableStateOf(12) }
    var selectedMinute by remember { mutableStateOf(0) }
    var isAm by remember { mutableStateOf(true) }

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
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
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
                        text = "Definir la hora",
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

                // Contenido del selector de hora
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Enter time",
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Selector de hora
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Selector de hora
                            Card(
                                modifier = Modifier
                                    .width(64.dp)
                                    .height(56.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFEAE1F8)
                                )
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    BasicTextField(
                                        value = selectedHour.toString().padStart(2, '0'),
                                        onValueChange = { newValue ->
                                            if (newValue.isEmpty()) {
                                                selectedHour = 0
                                            } else {
                                                val hour = newValue.toIntOrNull()
                                                if (hour != null && hour in 0..12) {
                                                    selectedHour = hour
                                                }
                                            }
                                        },
                                        textStyle = TextStyle(
                                            color = Color(0xFF673AB7),
                                            fontSize = 28.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Number
                                        ),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }

                            // Separador de hora y minutos
                            Text(
                                text = ":",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )

                            // Selector de minutos
                            Card(
                                modifier = Modifier
                                    .width(64.dp)
                                    .height(56.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFF0F0F0)
                                )
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    BasicTextField(
                                        value = selectedMinute.toString().padStart(2, '0'),
                                        onValueChange = { newValue ->
                                            if (newValue.isEmpty()) {
                                                selectedMinute = 0
                                            } else {
                                                val minute = newValue.toIntOrNull()
                                                if (minute != null && minute in 0..59) {
                                                    selectedMinute = minute
                                                }
                                            }
                                        },
                                        textStyle = TextStyle(
                                            color = Color.Black,
                                            fontSize = 28.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Number
                                        ),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }

                            // Espacio para separar
                            Spacer(modifier = Modifier.width(12.dp))

                            // Selector AM/PM
                            Column(
                                modifier = Modifier.width(48.dp)
                            ) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(28.dp)
                                        .clickable { isAm = true },
                                    shape = RoundedCornerShape(4.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = if (isAm) Color(0xFFFCE4EC) else Color(0xFFF0F0F0)
                                    )
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Text(
                                            text = "AM",
                                            color = if (isAm) Color.Red else Color.Black,
                                            fontWeight = if (isAm) FontWeight.Bold else FontWeight.Normal
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(28.dp)
                                        .clickable { isAm = false },
                                    shape = RoundedCornerShape(4.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = if (!isAm) Color(0xFFFCE4EC) else Color(0xFFF0F0F0)
                                    )
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Text(
                                            text = "PM",
                                            color = if (!isAm) Color.Red else Color.Black,
                                            fontWeight = if (!isAm) FontWeight.Bold else FontWeight.Normal
                                        )
                                    }
                                }
                            }
                        }

                        // Etiquetas
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Hour",
                                fontSize = 12.sp,
                                modifier = Modifier.width(64.dp),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = "Minute",
                                fontSize = 12.sp,
                                modifier = Modifier.width(64.dp),
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Botones inferiores
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Icono de reloj
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = "Reloj"
                            )

                            // Botones Cancel y OK
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                TextButton(onClick = onDismiss) {
                                    Text(
                                        text = "Cancel",
                                        color = Color(0xFF673AB7)
                                    )
                                }

                                TextButton(
                                    onClick = {
                                        onTimeSelected(selectedHour, selectedMinute, isAm)
                                        onDismiss()
                                    }
                                ) {
                                    Text(
                                        text = "OK",
                                        color = Color(0xFF673AB7),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// Ejemplo de uso:
@Composable
fun UseTimePickerExample() {
    var showTimePicker by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf("No time selected") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(text = "Selected time: $selectedTime")

        Button(onClick = { showTimePicker = true }) {
            Text("Show Time Picker")
        }

        TimePickerModal(
            isVisible = showTimePicker,
            onDismiss = { showTimePicker = false },
            onTimeSelected = { hour, minute, isAm ->
                val period = if (isAm) "AM" else "PM"
                selectedTime = "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')} $period"
            }
        )
    }
}