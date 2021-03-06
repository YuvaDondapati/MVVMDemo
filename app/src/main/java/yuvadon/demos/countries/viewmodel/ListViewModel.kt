package yuvadon.demos.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import yuvadon.demos.countries.BuildConfig
import yuvadon.demos.countries.di.DaggerApiComponent
import yuvadon.demos.countries.model.CountriesApi
import yuvadon.demos.countries.model.CountriesService
import yuvadon.demos.countries.model.Country
import yuvadon.demos.countries.view.CountryListAdapter
import javax.inject.Inject

class ListViewModel: ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val loadError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    @Inject
    lateinit var countriesService: CountriesService
    private val disposable = CompositeDisposable()

    init {
        DaggerApiComponent.builder().baseUrl(BuildConfig.BASE_URL).build().injectVM(this)
    }
    fun  refresh(){
        fetchCountries()
    }

    private fun fetchCountries() {

        /*
        Mockdata in case of demo purpose
         */
       /* val mockData = listOf(Country("CountryA"),
            Country("CountryB"),
            Country("CountryC"),
            Country("CountryD"),
            Country("CountryE"),
            Country("CountryF")
        )*/

        isLoading.value = true
        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(countriesList: List<Country>) {
                        countries.value = countriesList
                        isLoading.value = false
                        loadError.value = false
                    }

                    override fun onError(e: Throwable) {
                        isLoading.value = false
                        loadError.value = true
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}