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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.torregrosa.faunaval.R
import com.torregrosa.faunaval.data.DataSource.buttonsOptions
import com.torregrosa.faunaval.ui.theme.BackgroundColor
import com.torregrosa.faunaval.ui.theme.ButtonColor
import com.torregrosa.faunaval.ui.theme.TextBackgroundColor

@Composable
fun StartScreen(
    quantityOptions: List<Pair<Int, Int>>,
    // TODO: add onNextButtonClicked
    modifier: Modifier = Modifier
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
        quantityOptions.forEach { item ->
            Spacer(modifier = Modifier.height(30.dp))
            SelectQuantityButton(
                labelResourceId = item.first,
                onClick = { /* TODO: handle next button */ }
            )
        }
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

/**
 * Customizable button composable that displays the [labelResourceId]
 * and triggers [onClick] lambda when this composable is clicked
 */
@Composable
fun SelectQuantityButton(
    @StringRes labelResourceId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.widthIn(min = 250.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ButtonColor

        )
    ) {
        Text(stringResource(labelResourceId), style = MaterialTheme.typography.h4)
    }
}

@Preview(showBackground = true)
@Composable
fun StartOrderPreview() {
    StartScreen(quantityOptions = buttonsOptions)
}