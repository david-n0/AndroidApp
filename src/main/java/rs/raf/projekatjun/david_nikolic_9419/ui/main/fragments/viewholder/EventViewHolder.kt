package rs.raf.projekatjun.david_nikolic_9419.ui.main.fragments.viewholder

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.compat.SharedViewModelCompat.sharedViewModel
import retrofit2.http.HTTP
import rs.raf.projekatjun.david_nikolic_9419.R
import rs.raf.projekatjun.david_nikolic_9419.databinding.LayoutEventListItemBinding
import rs.raf.projekatjun.david_nikolic_9419.model.EventEntity
import rs.raf.projekatjun.david_nikolic_9419.ui.main.fragments.ShowEventsFragment
import rs.raf.projekatjun.david_nikolic_9419.viewmodel.EventViewModel


class EventViewHolder(
        private val binding: LayoutEventListItemBinding,
        private val fragment: ShowEventsFragment
) : RecyclerView.ViewHolder(binding.root) {

    // private val eventViewModel: MainContract.ViewModel by sharedViewModel<EventViewModel>()
    private lateinit var selectedEvent: EventEntity

    init {

        //SHARE
        binding.btnShareVH.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {

                type = "text/plain"
                putExtra(Intent.EXTRA_TITLE, selectedEvent.name) // recipients
                putExtra(Intent.EXTRA_SUBJECT, selectedEvent.description)
                putExtra(Intent.EXTRA_TIME, selectedEvent.date)
                putExtra(Intent.EXTRA_TIME, selectedEvent.time)
                putExtra(Intent.EXTRA_STREAM, selectedEvent.url)

                putExtra(Intent.EXTRA_TEXT, selectedEvent.name + " " + selectedEvent.description + " " + selectedEvent.date + " " + selectedEvent.time + " " + selectedEvent.url) // recipients

            }

            val chooserIntent = Intent.createChooser(intent, "Share")

            fragment.activity?.startActivity(chooserIntent)

        }

        //DELETE
        binding.btnDeleteVH.setOnClickListener {
            Toast.makeText(fragment.requireContext(), "Kliknuto ", Toast.LENGTH_SHORT).show()
            Log.d("KLIK", "Kliknuto na: DELETE")

        }

    }

    @SuppressLint("ResourceAsColor")
    fun bind(event: EventEntity) {

        selectedEvent = event
        binding.eventNameVH.text = event.name
        binding.descriptionVH.text = event.description
        binding.dateVH.text = event.date
        binding.timeVH.text = event.time
        binding.url.text = event.url

        if (event.priority.toLowerCase() == "low") {

            Log.d("PRIORITET", "Low: " + event.priority.toLowerCase())
            binding.fragmentItemData.setBackgroundColor(Color.parseColor("#59FFFFFF"))
        } else if (event.priority.toLowerCase() == "medium") {

            Log.d("PRIORITET", "Med: " + event.priority.toLowerCase())
            binding.fragmentItemData.setBackgroundColor((Color.parseColor("#595EE639")))

        } else if (event.priority.toLowerCase() == "high") {

            Log.d("PRIORITET", "Med: " + event.priority.toLowerCase())
            binding.fragmentItemData.setBackgroundColor(Color.parseColor("#4DE63946"))

        }
    }

}