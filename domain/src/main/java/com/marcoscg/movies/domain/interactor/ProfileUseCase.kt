package com.marcoscg.movies.domain.interactor

import com.marcoscg.movies.domain.repository.MoviesRemoteRepository
import com.marcoscg.movies.model.DataUserResponse
import com.marcoscg.movies.model.LoginResponse
import com.marcoscg.movies.model.UserLogin
import io.reactivex.Single

class ProfileUseCase (private val moviesRemoteRepository: MoviesRemoteRepository) {
    fun execute(token: String) : Single<DataUserResponse> {
        return moviesRemoteRepository.getDataUser(token)
    }
}