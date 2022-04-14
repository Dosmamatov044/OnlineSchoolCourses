package com.example.onlineschoolcourses.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.onlineschoolcourses.R


fun replaceFragmentScreen(fragment: Fragment, addStack: Boolean = true) {
    /* Функция расширения для AppCompatActivity, позволяет устанавливать фрагменты */
    if (addStack) {
        APP_SCREEN_ACTIVITY.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.fragment_containers,
                fragment
            ).commit()
    } else {
        APP_SCREEN_ACTIVITY.supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_containers,
                fragment
            ).commit()
    }

}


fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()

}

fun reverseTimer(context: Context, text: TextView, button: Button): TextView {
    var count = 30

    object : CountDownTimer(30000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            if (count != 0 && count > 0) {
                count = --count
                text.text = count.toString()
                button.background =
                    ContextCompat.getDrawable(context, R.drawable.rounded_grey_corner_backgrounde)
                button.isClickable = false
            } else {
                Log.d("ololo", "end")

            }
        }
        override fun onFinish() {
            Log.d("ololo", "done")
            text.text = "30"
            button.background =
                ContextCompat.getDrawable(context, R.drawable.rounded_corner_background)
            button.isClickable = true
        }
    }.start()
    return text
}


fun getImageFromFile(uri:Uri,context:Context,imageView:ImageView){

    if (uri!=null) {
        Glide.with(context)
            .load(uri)

            .placeholder(R.drawable.no_photography_24)
            .error(R.drawable.no_photography_24)
            .into(imageView)
    } else {
        Glide.with(context).clear(imageView)
        imageView.setImageResource(R.drawable.no_photography_24)
    }
}

fun ImageView.loadImage(url: String?) {
    Glide
        .with(this.context)
        .load(url)
        .centerCrop()
        .into(this)
}
 fun hasInternet(context: Context): Boolean {

    // register activity with the connectivity manager service
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // if the android version is equal to M
    // or greater we need to use the
    // NetworkCapabilities to check what type of
    // network has the internet connection
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        // Returns a Network object corresponding to
        // the currently active default data network.
        val network = connectivityManager.activeNetwork ?: return false

        // Representation of the capabilities of an active network.
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            // Indicates this network uses a Wi-Fi transport,
            // or WiFi has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            // Indicates this network uses a Cellular transport. or
            // Cellular has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            // else return false
            else -> false
        }
    } else {
        // if the android version is below M
        @Suppress("DEPRECATION") val networkInfo =
            connectivityManager.activeNetworkInfo ?: return false
        @Suppress("DEPRECATION")
        return networkInfo.isConnected
    }
}


