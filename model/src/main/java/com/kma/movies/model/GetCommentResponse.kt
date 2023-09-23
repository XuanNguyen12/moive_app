package com.kma.movies.model


data class GetCommentResponse(

    var Id: String? = null,
    var userId: UserId? = UserId(),
    var movieId: String? = null,
    var content: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,

)