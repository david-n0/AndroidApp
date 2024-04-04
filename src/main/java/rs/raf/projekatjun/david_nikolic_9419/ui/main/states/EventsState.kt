package rs.raf.projekat2.david_nikolic_9419.ui.states

import rs.raf.projekatjun.david_nikolic_9419.model.Event
import rs.raf.projekatjun.david_nikolic_9419.model.EventEntity
import rs.raf.projekatjun.david_nikolic_9419.model.TimeEntity

sealed class EventsState {
    object Loading : EventsState()
    object DataFetched : EventsState()
    data class Success(val events: List<EventEntity>) : EventsState()
    data class Success2(val event: EventEntity) : EventsState()

    data class Error(val message: String) : EventsState()
}