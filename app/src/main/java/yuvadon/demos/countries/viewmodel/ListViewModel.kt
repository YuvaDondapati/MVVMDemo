package yuvadon.demos.countries.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import yuvadon.demos.countries.model.Country

class ListViewModel: ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val loadError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()


    fun refresh(){
        fetchCountries()
    }

    private fun fetchCountries(){
        val mockData = listOf(Country("CountryA"),
            Country("CountryB"),
            Country("CountryC"),
            Country("CountryD"),
            Country("CountryE"),
            Country("CountryF")
        )

        loadError.value = false
        isLoading.value = false
        countries.value = mockData
    }

}