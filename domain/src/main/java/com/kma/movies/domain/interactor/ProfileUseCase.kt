package com.kma.movies.domain.interactor

import com.kma.movies.domain.repository.MoviesRemoteRepository
import com.kma.movies.model.DataUserResponse
import io.reactivex.Single

class ProfileUseCase (private val moviesRemoteRepository: MoviesRemoteRepository) {
    fun execute() : Single<DataUserResponse> {
        return moviesRemoteRepository.getDataUser()
    }
}