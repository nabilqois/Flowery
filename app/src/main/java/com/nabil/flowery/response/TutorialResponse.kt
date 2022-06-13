package com.nabil.flowery.response

data class TutorialResponse(
    var error: String,
    var message: String,
    var result: List<TutorialResult>
)

data class TutorialResult(
    var _id: String,
    var global_name: String,
    var local_name: String,
    var images: String,
    var plant: List<List<String>>,
    var take_care: List<List<String>>
)
