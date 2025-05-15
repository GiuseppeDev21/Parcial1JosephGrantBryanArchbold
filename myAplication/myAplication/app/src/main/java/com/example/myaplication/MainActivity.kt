package com.example.myaplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                encabezadoLogin(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
fun encabezadoLogin(modifier: Modifier = Modifier) {
    var nota by remember { mutableStateOf("") }
    val contexto = LocalContext.current
    var mensajeValidacion by remember { mutableStateOf<String?>(null) } // Estado para el mensaje

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(35.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Parcial#1",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Estudiante#1",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Estudiante#2",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = "Ingrese la Nota a validar",
            fontSize = 15.sp,
            fontWeight = FontWeight.Light,
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = nota,
            onValueChange = { nota = it },
            placeholder = { Text(text = "Nota") },
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, shape = RoundedCornerShape(25.dp), color = Color.Gray),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (nota.isNotBlank()) {
                    val resultado = validarNota(nota) // Llama a la función de lógica
                    mensajeValidacion = resultado // Actualiza el estado con el resultado
                    Toast.makeText(contexto, resultado, Toast.LENGTH_LONG).show() // Muestra el Toast aquí
                } else {
                    val errorMessage = "Ingrese una nota correcta"
                    Toast.makeText(contexto, errorMessage, Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier
                .width(400.dp)
        ) {
            Text(text = "Validar Nota")
        }

        // Puedes mostrar el mensaje de validación en la UI si lo deseas
        mensajeValidacion?.let {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = it)
        }
    }
}

// Esta función ahora solo contiene la lógica de validación y devuelve un String
fun validarNota(notaString: String): String {
    val nota = notaString.toIntOrNull()
    return when {
        nota == null -> "Ingrese una nota válida"
        nota > 100 -> "Te pasastes de nota"
        nota in 91..100 -> "A (Excelente)"
        nota in 81..90 -> "B (Bueno)"
        nota in 71..80 -> "C (Regular)"
        nota in 61..70 -> "D (Más o menos regular)"
        nota < 61 -> "No Aprobado, gracias por participar"
        else -> "Error al validar la nota"
    }
}

@Preview(showSystemUi = true)
@Composable
fun previewLogin() {
    encabezadoLogin()
}