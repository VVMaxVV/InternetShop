package com.example.internetshop.presentation.viewModel

import android.util.Log
import androidx.databinding.ObservableField
import com.example.internetshop.data.cache.TokenPreference
import com.example.internetshop.domain.data.model.UserCredentials
import com.example.internetshop.domain.data.usecase.AuthUseCase
import com.example.internetshop.presentation.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
            navEventLiveData.value =
                AuthenticationEvent.ToastAuthenticationEvent("Fill in all the fields!")
        } else {
            navEventLiveData.value =
                AuthenticationEvent.SetProgressBarVisibilityEvent(true)
            compositeDisposable.add(
                getAuthUseCase.execute(UserCredentials(username.get()!!, password.get()!!))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        tokenPreference.setToken(it)
                        navEventLiveData.value =
                            AuthenticationEvent.OpenProductListAuthenticationEvent
                    },
                        {
                            Log.e("Error", "AuthenticationViewModel error: ${it.message}")
                            navEventLiveData.value =
                                AuthenticationEvent.SetProgressBarVisibilityEvent(false)
                            navEventLiveData.value =
                                AuthenticationEvent.ServerNotResponseEvent
                        })
            )
        }
    }

    sealed class AuthenticationEvent {
        object OpenProductListAuthenticationEvent : AuthenticationEvent()
        object ServerNotResponseEvent: AuthenticationEvent()
        data class SetProgressBarVisibilityEvent(val visibility: Boolean) : AuthenticationEvent()
        data class ToastAuthenticationEvent(val text: String) : AuthenticationEvent()
    }

    fun onGoogleClick() {
        navEventLiveData.value = AuthenticationEvent.ToastAuthenticationEvent("Google clicked")
    }
}