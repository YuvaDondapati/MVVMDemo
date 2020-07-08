package yuvadon.demos.countries.di

import dagger.BindsInstance
import dagger.Component
import yuvadon.demos.countries.model.CountriesService
import yuvadon.demos.countries.viewmodel.ListViewModel


@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(countriesService: CountriesService)
    fun injectVM(viewModel: ListViewModel)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun baseUrl(baseUrl: String): Builder
        fun build(): ApiComponent
    }
}