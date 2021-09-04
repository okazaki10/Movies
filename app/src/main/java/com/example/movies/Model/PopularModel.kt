package com.example.movies.Model

import com.google.gson.annotations.SerializedName

data class PopularModel(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<PopularListModel>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)