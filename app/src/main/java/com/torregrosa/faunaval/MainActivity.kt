package com.torregrosa.faunaval

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.torregrosa.faunaval.ui.theme.FaunaValTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FaunaValTheme {
                FaunaValApp()
            }
        }
    }
}