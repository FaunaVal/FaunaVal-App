package com.torregrosa.faunaval.ui.identify

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.torregrosa.faunaval.App
import com.torregrosa.faunaval.R
import com.torregrosa.faunaval.ui.theme.BackgroundColor
import com.torregrosa.faunaval.ui.theme.ButtonColor

sealed class Question(val text: String)

object InitialQuestion : Question(App.context.getString(R.string.anfibios_pregunta_1))
object BodyQuestion : Question(App.context.getString(R.string.anfibios_pregunta_2))
object EyeQuestion : Question(App.context.getString(R.string.anfibios_pregunta_3))
object SkinQuestion : Question(App.context.getString(R.string.anfibios_pregunta_4))

@Composable
fun QuestionsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TitleQuestions()
        QuestionsColumn(navController = navController)
    }

}

@Composable
fun QuestionsColumn(navController: NavController) {
    var currentQuestion by remember { mutableStateOf<Question>(InitialQuestion) }
    var answers by remember { mutableStateOf("1") }

    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(ButtonColor)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentQuestion.text,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )
        Row(
            modifier = Modifier.padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButtonOption(
                selected = answers == "1",
                onClick = { answers = "1" },
                resLabel = R.string.si
            )
            Spacer(
                modifier = Modifier.width(16.dp)
            )
            RadioButtonOption(
                selected = answers == "0",
                onClick = { answers = "0" },
                resLabel = R.string.no
            )
        }
    }

    Row(
        modifier = Modifier.padding(10.dp)
    ) {
        Button(
            modifier = Modifier
                .widthIn(min = 150.dp)
                .padding(bottom = 30.dp, end = 10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ButtonColor
            ),
            onClick = {
                if (currentQuestion == InitialQuestion) {
                    navController.navigate("Identify")
                } else {
                    when (currentQuestion) {
                        BodyQuestion -> currentQuestion = InitialQuestion
                        EyeQuestion -> currentQuestion = BodyQuestion
                        SkinQuestion -> currentQuestion = EyeQuestion
                        else -> {
                            navController.navigate("Identify")
                        }
                    }
                    answers = "1"
                }
            }
        ) {
            Text(text = stringResource(R.string.boton_atras), style = MaterialTheme.typography.h5)
        }
        Button(
            modifier = Modifier
                .widthIn(min = 150.dp)
                .padding(bottom = 30.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ButtonColor
            ),
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
                answers = "1"
            },
        ) {
            Text(
                text = stringResource(R.string.boton_siguiente),
                style = MaterialTheme.typography.h5
            )
        }
    }
}

fun searchFunction(query: String, navController: NavController) {
    navController.navigate("AnimalList/4?filter=${query}")
}

@Composable
fun AmphibiaIdentificationPage(navController: NavController) {
    QuestionsScreen(navController)
}

@Preview(showBackground = true)
@Composable
fun PreviewIdentification() {
    AmphibiaIdentificationPage(navController = NavHostController(LocalContext.current))
}
