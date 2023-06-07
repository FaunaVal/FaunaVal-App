package com.torregrosa.faunaval.ui.identify

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.torregrosa.faunaval.R
import com.torregrosa.faunaval.ui.theme.BackgroundColor
import com.torregrosa.faunaval.ui.theme.ButtonColor
import com.torregrosa.faunaval.ui.theme.TextBackgroundColor

@Composable
fun AnimalTypeQuestionScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TitleQuestions()
        CategoryQuestions(navController = navController)
    }
}

@Composable
fun CategoryQuestions(navController: NavController) {
    var selectedOption by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(ButtonColor)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.categoria_pregunta),
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(vertical = 16.dp),
            )
        }

        RadioButtonOption(
            selected = selectedOption == "1",
            onClick = { selectedOption = "1" },
            resLabel = R.string.categoria_respuesta_1
        )
        RadioButtonOption(
            selected = selectedOption == "2",
            onClick = { selectedOption = "2" },
            resLabel = R.string.categoria_respuesta_2
        )
        RadioButtonOption(
            selected = selectedOption == "3",
            onClick = { selectedOption = "3" },
            resLabel = R.string.categoria_respuesta_3
        )
        RadioButtonOption(
            selected = selectedOption == "4",
            onClick = { selectedOption = "4" },
            resLabel = R.string.categoria_respuesta_4
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
    nextButton(selectedOption = selectedOption, navController = navController)

}

@Composable
fun nextButton(selectedOption: String, navController: NavController) {
    Button(
        modifier = Modifier
            .widthIn(min = 250.dp)
            .padding(bottom = 30.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ButtonColor
        ),
        onClick = {
            when (selectedOption) {
                "4" -> navController.navigate("Amphibia_questions")
            }
            println("Respuesta seleccionada: $selectedOption")
        }
    ) {
        Text(
            text = stringResource(R.string.siguiente),
            style = MaterialTheme.typography.h4
        )
    }
}

@Composable
fun RadioButtonOption(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: (() -> Unit)?,
    @StringRes resLabel: Int
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = BackgroundColor
            )
        )
        CustomQuestionText(labelResourceId = resLabel)
    }
}

@Composable
fun CustomQuestionText(@StringRes labelResourceId: Int) {
    Text(
        text = stringResource(id = labelResourceId),
        style = MaterialTheme.typography.body1,
    )
}

@Composable
fun TitleQuestions(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .height(70.dp)
            .background(TextBackgroundColor.copy(alpha = 0.5f)),

        Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.cuestionario_titulo).uppercase(),
            style = MaterialTheme.typography.h3,
            letterSpacing = 3.sp,
            color = Color.White
        )
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
