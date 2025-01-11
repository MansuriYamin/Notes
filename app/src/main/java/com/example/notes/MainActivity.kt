package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notes.ui.home.HomeScreen
import com.example.notes.ui.navigation.DashboardDestinations
import com.example.notes.ui.notes.AddEditNoteScreen
import com.example.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Creating navgraph
                    val navController = rememberNavController()

                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = DashboardDestinations.HomeScreenDestination
                    ) {
                        composable<DashboardDestinations.HomeScreenDestination> {
                            HomeScreen(
                                onAddClick = {
                                    navController.navigate(DashboardDestinations.AddEditNoteScreenDestination)
                                }
                            )
                        }
                        composable<DashboardDestinations.AddEditNoteScreenDestination> {
                            AddEditNoteScreen()
                        }
                    }
                }
            }
        }
    }
}
