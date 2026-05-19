package io.github.sushaant.maintenancetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import io.github.sushaant.maintenancetracker.navigation.NavGraph
import io.github.sushaant.maintenancetracker.ui.screens.home.HomeScreen
import io.github.sushaant.maintenancetracker.ui.theme.MaintenanceTrackerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            MaintenanceTrackerTheme {

                val navController = rememberNavController()

                NavGraph(
                    navController = navController
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Preview() {
    HomeScreen({})
}