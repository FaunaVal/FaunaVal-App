package com.torregrosa.faunaval.ui

import androidx.annotation.DrawableRes
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.torregrosa.faunaval.R
import com.torregrosa.faunaval.ui.theme.BackgroundColor
import com.torregrosa.faunaval.ui.theme.ButtonColor
import com.torregrosa.faunaval.ui.theme.TextBackgroundColor


@Composable
fun CategoriesScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .background(BackgroundColor)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        Title()
        Spacer(modifier = Modifier.height(20.dp))
        ButtonGrid(navController)
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .height(70.dp)
            .background(TextBackgroundColor.copy(alpha = 0.5f)),

        Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.categorias).uppercase(),
            style = MaterialTheme.typography.h3,
            letterSpacing = 5.sp
        )
    }
}

@Composable
fun SelectCategoryButton(
    @StringRes labelResourceId: Int,
    @DrawableRes drawableResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(140.dp)
            .width(140.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ButtonColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = drawableResId),
                contentDescription = null,
                modifier = Modifier.size(140.dp, 100.dp),
                alignment = Alignment.TopCenter
            )
            Text(stringResource(labelResourceId), style = MaterialTheme.typography.h6)
        }
    }
}

@Composable
fun ButtonGrid(navController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        SelectCategoryButton(
            labelResourceId = R.string.mamiferos,
            drawableResId = R.drawable.mamifero,
            onClick = { navController.navigate("AnimalList/1") })
        SelectCategoryButton(
            labelResourceId = R.string.aves,
            drawableResId = R.drawable.ave,
            onClick = { navController.navigate("AnimalList/2") })
    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        SelectCategoryButton(
            labelResourceId = R.string.reptiles,
            drawableResId = R.drawable.reptil,
            onClick = { navController.navigate("AnimalList/3") })
        SelectCategoryButton(
            labelResourceId = R.string.anfibios,
            drawableResId = R.drawable.anfibio,
            onClick = { navController.navigate("AnimalList/4") })
    }
}


@Preview(showBackground = true)
@Composable
fun CategoriesScreenPreview() {
    CategoriesScreen(navController = NavHostController(LocalContext.current))
}