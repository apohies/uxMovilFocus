package components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun AlarmListScreen(onNavigateToCreateAlarm: () -> Unit , onedit :()-> Unit,onconfig : ()-> Unit) {
    val alarms = remember {
        listOf(
            AlarmItem("Droguería", "10:40 AM"),
            AlarmItem("Droguería", "10:40 AM"),
            AlarmItem("Droguería", "10:40 AM"),
            AlarmItem("Droguería", "10:40 AM")
        )
    }

    var showDeleteAlarmDialog by remember { mutableStateOf(false) }
    var selectedAlarm by remember { mutableStateOf("Droguería") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = {

                            onconfig()
                        },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "Ir a Configuración",
                            modifier = Modifier.size(24.dp)
                        )
                    }


                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(Color.Green, shape = CircleShape)
                            .clickable {

                                onNavigateToCreateAlarm()
                            },
                        contentAlignment = Alignment.Center
                    ) {

                    }
                }
            }


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray.copy(alpha = 0.5f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    alarms.forEach { alarm ->
                        AlarmCard(
                            title = alarm.title,
                            description = alarm.time,
                            onEditClick = { onedit() },
                            onDeleteClick = { showDeleteAlarmDialog = true }
                        )
                    }
                }
            }
        }




//        Box(
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(end = 16.dp, bottom = 72.dp)
//        ) {
//            FloatingActionButton(
//                onClick = {  },
//                containerColor = Color.DarkGray,
//                contentColor = Color.White,
//                shape = CircleShape,
//                modifier = Modifier.size(40.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Añadir"
//                )
//            }
//        }

        DeleteAlarmModal(
            isVisible = showDeleteAlarmDialog,
            alarmName = selectedAlarm,
            onDismiss = { showDeleteAlarmDialog = false },
            onConfirm = {

                showDeleteAlarmDialog = false
            }
        )
    }
}

@Composable
fun AlarmCard(
    title: String,
    description: String,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFADD8E6) // Color azul claro
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall
                )
            }


            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(Color.Yellow, shape = CircleShape)
                        .clickable { onEditClick() },
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
                        .background(Color.Red, shape = CircleShape)
                        .clickable { onDeleteClick() },
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
}

data class AlarmItem(
    val title: String,
    val time: String
)