package com.torregrosa.faunaval.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun AnimalTypeQuestionScreen(navController: NavController) {
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
                selected = selectedOption == "1",
                onClick = { selectedOption = "1" }
            )
            Text(
                text = "Animal con pelo y 4 patas (Mamífero)",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 24.dp)
            )
        }
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            RadioButton(
                selected = selectedOption == "2",
                onClick = { selectedOption = "2" }
            )
            Text(
                text = "Animal con plumas, alas y pico (Aves)",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 24.dp)
            )
        }
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            RadioButton(
                selected = selectedOption == "3",
                onClick = { selectedOption = "3" }
            )
            Text(
                text = "Parece una tortuga, serpiente o lagarto (Reptiles)",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 24.dp)
            )
        }
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            RadioButton(
                selected = selectedOption == "4",
                onClick = { selectedOption = "4" }
            )
            Text(
                text = "Parece un sapo, rana o lagarto de agua (Anfibios)",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 24.dp)
            )
        }
        Button(
            onClick = {
                when(selectedOption){
                    "4" -> navController.navigate("Amphibia_questions")
                }
                println("Respuesta seleccionada: $selectedOption")
            }
        ) {
            Text(text = "Siguiente")
        }
    }
}

@Composable
fun CategoryQuestion(navController: NavController) {
    AnimalTypeQuestionScreen(navController)
}

@Preview(showBackground = true)
@Composable
fun PreviewQuestion() {
    CategoryQuestion(navController = NavHostController(LocalContext.current))
}
