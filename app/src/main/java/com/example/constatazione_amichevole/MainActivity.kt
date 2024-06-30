package com.example.constatazione_amichevole

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.constatazione_amichevole.ui.theme.Constatazione_amichevoleTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }

        }

    }



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Constatazione_amichevoleTheme {

    }
}