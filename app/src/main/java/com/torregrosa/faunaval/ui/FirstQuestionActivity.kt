package com.torregrosa.faunaval.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimalTypeQuestionScreen() {
    var selectedOption by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "¿Qué tipo de animal buscas?",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            RadioButton(
                selected = selectedOption == "Tiene pelo?",
                onClick = { selectedOption = "Tiene pelo?" }
            )
            Text(
                text = "Tiene pelo?",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 24.dp)
            )
        }
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            RadioButton(
                selected = selectedOption == "Tiene plumas?",
                onClick = { selectedOption = "Tiene plumas?" }
            )
            Text(
                text = "Tiene plumas?",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 24.dp)
            )
        }
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            RadioButton(
                selected = selectedOption == "Parece una tortuga, serpiente o lagarto?",
                onClick = { selectedOption = "Parece una tortuga, serpiente o lagarto?" }
            )
            Text(
                text = "Parece una tortuga, serpiente o lagarto?",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 24.dp)
            )
        }
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            RadioButton(
                selected = selectedOption == "Otros",
                onClick = { selectedOption = "Otros" }
            )
            Text(
                text = "Otros",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 24.dp)
            )
        }
        Button(
            onClick = {
                println("Respuesta seleccionada: $selectedOption")
            }
        ) {
            Text(text = "Siguiente")
        }
    }
}

@Composable
fun CategoryQuestion() {
    AnimalTypeQuestionScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewQuestion() {
    CategoryQuestion()
}
