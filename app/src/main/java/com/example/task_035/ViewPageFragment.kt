package com.example.task_035


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ImageView
import com.example.task_035.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 * Use the [ViewPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewPageFragment : Fragment() {
    private lateinit var imageViewIV: ImageView
    private lateinit var cityCaptionET: EditText
    private lateinit var loadBTN: Button

    private lateinit var feelsLikeTV: TextView
    private lateinit var grndLevelTV: TextView
    private lateinit var humidityTV:  TextView
    private lateinit var pressureTV:  TextView
    private lateinit var seaLevelTV:  TextView
    private lateinit var tempTV:      TextView
    private lateinit var tempMaxTV:   TextView
    private lateinit var tempMinTV:   TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI(view)

        val viewPagerItem = arguments?.getSerializable("vp") as OnMainFragmentModel
        imageViewIV.setImageResource(viewPagerItem.imageView)
        cityCaptionET.setText(viewPagerItem.cityCaption.toString())
        if (viewPagerItem.cityCaption == "any_city")
        {
            cityCaptionET.setEnabled(true)
        }
        else
        {
            cityCaptionET.setEnabled(false)
        }

        loadBTN.setOnClickListener{
            getCurrentWeather()
        }
    }

    fun initUI(view: View) {
        imageViewIV = view.findViewById(R.id.imageViewIV)
        cityCaptionET = view.findViewById(R.id.cityCaptionET)
        loadBTN = view.findViewById(R.id.loadBTN)

        feelsLikeTV = view.findViewById(R.id.feelsLikeTV)
        grndLevelTV = view.findViewById(R.id.grndLevelTV)
        humidityTV  = view.findViewById(R.id.humidityTV)
        pressureTV  = view.findViewById(R.id.pressureTV)
        seaLevelTV  = view.findViewById(R.id.seaLevelTV)
        tempTV      = view.findViewById(R.id.tempTV)
        tempMaxTV   = view.findViewById(R.id.tempMaxTV)
        tempMinTV   = view.findViewById(R.id.tempMinTV)
    }

    private fun getCurrentWeather() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getCurrentWeather(cityCaptionET.text.toString(), "metric", getString(R.string.app_key))
            } catch (e: IOException) {
            //    Toast.makeText(context, "app error ${e.message}", Toast.LENGTH_SHORT).show()
                return@launch
            } catch (e: HttpException) {
            //    Toast.makeText(context, "http error ${e.message}", Toast.LENGTH_SHORT).show()
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    val data = response.body()

                    feelsLikeTV.text = "${data!!.main.feels_like}"
                    grndLevelTV.text = "${data!!.main.grnd_level}"
                    humidityTV.text  = "${data!!.main.humidity} %"
                    pressureTV.text  = "${data!!.main.pressure} mm hc"
                    seaLevelTV.text  = "${data!!.main.sea_level} m"
                    tempTV.text      = "${data!!.main.temp} deg C"
                    tempMaxTV.text   = "${data!!.main.temp_max} deg C"
                    tempMinTV.text   = "${data!!.main.temp_min} deg C"
                }
            }
        }
    }
}