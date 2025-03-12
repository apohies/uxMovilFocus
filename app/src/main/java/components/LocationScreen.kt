package components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import com.example.focus.R

@Composable
fun LocationScreen(
    onCancelar: () -> Unit,
    onInicio: () -> Unit
) {
    var location by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Localización",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Campo de lugar con botón X
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Lugar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            trailingIcon = {
                IconButton(onClick = { location = "" }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Limpiar"
                    )
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(8.dp)
        )

        // Imagen del mapa (placeholder)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(vertical = 16.dp)
                .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
        ) {
            // Esta es la imagen placeholder que luego reemplazarás con tu mapa real
            Image(
                painter = painterResource(id = R.drawable.map_placeholder),
                contentDescription = "Mapa",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Alternativa si no tienes una imagen de placeholder
            // Descomenta esto y comenta el Image de arriba si prefieres usar esto
            /*
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = Color.Gray
                )
                Text(
                    text = "Imagen del mapa",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            */
        }

        // Espacio flexible
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
                onClick = onInicio,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0B6DA2) // Azul oscuro
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.width(120.dp)
            ) {
                Text("Inicio")
            }
        }
    }
}