package components
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
fun DaysPickerModal(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onDaysSelected: (List<String>) -> Unit
) {

    val days = listOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo")
    val selectedDays = remember { mutableStateListOf<String>() }

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
                        text = "Definir serie",
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

                // Contenido del selector de días
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
                        // Grid de selección de días
                        GridLayout(
                            items = days,
                            columns = 2,
                            modifier = Modifier.fillMaxWidth()
                        ) { day ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                val isSelected = selectedDays.contains(day)
                                RadioButton(
                                    selected = isSelected,
                                    onClick = {
                                        if (isSelected) {
                                            selectedDays.remove(day)
                                        } else {
                                            selectedDays.add(day)
                                        }
                                    }
                                )
                                Text(
                                    text = day,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }

                        // Excepción para Domingo que va en el centro
                        if (days.contains("Domingo")) {
                            val isSelected = selectedDays.contains("Domingo")
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(top = 8.dp)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                RadioButton(
                                    selected = isSelected,
                                    onClick = {
                                        if (isSelected) {
                                            selectedDays.remove("Domingo")
                                        } else {
                                            selectedDays.add("Domingo")
                                        }
                                    }
                                )
                                Text(
                                    text = "Domingo",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                }

                // Botón Aceptar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            onDaysSelected(selectedDays.toList())
                            onDismiss()
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0B6DA2) // Azul oscuro
                        ),
                        modifier = Modifier.width(120.dp)
                    ) {
                        Text("Aceptar")
                    }
                }
            }
        }
    }
}

/**
 * Utility para crear un grid layout personalizado
 */
@Composable
fun <T> GridLayout(
    items: List<T>,
    columns: Int,
    modifier: Modifier = Modifier,
    content: @Composable (T) -> Unit
) {
    Column(modifier = modifier) {
        var itemsPerRow = 0
        var rowItems = mutableListOf<T>()

        for (item in items) {
            if (item.toString() == "Domingo") continue // Skip Domingo to place at center later

            rowItems.add(item)
            itemsPerRow++

            if (itemsPerRow == columns) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (rowItem in rowItems) {
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            content(rowItem)
                        }
                    }
                }
                rowItems = mutableListOf()
                itemsPerRow = 0
            }
        }

        // Handle any remaining items
        if (rowItems.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (rowItem in rowItems) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        content(rowItem)
                    }
                }

                // Add empty boxes to fill the row
                repeat(columns - rowItems.size) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) { }
                }
            }
        }
    }
}