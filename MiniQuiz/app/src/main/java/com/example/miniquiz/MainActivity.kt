package com.example.miniquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.miniquiz.ui.theme.MiniQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniQuizTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MiniQuizUI(modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MiniQuizUI(modifier : Modifier) {
    var respuestaElegida by remember {
        mutableStateOf(0)
    }
    var respuestaCorrecta = 2
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 60.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MiniQuizHeader()
        MiniQuizGameInfo()
        MiniQuizQuestion()
        MiniQuizAnswers(respuestaElegida) {
            nuevaRespuesta -> respuestaElegida = nuevaRespuesta
        }
        MiniQuizFooter(respuestaElegida, respuestaCorrecta)
    }
}

@Composable
fun MiniQuizHeader() {
    Text(
        text = "Bienvenido a MiniQuiz",
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun MiniQuizGameInfo() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Pregunta 1 de 5"
        )
        Text(
            text = "0 Puntos"
        )
    }
}

@Composable
fun MiniQuizQuestion() {
    Text(
        text = "¿Qué lenguaje utilizamos para desarrollar aplicaciones Android?",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun MiniQuizAnswers(respuestaElegida: Int, onRespuestaSeleccionada: (Int) -> Unit) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = { onRespuestaSeleccionada(1) },
            enabled = respuestaElegida == 0,
            modifier = Modifier.weight(1f)
        ) {
            Text("JavaScript")
        }

        Button(
            onClick = { onRespuestaSeleccionada(2) },
            enabled = respuestaElegida == 0,
            modifier = Modifier.weight(1f)
        ) {
            Text("Kotlin")
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = { onRespuestaSeleccionada(3) },
            enabled = respuestaElegida == 0,
            modifier = Modifier.weight(1f)
        ) {
            Text("PHP")
        }

        Button(
            onClick = { onRespuestaSeleccionada(4) },
            enabled = respuestaElegida == 0,
            modifier = Modifier.weight(1f)
        ) {
            Text("Python")
        }
    }
}

@Composable
fun MiniQuizFooter(respuestaElegida: Int, respuestaCorrecta: Int){
    val textoResultado = when {
        respuestaElegida == 0 -> "Selecciona una respuesta para continuar"
        respuestaElegida == respuestaCorrecta -> "¡Correcto! Kotlin es el lenguaje oficial."
        else -> "Incorrecto. Intentalo de nuevo."
    }
    Text(
        text = textoResultado,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )
}
@Preview(showBackground = true)
@Composable
fun MiniQuizUIPreview() {
    MiniQuizTheme {
        MiniQuizUI(modifier = Modifier)
    }
}