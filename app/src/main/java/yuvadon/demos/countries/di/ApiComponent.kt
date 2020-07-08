package yuvadon.demos.countries.di

import dagger.BindsInstance
import dagger.Component
import yuvadon.demos.countries.model.CountriesService


@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(countriesService: CountriesService)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun baseUrl(baseUrl: String): Builder
        fun build(): ApiComponent
    }
}