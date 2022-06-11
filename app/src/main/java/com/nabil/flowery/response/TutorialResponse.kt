package com.nabil.flowery.response

data class TutorialResponse(
    var error: String,
    var message: String,
    var result: TutorialResult
)

data class TutorialResult(
    var _id: String,
    var flower_id: String,
    var plant: List<String>,
    var take_care: List<String>
)
