package components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun RoundedButton(onClick: () -> Unit , title: String , color : Boolean) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(100.dp)
            .padding(vertical = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor =  if (color)  { Color(0xFF0B6DA2)
            } else {
                Color(0xFF3A5A40) } ,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text = title)
    }
}