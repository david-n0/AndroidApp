package rs.raf.projekat2.david_nikolic_9419.ui.states

import rs.raf.projekatjun.david_nikolic_9419.model.Event
import rs.raf.projekatjun.david_nikolic_9419.model.TimeEntity

sealed class AddEventState {
    data class Success(val time: TimeEntity) : AddEventState()
    data class Error(val message: String): AddEventState()
    object Loading : AddEventState()
    object DataFetched : AddEventState()

}