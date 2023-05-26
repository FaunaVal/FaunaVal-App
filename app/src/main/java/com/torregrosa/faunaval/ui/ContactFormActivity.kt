package com.torregrosa.faunaval.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.torregrosa.faunaval.R
import com.torregrosa.faunaval.ui.theme.BackgroundColor
import com.torregrosa.faunaval.ui.theme.ButtonColor

@Composable
fun ContactForm() {
    ContactFormPage()
}

@Composable
fun ContactFormPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormColumn()
    }

}

@Composable
fun FormColumn() {
    val email = stringResource(id = R.string.email)
    val chooseClientText = stringResource(R.string.elige_cliente)
    val senderName = remember {
        mutableStateOf(TextFieldValue())
    }
    val emailSubject = remember {
        mutableStateOf(TextFieldValue())
    }
    val emailBody = remember {
        mutableStateOf(TextFieldValue())
    }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(ButtonColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomNameField(nameBody = senderName, hint = stringResource(R.string.introduce_nombre))
        CustomEmailField(emailBody = emailSubject, hint = stringResource(R.string.introduce_asunto))
        CustomTextField(textBody = emailBody, hint = stringResource(R.string.escribe_mensaje))
    }
    Button(
        modifier = Modifier
            .widthIn(min = 250.dp)
            .padding(top = 30.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ButtonColor
        ),
        onClick = {
            val i = Intent().apply {
                action = Intent.ACTION_SEND
                data = Uri.parse("mailto:")
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                putExtra(Intent.EXTRA_SUBJECT, emailSubject.value.text)
                putExtra(Intent.EXTRA_TEXT, senderName.value.text + System.lineSeparator() + emailBody.value.text)
            }
            i.type = "message/rfc822"
            context.startActivity(Intent.createChooser(i, chooseClientText))

        }) {
        Text(
            text = stringResource(R.string.enviar_boton),
            style = MaterialTheme.typography.h4
        )
    }
}


@Composable
fun CustomNameField(nameBody: MutableState<TextFieldValue>, hint: String) {
    TextField(
        value = nameBody.value,
        onValueChange = { nameBody.value = it },
        label = { Text(text = hint) },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp)),
        textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
        singleLine = true,
        colors = MyAppTextFieldColors()
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CustomEmailField(emailBody: MutableState<TextFieldValue>, hint: String) {
    TextField(
        value = emailBody.value,
        onValueChange = { emailBody.value = it },
        label = { Text(text = hint) },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp)),
        textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
        singleLine = true,
        colors = MyAppTextFieldColors()
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CustomTextField(textBody: MutableState<TextFieldValue>, hint: String) {
    TextField(
        value = textBody.value,
        onValueChange = { textBody.value = it },
        label = { Text(text = hint) },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(120.dp)
            .clip(shape = RoundedCornerShape(10.dp)),
        textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
        colors = MyAppTextFieldColors()
    )
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun MyAppTextFieldColors() = TextFieldDefaults.textFieldColors(
    backgroundColor = Color.White,
    focusedIndicatorColor = BackgroundColor,
    focusedLabelColor = BackgroundColor,
)

@Preview(showBackground = true)
@Composable
fun PreviewForm() {
    ContactFormPage()
}