package com.example.internetshop.model.data.di.module

import com.example.internetshop.model.data.remote.AuthApi
import com.example.internetshop.model.implementation.AuthImpl
import com.example.internetshop.model.interfaces.LoginRepository
import com.example.internetshop.presentation.ViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {
    @Provides
    fun getAuthApi(retrofit: Retrofit): AuthApi{
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    fun getAuthRepository(authApi: AuthApi): LoginRepository {
        return AuthImpl(authApi)
    }

    @Provides
    fun getViewModelFactory(loginRepository: LoginRepository): ViewModelFactory {
        return ViewModelFactory(loginRepository)
    }


}