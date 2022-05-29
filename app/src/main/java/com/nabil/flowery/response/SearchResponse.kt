package com.nabil.flowery.response

data class SearchResponse(
    var error: String? = null,
    var message: String? = null,
    var result: List<ListFlower>
)

data class ListFlower(
    var _id: String? = null,
    var global_name: String? = null,
    var local_name: String? = null,
    var images: String? = null
)
