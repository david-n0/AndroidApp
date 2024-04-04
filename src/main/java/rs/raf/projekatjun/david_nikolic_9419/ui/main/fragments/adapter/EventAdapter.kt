package rs.raf.projekatjun.david_nikolic_9419.ui.main.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import rs.raf.projekatjun.david_nikolic_9419.databinding.LayoutEventListItemBinding
import rs.raf.projekatjun.david_nikolic_9419.model.EventEntity
import rs.raf.projekatjun.david_nikolic_9419.ui.main.fragments.ShowEventsFragment
import rs.raf.projekatjun.david_nikolic_9419.ui.main.fragments.diff.EventDiffItemCallback
import rs.raf.projekatjun.david_nikolic_9419.ui.main.fragments.viewholder.EventViewHolder

class EventAdapter(
        private val fragment: ShowEventsFragment
) : ListAdapter<EventEntity, EventViewHolder>(EventDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemBinding =
                LayoutEventListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(itemBinding, fragment)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}