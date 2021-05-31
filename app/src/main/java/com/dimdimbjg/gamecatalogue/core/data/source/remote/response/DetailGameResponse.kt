package com.dimdimbjg.gamecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailGameResponse(
	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("ratings_count")
	val ratingsCount: Int,

	@field:SerializedName("released")
	val released: String,

	@field:SerializedName("description_raw")
	val descriptionRaw: String,

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("background_image_additional")
	val backgroundImageAdditional: String

)
