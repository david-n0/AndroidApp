package rs.raf.projekatjun.david_nikolic_9419.ui.main.fragments.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekatjun.david_nikolic_9419.model.EventEntity

class EventDiffItemCallback : DiffUtil.ItemCallback<EventEntity>(){

    override fun areItemsTheSame(oldItem: EventEntity, newItem: EventEntity): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: EventEntity, newItem: EventEntity): Boolean {
        return oldItem.name == newItem.name && oldItem.url == newItem.url
    }
}