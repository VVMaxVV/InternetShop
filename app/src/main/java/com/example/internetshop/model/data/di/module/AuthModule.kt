package com.example.internetshop.model.data.di.module

import com.example.internetshop.data.repository.AuthImpl
import com.example.internetshop.data.response.mapper.UserCredentialsMapper
import com.example.internetshop.data.retrofitapi.AuthApi
import com.example.internetshop.domain.data.repository.LoginRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {
    @Provides
    fun getAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    fun getUserCredentialsMapper(): UserCredentialsMapper{
        return UserCredentialsMapper()
    }

    @Provides
    fun getAuthRepository(authApi: AuthApi, userCredentialsMapper: UserCredentialsMapper): LoginRepository {
        return AuthImpl(authApi, userCredentialsMapper)
    }
}