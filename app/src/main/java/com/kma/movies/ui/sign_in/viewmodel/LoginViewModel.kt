package com.kma.movies.ui.sign_in.viewmodel

import androidx.lifecycle.ViewModel
import com.kma.movies.data.Resource
import com.kma.movies.domain.interactor.UserLoginUseCase
import com.kma.movies.model.LoginResponse
import com.kma.movies.model.UserLogin
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel (private val userLoginUseCase: UserLoginUseCase) : ViewModel() {

    private val stateFlow = MutableStateFlow<Resource<LoginResponse>>(Resource.empty())
    var disposable: Disposable? = null

    val userSignUpState: StateFlow<Resource<LoginResponse>>
        get() = stateFlow

    fun register(userLogin: UserLogin) {
        stateFlow.value = Resource.loading()

        disposable = userLoginUseCase.execute(userLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                stateFlow.value = Resource.success(res)
                stateFlow.value = Resource.empty()
            }, { throwable ->
                throwable.localizedMessage?.let {
                    stateFlow.value = Resource.error(it)
                }
            })
    }

}