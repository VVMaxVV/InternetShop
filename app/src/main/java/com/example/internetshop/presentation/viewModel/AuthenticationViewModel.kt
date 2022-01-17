package com.example.internetshop.presentation.viewModel

import androidx.databinding.ObservableField
import com.example.internetshop.data.cache.TokenPreference
import com.example.internetshop.domain.data.model.UserCredentials
import com.example.internetshop.domain.data.repository.AuthRepository
import com.example.internetshop.domain.data.usecase.impl.GetAuthUseCaseImpl
import com.example.internetshop.presentation.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthenticationViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenPreference: TokenPreference,
    private val getAuthUseCaseImpl: GetAuthUseCaseImpl
) :
    BaseViewModel() {
    val navEventLiveData = SingleLiveEvent<Event>()

    val password = ObservableField("83r5^_")
    val username = ObservableField("mor_2314")

    fun onScreenStart() {
        val token = tokenPreference.getToken()
        if (token.token.isNullOrEmpty().not()) {
            navEventLiveData.value = Event.OpenProductListEvent
        }
    }

    fun getToken() {
        if (username.get().isNullOrEmpty() || password.get().isNullOrEmpty()) {
            Event.ToastEvent("Fill in all the fields!")
        } else {
            compositeDisposable.add(
                getAuthUseCaseImpl.execute(UserCredentials(username.get()!!, password.get()!!))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        tokenPreference.setToken(it)
                        navEventLiveData.value = Event.OpenProductListEvent
                    },
                        {
                            Event.ToastEvent(it.message ?: "Error")
                        })
            )
        }
    }

    sealed class Event {
        object OpenProductListEvent : Event()
        data class ToastEvent(val text: String) : Event()
    }

    fun onGoogleClick() {
        Event.ToastEvent("Google clicked")
    }
}