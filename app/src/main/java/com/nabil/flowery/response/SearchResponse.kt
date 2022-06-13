package com.nabil.flowery.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class SearchResponse(
    var error: String? = null,
    var message: String? = null,
    var result: List<ListFlower>
)

@Parcelize
data class ListFlower(
    var _id: String? = null,
    var global_name: String? = null,
    var local_name: String? = null,
    var images: String? = null
) : Parcelable
