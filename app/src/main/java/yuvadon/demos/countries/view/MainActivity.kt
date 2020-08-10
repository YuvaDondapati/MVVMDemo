package yuvadon.demos.countries.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import yuvadon.demos.countries.R
import yuvadon.demos.countries.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

   private val viewModel: ListViewModel by viewModels()
   private val countriesAdapter = CountryListAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        //recyclerview setup we can directly access with id (countriesList)


        countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        swiperefereshLayout.setOnRefreshListener {
            swiperefereshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.countries.observe(this, Observer {
            countries -> countries?.let {
            countriesList.visibility = View.VISIBLE
            countriesAdapter.updateCountries(it) }
        })
        viewModel.loadError.observe(this, Observer {
            isError -> isError?.let { errorText.visibility = if(it) View.VISIBLE else View.GONE }
        })

        viewModel.isLoading.observe(this, Observer {
            isLoading -> isLoading?.let {
            loading_bar.visibility = if(it) View.VISIBLE else View.GONE
            if(it){
                errorText.visibility = View.GONE
                countriesList.visibility = View.GONE
            }
        }
        })
    }
}
