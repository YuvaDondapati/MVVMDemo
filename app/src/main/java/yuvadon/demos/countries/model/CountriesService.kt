package yuvadon.demos.countries.model
import io.reactivex.Single
import yuvadon.demos.countries.BuildConfig
import yuvadon.demos.countries.di.DaggerApiComponent
import javax.inject.Inject

class CountriesService {

//    private val BASE_URL = "https://raw.githubusercontent.com"

    @Inject
    lateinit var api: CountriesApi

    init {
     DaggerApiComponent.builder()
         .baseUrl(BuildConfig.BASE_URL)
         .build().inject(this)
    }

    fun getCountries(): Single<List<Country>>{
        return api.getCountries()
    }
}