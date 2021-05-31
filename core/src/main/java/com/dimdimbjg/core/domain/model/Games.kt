package com.dimdimbjg.core.domain.model

data class Games(
    val id: Int,
    val backgroundImage: String,
    val name: String,
    val isFavorites: Boolean = false
)

