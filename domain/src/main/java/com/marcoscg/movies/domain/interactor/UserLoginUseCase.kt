package com.marcoscg.movies.domain.interactor

import com.marcoscg.movies.domain.repository.MoviesRemoteRepository
import com.marcoscg.movies.model.LoginResponse
import com.marcoscg.movies.model.UserLogin
import io.reactivex.Single

class UserLoginUseCase (private val moviesRemoteRepository: MoviesRemoteRepository) {
    fun execute(userLogin: UserLogin) : Single<LoginResponse> {
        return moviesRemoteRepository.loginUser(userLogin)
    }
}