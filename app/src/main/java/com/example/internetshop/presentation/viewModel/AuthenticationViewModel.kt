package com.example.internetshop.presentation.viewModel

import androidx.databinding.ObservableField
import com.example.internetshop.data.cache.TokenPreference
import com.example.internetshop.domain.data.model.UserCredentials
import com.example.internetshop.domain.data.usecase.AuthUseCase
import com.example.internetshop.presentation.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthenticationViewModel @Inject constructor(
    private val tokenPreference: TokenPreference,
    private val getAuthUseCase: AuthUseCase
) : BaseViewModel() {
    val navEventLiveData = SingleLiveEvent<AuthenticationEvent>()

    val password = ObservableField("83r5^_")
    val username = ObservableField("mor_2314")

    fun onScreenStart() {
        val token = tokenPreference.getToken()
        if (token.token.isNullOrEmpty().not()) {
            navEventLiveData.value = AuthenticationEvent.OpenProductListAuthenticationEvent
        }
    }

    fun getToken() {
        if (username.get().isNullOrEmpty() || password.get().isNullOrEmpty()) {
            AuthenticationEvent.ToastAuthenticationEvent("Fill in all the fields!")
        } else {
            compositeDisposable.add(
                getAuthUseCase.execute(UserCredentials(username.get()!!, password.get()!!))
                    .timeout(10, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        tokenPreference.setToken(it)
                        navEventLiveData.value =
                            AuthenticationEvent.OpenProductListAuthenticationEvent
                    },
                        {
                            AuthenticationEvent.ToastAuthenticationEvent(it.message ?: "Error")
                        })
            )
        }
    }

    sealed class AuthenticationEvent {
        object OpenProductListAuthenticationEvent : AuthenticationEvent()
        data class ToastAuthenticationEvent(val text: String) : AuthenticationEvent()
    }

    fun onGoogleClick() {
        navEventLiveData.value = AuthenticationEvent.ToastAuthenticationEvent("Google clicked")
    }
}