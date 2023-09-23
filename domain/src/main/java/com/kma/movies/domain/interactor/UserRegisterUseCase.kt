package com.kma.movies.domain.interactor

import com.kma.movies.domain.repository.MoviesRemoteRepository
import com.kma.movies.model.RegisterStatus
import com.kma.movies.model.UserRegister
import io.reactivex.Single

class UserRegisterUseCase (private val moviesRemoteRepository: MoviesRemoteRepository) {
    fun execute (userRegister: UserRegister) : Single<RegisterStatus> {
        return moviesRemoteRepository.registerUser(userRegister)
    }
}