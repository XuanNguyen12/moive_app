package com.marcoscg.movies.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import com.marcoscg.movies.data.Resource
import com.marcoscg.movies.domain.interactor.ProfileUseCase
import com.marcoscg.movies.domain.interactor.UserRegisterUseCase
import com.marcoscg.movies.model.DataUserResponse
import com.marcoscg.movies.model.RegisterStatus
import com.marcoscg.movies.model.UserRegister
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel(private val profileUseCase: ProfileUseCase) : ViewModel() {

    private val stateFlow = MutableStateFlow<Resource<DataUserResponse>>(Resource.empty())
    var disposable: Disposable? = null

    val profileState: StateFlow<Resource<DataUserResponse>>
        get() = stateFlow

    fun getData() {
        stateFlow.value = Resource.loading()
        disposable = profileUseCase.execute("")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                stateFlow.value = Resource.success(res)
            }, { throwable ->
                throwable.localizedMessage?.let {
                    stateFlow.value = Resource.error(it)
                }
            })
    }

}