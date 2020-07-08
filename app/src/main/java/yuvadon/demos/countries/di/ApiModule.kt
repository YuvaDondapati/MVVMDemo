package yuvadon.demos.countries.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import yuvadon.demos.countries.model.CountriesApi

@Module
class ApiModule {

    @Provides
    fun provideRetrofit(baseUrl: String): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideCountriesApi(retrofit: Retrofit):CountriesApi{
        return retrofit.create(CountriesApi::class.java)
    }

}