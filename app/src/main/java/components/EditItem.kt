

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
fun EditItemModal(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onAddItem: (String) -> Unit
) {
    var itemText by remember { mutableStateOf("") }

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
                        text = "Editar Item",
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

                // Contenido del modal
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // Campo de texto para item
                        OutlinedTextField(
                            value = "pastillas",
                            onValueChange = { itemText = it },
                            label = { Text("Item") },
                            modifier = Modifier.fillMaxWidth(),
                            trailingIcon = {
                                IconButton(onClick = { itemText = "" }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Limpiar"
                                    )
                                }
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp)
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
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.width(120.dp)
                    ) {
                        Text("Cancelar")
                    }

                    Button(
                        onClick = {
                            if (itemText.isNotBlank()) {
                                onAddItem(itemText)
                                itemText = ""
                                onDismiss()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0B6DA2) // Azul oscuro
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.width(120.dp)
                    ) {
                        Text("Inicio")
                    }
                }
            }

        }
    }
}


