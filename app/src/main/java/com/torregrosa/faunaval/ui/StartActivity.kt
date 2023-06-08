package com.torregrosa.faunaval.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.torregrosa.faunaval.AnimalViewModel
import com.torregrosa.faunaval.R
import com.torregrosa.faunaval.ui.theme.BackgroundColor
import com.torregrosa.faunaval.ui.theme.ButtonColor
import com.torregrosa.faunaval.ui.theme.TextBackgroundColor

@Composable
fun StartScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: AnimalViewModel = hiltViewModel()
) {

    Column(
        modifier = modifier
            .background(BackgroundColor)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        TitleImage()
        Spacer(modifier = Modifier.height(8.dp))
        SelectOptionButton(
            labelResourceId = R.string.identificar,
            onClick = { navController.navigate("Identify") })
        SelectOptionButton(
            labelResourceId = R.string.explorar,
            onClick = { navController.navigate("Categories") })
        SelectOptionButton(
            labelResourceId = R.string.colaborar,
            onClick = { navController.navigate("Contact") })
    }
}

@Composable
fun TitleImage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Image(
            painter = painterResource(R.drawable.rafol_benicadell),
            contentDescription = null,
            alpha = 0.6F,
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxWidth()
        )
        Box(
            modifier = modifier
                .padding(top = 100.dp)
                .fillMaxWidth()
                .height(70.dp)
                .background(TextBackgroundColor.copy(alpha = 0.5f)),

            Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.app_name).uppercase(),
                style = MaterialTheme.typography.h3,
                letterSpacing = 12.sp
            )
        }
    }
}


@Composable
fun SelectOptionButton(
    @StringRes labelResourceId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .widthIn(min = 250.dp)
            .padding(top = 30.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ButtonColor
        )
    ) {
        Text(stringResource(labelResourceId), style = MaterialTheme.typography.h4)
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    StartScreen(navController = NavHostController(LocalContext.current))
}