package com.torregrosa.faunaval

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.torregrosa.faunaval.repository.AnimalRepository
import com.torregrosa.faunaval.ui.AnimalListScreen
import com.torregrosa.faunaval.ui.CategoriesScreen
import com.torregrosa.faunaval.ui.StartScreen
import com.torregrosa.faunaval.ui.theme.BackgroundColor

@Composable
fun FaunaValAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name), color = Color.White) },
        modifier = modifier,
        backgroundColor = BackgroundColor,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Atrás"
                    )
                }
            }
        }
    )
}

@Composable
fun FaunaValApp( modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    // TODO: Get current back stack entry

    // TODO: Get the name of the current screen

    Scaffold(
        topBar = {
            FaunaValAppBar(
                canNavigateBack = false,
                navigateUp = { /* TODO: implement back navigation */ }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Start",
            modifier = modifier.padding(innerPadding)
        )
        {
            composable("Start") {
                StartScreen(navController)

            }
            composable("Categories") {
                CategoriesScreen(navController)
            }
            composable("AnimalList") {
                AnimalListScreen(navController)
            }
        }
    }
}