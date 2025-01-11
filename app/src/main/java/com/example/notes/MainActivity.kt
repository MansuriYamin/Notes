package com.example.notes

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.notes.ui.home.HomeScreen
import com.example.notes.ui.home.NoteSharedViewModel
import com.example.notes.ui.navigation.DashboardDestinations
import com.example.notes.ui.notes.NoteDetailsScreen
import com.example.notes.ui.theme.NotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                        startDestination = DashboardDestinations.HomeDestination
                    ) {
                        composable<DashboardDestinations.HomeDestination> { backStackEntry ->
                            val noteSharedViewModel =
                                hiltViewModel<NoteSharedViewModel>(backStackEntry)
                            HomeScreen(
                                noteSharedViewModel = noteSharedViewModel,
                                onNoteClick = { id ->
                                    Log.d("TAG", "onCreate: onnoteclick $id")
                                    navController.navigate(
                                        DashboardDestinations.NoteDetailsDestination(id = id)
                                    )
                                },
                            )
                        }

                        composable<DashboardDestinations.NoteDetailsDestination> { backStackEntry ->
                            val noteDetailsDestination = backStackEntry.toRoute<DashboardDestinations.NoteDetailsDestination>()
                            Log.d("TAG", "onCreate: databack $id")
                            navController.previousBackStackEntry?.let {
                                val noteSharedViewModel = hiltViewModel<NoteSharedViewModel>(it)
                                NoteDetailsScreen(
                                    noteSharedViewModel = noteSharedViewModel,
                                    id = noteDetailsDestination.id,
                                    onNoteSaved = {
                                        navController.popBackStack()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
