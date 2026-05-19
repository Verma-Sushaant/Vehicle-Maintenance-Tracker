package io.github.sushaant.maintenancetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.sushaant.maintenancetracker.navigation.AppNavigation
import io.github.sushaant.maintenancetracker.ui.theme.MaintenanceTrackerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            MaintenanceTrackerTheme {

                AppNavigation()
            }
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    HomeScreen({},{})
//}