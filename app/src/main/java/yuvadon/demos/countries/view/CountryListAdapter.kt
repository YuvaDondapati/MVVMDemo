package yuvadon.demos.countries.view
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_country.view.*
import yuvadon.demos.countries.R
import yuvadon.demos.countries.model.Country
import yuvadon.demos.countries.utils.getProgressDrawable
import yuvadon.demos.countries.utils.loadImage

class CountryListAdapter(var countries: ArrayList<Country>): RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries: List<Country>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    /*
   create the viewholder by passing view which we created (item_country)
    */
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
     holder.bind(countries[position])
    }

    class CountryViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val countryName = view.name
        private val imageView = view.imageView
        private val capital = view.capital

        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country){
            countryName.text = country.countryName
            capital.text = country.capital
            imageView.loadImage(country.flag, progressDrawable)
        }
    }
}