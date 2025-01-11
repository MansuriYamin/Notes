package com.example.notes.ui.navigation

import kotlinx.serialization.Serializable

// Destinations for dashboard navgraph
sealed class DashboardDestinations {
    @Serializable
    data object HomeScreenDestination : DashboardDestinations()

    @Serializable
    data object AddEditNoteScreenDestination : DashboardDestinations()
}