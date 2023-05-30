package com.torregrosa.faunaval.ui

import androidx.compose.foundation.layout.*
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

sealed class Question(val text: String)

object InitialQuestion : Question("¿Tiene cola?")
object BodyQuestion : Question("¿Tiene el cuerpo rechoncho y las patas cortas?")
object EyeQuestion : Question("¿Tiene el iris del ojo anaranjado o rojo?")
object SkinQuestion : Question("¿Tiene puntos anaranjados en la piel?")

@Composable
fun QuestionScreen(navController: NavController) {
    var currentQuestion by remember { mutableStateOf<Question>(InitialQuestion) }
    var answers by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentQuestion.text,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            modifier = Modifier.padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = answers == "1",
                onClick = { answers = "1" })
            Text(
                text = "yes",
                style = MaterialTheme.typography.body1
            )
            Spacer(
                modifier = Modifier.width(16.dp)
            )
            RadioButton(
                selected = answers == "0",
                onClick = { answers = "0" })
            Text(
                text = "no",
                style = MaterialTheme.typography.body1
            )
        }
        Button(
            onClick = {
                when (currentQuestion) {
                    InitialQuestion -> if (answers == "1") {
                        // Ejecutar función cuando la respuesta es afirmativa (tiene cola)
                        searchFunction("1", navController)
                    } else {
                        currentQuestion = BodyQuestion
                    }
                    BodyQuestion -> if (answers == "0") {
                        // Ejecutar función cuando la respuesta es afirmativa (cuerpo rechoncho y patas cortas)
                        searchFunction("11", navController)
                    } else {
                        currentQuestion = EyeQuestion
                    }
                    EyeQuestion -> if (answers == "1") {
                        // Ejecutar función cuando la respuesta es afirmativa (Ojos)
                        searchFunction("111", navController)
                    } else {
                        currentQuestion = SkinQuestion
                    }
                    SkinQuestion -> if (answers == "1") {
                        // Ejecutar función cuando la respuesta es afirmativa (Piel)
                        searchFunction("1111", navController)
                    } else {
                        // Ejecutar función cuando la respuesta es negativa (Piel)
                        searchFunction("0", navController)
                    }
                }
                answers = ""
            },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Next")
        }
        Button(
            onClick = {
                currentQuestion = InitialQuestion
                answers = ""
            }
        ) {
            Text(text = "Back")
        }
    }
}

fun searchFunction(query: String, navController: NavController) {
    navController.navigate("AnimalList/4?filter=${query}")
}

@Composable
fun AmphibiaIdentificationPage(navController: NavController) {
    QuestionScreen(navController)
}

@Preview(showBackground = true)
@Composable
fun PreviewIdentification() {
    AmphibiaIdentificationPage(navController = NavHostController(LocalContext.current))
}
