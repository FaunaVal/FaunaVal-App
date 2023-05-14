package com.torregrosa.faunaval.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.torregrosa.faunaval.AnimalViewModel
import com.torregrosa.faunaval.model.Animal
import kotlinx.coroutines.runBlocking


@Composable
fun AnimalCard(animal: Animal, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(8.dp), elevation = 4.dp) {
        Column {
            Text(
                text = animal.nombreComun,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = animal.nombreCient,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "hola",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.h6
            )

        }
    }
}

@Composable
private fun AnimalList(animalList: List<Animal>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(animalList) { animal: Animal ->
            AnimalCard(animal = animal)
        }
    }
}

@Composable
fun AnimalListScreen(navController: NavHostController, viewModel : AnimalViewModel = hiltViewModel()) {
        AnimalList(runBlocking { viewModel.getAnimalList() })
}
