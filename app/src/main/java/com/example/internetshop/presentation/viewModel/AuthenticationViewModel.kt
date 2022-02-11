package com.example.internetshop.presentation.viewModel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.internetshop.R
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
    val events = SingleLiveEvent<AuthenticationEvent>()

    val progressBarVisibilityLiveData = MutableLiveData<Boolean>()

    val password = ObservableField("83r5^_")
    val username = ObservableField("mor_2314")

    fun onScreenStart() {
        val token = tokenPreference.getToken()
        if (token.token.isNullOrEmpty().not()) {
            events.value = AuthenticationEvent.OpenProductListAuthenticationEvent
        }
    }

    fun getToken() {
        if (username.get().isNullOrEmpty() || password.get().isNullOrEmpty()) {
            events.value =
                AuthenticationEvent.ToastAuthenticationEvent(R.string.error_fill_all_fields)
        } else {
            progressBarVisibilityLiveData.value = true
            compositeDisposable.add(
                getAuthUseCase.execute(UserCredentials(username.get()!!, password.get()!!))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        tokenPreference.setToken(it)
                        events.value =
                            AuthenticationEvent.OpenProductListAuthenticationEvent
                    },
                        {
                            Log.e("Error", "AuthenticationViewModel error: ${it.message}")
                            progressBarVisibilityLiveData.value = false
                            events.value =
                                AuthenticationEvent.ServerNotResponseEvent
                        })
            )
        }
    }

    sealed class AuthenticationEvent {
        object OpenProductListAuthenticationEvent : AuthenticationEvent()
        object ServerNotResponseEvent: AuthenticationEvent()
        data class ToastAuthenticationEvent(val textId: Int) : AuthenticationEvent()
    }

    fun onGoogleClick() {
        events.value = AuthenticationEvent.ToastAuthenticationEvent(R.string.label_google_clicked)
    }
}