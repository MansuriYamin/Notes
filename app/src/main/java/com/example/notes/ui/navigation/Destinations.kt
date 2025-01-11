package com.example.notes.ui.navigation

import kotlinx.serialization.Serializable

// Destinations for dashboard navgraph
sealed class DashboardDestinations {
    @Serializable
    data object HomeDestination : DashboardDestinations()

    @Serializable
    data class NoteDetailsDestination(val id: Int? = null) : DashboardDestinations()
}