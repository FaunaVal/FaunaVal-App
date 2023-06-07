package com.torregrosa.faunaval.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.torregrosa.faunaval.R
import com.torregrosa.faunaval.model.Animal
import com.torregrosa.faunaval.model.Foto
import com.torregrosa.faunaval.ui.theme.BackgroundColor
import com.torregrosa.faunaval.ui.theme.ButtonColor
import com.torregrosa.faunaval.ui.theme.TextBackgroundColor

@Composable
fun AnimalDetail(animal: Animal) {
    AnimalDetailColumn(animal = animal)
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun AnimalDetailColumn(animal: Animal) {
    val pagerState = rememberPagerState(pageCount = animal.fotos.size)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = ScrollState(0))
            .background(BackgroundColor),
        verticalArrangement = Arrangement.Top,
    ) {
        HorizontalPager(state = pagerState) { page ->
            val imageUrl = animal.fotos[page]
            Box(modifier = Modifier.fillMaxSize()) {
                AnimalImage(url = imageUrl.url)
            }
        }
        if (animal.fotos.size > 1) {
            DotsIndicator(totalDots = animal.fotos.size, selectedIndex = pagerState.currentPage)
        }
        InfoColumn(animal = animal)
        DescriptionColumn(description = animal.descripcion)
    }
}

@Composable
fun DescriptionColumn(description: String) {
    Text(
        text = stringResource(id = R.string.descripcion),
        modifier = Modifier
            .padding(start = 10.dp),
        color = Color.White,
        style = MaterialTheme.typography.h6,
    )
    Box(
        modifier = Modifier
            .padding(10.dp)
            .background(TextBackgroundColor.copy(alpha = 0.5f))
            .fillMaxSize()
    ) {
        Text(
            text = description,
            modifier = Modifier
                .padding(10.dp),
            color = Color.White,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun InfoColumn(animal: Animal) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(
            text = animal.nombreComun,
            letterSpacing = 5.sp,
            style = MaterialTheme.typography.h3,
            color = Color.White,
        )
        Text(
            text = animal.nombreCient,
            style = MaterialTheme.typography.h4,
            fontStyle = FontStyle.Italic,
            color = Color.White,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        TextInfoH6(info = stringResource(id = R.string.familia, animal.familia))
        TextInfoH6(info = stringResource(id = R.string.orden, animal.orden))
        TextInfoH6(info = stringResource(id = R.string.clase, animal.clase))
    }
}

@Composable
fun TextInfoH6(info: String) {
    Text(
        text = info,
        style = MaterialTheme.typography.h5,
        color = Color.White,
        modifier = Modifier.padding(bottom = 5.dp)
    )
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int
) {
    LazyRow(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
    ) {
        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = ButtonColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = TextBackgroundColor.copy(alpha = 0.5f))
                )
            }
            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun AnimalImage(url: String) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 1.dp),
        contentScale = ContentScale.FillWidth,
    )
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    AnimalDetailColumn(
        animal = Animal(
            1, "Zorro Rojo Y Marrón", "Raposa", "Marrón", "Ordenad", "Familiar",
            "El zorro es un cánido bien conocido y fácil de identificar, en buena medida por ser personaje común en cuentos y en el imaginario popular. Se caracteriza por su larga y poblada cola, rostro enmarcado por grandes orejas y afilado hocico y peso entre 3 y 8 kilogramos, siendo mayores los machos. Su organización social es plástica, con ejemplares solitarios, otros emparejados, o formando pequeños grupos familiares. Entra en celo en invierno, produciéndose los partos en primavera con camadas entre una y siete crías, que se dispersarán del núcleo familiar a partir de finales de verano y que pueden reproducirse a partir del año de edad. Su actividad es esencialmente nocturna, aunque puede verse a la luz del día tras el amanecer o antes del ocaso. Se trata de un carnívoro oportunista, que aprovecha distintas fuentes de alimentación según su abundancia en cada lugar y época del año. En la provincia de Valencia, Urios (1990) determinó que la aparición más frecuente en estómagos y excrementos era materia vegetal (48,8 %), seguida de aves (26,3 %), invertebrados (17,0 %), micromamíferos (13,4 %) y carroña (12,3 %). En el entorno de poblaciones humanas la basura puede ser parte importante de su dieta",
            listOf(Foto(1, "https://bddb.gva.es/bancodedatos/imagenes/BIO_IMAGENES/6_5939.jpg"))
        )
    )
}