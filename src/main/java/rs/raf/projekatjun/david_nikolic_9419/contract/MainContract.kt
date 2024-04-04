import androidx.lifecycle.LiveData
import rs.raf.projekat2.david_nikolic_9419.ui.states.AddEventState
import rs.raf.projekat2.david_nikolic_9419.ui.states.EventsState
import rs.raf.projekatjun.david_nikolic_9419.model.Event
import rs.raf.projekatjun.david_nikolic_9419.model.EventEntity

interface MainContract {

    interface ViewModel {

        val showEventState: LiveData<EventsState>
        val addEventState: LiveData<AddEventState>

        fun getTimeForCity(city: String)
        fun fetchTimeForCity(city: String)

        fun getAllEvents()
       // fun fetchAllEvents()
        fun addEvent(event: EventEntity)
    }

}