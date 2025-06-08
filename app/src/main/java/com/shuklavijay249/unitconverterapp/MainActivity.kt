package com.shuklavijay249.unitconverterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.shuklavijay249.unitconverterapp.compose.BaseScreen
import com.shuklavijay249.unitconverterapp.data.ConverterDatabase
import com.shuklavijay249.unitconverterapp.data.ConverterRepositoryImpl
import com.shuklavijay249.unitconverterapp.ui.theme.UnitConverterAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var factory : ConverterViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UnitConverterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                  BaseScreen(factory = factory)
                }
            }
        }
    }
}

