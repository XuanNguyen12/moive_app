package com.kma.movies.ui.sign_in.viewmodel

import androidx.lifecycle.ViewModel
import com.kma.movies.data.Resource
import com.kma.movies.domain.interactor.UserRegisterUseCase
import com.kma.movies.model.RegisterStatus
import com.kma.movies.model.UserRegister
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel(private val userRegisterUseCase: UserRegisterUseCase) : ViewModel() {

    private val stateFlow = MutableStateFlow<Resource<RegisterStatus>>(Resource.empty())
    var disposable: Disposable? = null

    val userSignUpState: StateFlow<Resource<RegisterStatus>>
        get() = stateFlow

    fun register(userRegister: UserRegister) {
        stateFlow.value = Resource.loading()

        disposable = userRegisterUseCase.execute(userRegister)
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