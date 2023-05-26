package com.torregrosa.faunaval.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.torregrosa.faunaval.AnimalViewModel
import com.torregrosa.faunaval.model.Animal
import com.torregrosa.faunaval.model.Foto
import com.torregrosa.faunaval.ui.theme.BackgroundColor
import com.torregrosa.faunaval.ui.theme.ButtonColor
import kotlinx.coroutines.runBlocking


@Composable
fun AnimalCard(animal: Animal, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = ButtonColor,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = animal.fotosDTO[0].url,
                contentDescription = null,
                modifier = Modifier
                    .size(130.dp, 100.dp)
                    .padding(5.dp),
            )
            Column(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = animal.nombreComun,
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.h4
                )
                Text(
                    text = animal.nombreCient,
                    modifier = Modifier.padding(1.dp),
                    style = MaterialTheme.typography.body2
                )
            }
        }

    }
}

@Composable
private fun AnimalList(
    navController: NavHostController,
    animalList: List<Animal>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundColor),
        contentPadding = PaddingValues(10.dp),
    ) {
        items(animalList) { animal: Animal ->
            AnimalCard(animal = animal, { navController.navigate("AnimalDetail/${animal.id}") })
        }
    }
}

@Composable
fun AnimalListScreen(
    navController: NavHostController,
    categoryId: Int,
    viewModel: AnimalViewModel = hiltViewModel()
) {
    AnimalList(navController, runBlocking { viewModel.getAnimalList(categoryId) })
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    AnimalCard(
        animal = Animal(
            1, "Zorro", "Raposa", "Marrón", "Ordenad", "Familiar", "SuperBonico",
            listOf(Foto(1, "https://bddb.gva.es/bancodedatos/imagenes/BIO_IMAGENES/6_5939.jpg"))
        ), onClick = {}
    )
}
