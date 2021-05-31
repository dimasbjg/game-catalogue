package com.dimdimbjg.gamecatalogue.core.domain.model

data class Detail(
    val rating: Double = 0.0,
    val id: Int = -1,
    val ratingsCount: Int = 0,
    val released: String = "",
    val descriptionRaw: String = "",
    val backgroundImage: String = "",
    val name: String = "",
    val backgroundImageAdditional: String = ""
) {
    companion object {
        fun createEmptyObject(): Detail = Detail(id = -1)
    }
}