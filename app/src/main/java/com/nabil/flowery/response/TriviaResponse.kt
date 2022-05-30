package com.nabil.flowery.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TriviaResponse(
    var error: String? = null,
    var message: String? = null,
    var result: List<ListTrivia>
):Parcelable
@Parcelize
data class ListTrivia(
    var _id: String? = null,
    var flower_name: String? = null,
    var content_trivia: String? = null,
):Parcelable