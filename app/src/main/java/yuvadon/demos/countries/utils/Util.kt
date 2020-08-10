package yuvadon.demos.countries.utils

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import yuvadon.demos.countries.R

fun getProgressDrawable(context: Context): androidx.swiperefreshlayout.widget.CircularProgressDrawable {

    return androidx.swiperefreshlayout.widget.CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(
    url: String?,
    progressDrawable: androidx.swiperefreshlayout.widget.CircularProgressDrawable
){
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}