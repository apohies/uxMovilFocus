package components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*

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
import com.example.focus.FullScreenCard
import com.example.focus.R

@Composable
fun LocationScreen(
    onCancelar: () -> Unit,
    onInicio: () -> Unit
) {
    var location by remember { mutableStateOf("") }

    FullScreenCard {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            // Título
            Text(
                text = "Localización",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )


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


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .padding(vertical = 16.dp)
                    .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
            ) {

                Image(
                    painter = painterResource(id = R.drawable.map_placeholder),
                    contentDescription = "Mapa",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )


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
}