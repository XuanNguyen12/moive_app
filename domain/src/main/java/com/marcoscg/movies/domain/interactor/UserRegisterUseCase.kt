package com.marcoscg.movies.domain.interactor

import com.marcoscg.movies.domain.repository.MoviesRemoteRepository
import com.marcoscg.movies.model.RegisterStatus
import com.marcoscg.movies.model.UserRegister
import io.reactivex.Single
import javax.xml.ws.Response

class UserRegisterUseCase (private val moviesRemoteRepository: MoviesRemoteRepository) {
    fun execute (userRegister: UserRegister) : Single<RegisterStatus> {
        return moviesRemoteRepository.registerUser(userRegister)
    }
}