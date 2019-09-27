package yuvadon.demos.countries.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import yuvadon.demos.countries.R
import yuvadon.demos.countries.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

   lateinit var viewModel: ListViewModel
   private val countriesAdapter = CountryListAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        //recyclerview setup we can directly access with id (countriesList)
        countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter =countriesAdapter
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.countries.observe(this, Observer {
            countries -> countries?.let { countriesAdapter.updateCountries(it) }
        })
    }
}
