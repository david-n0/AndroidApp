package rs.raf.projekatjun.david_nikolic_9419.data.repository

import android.provider.CalendarContract
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekatjun.david_nikolic_9419.model.Event
import rs.raf.projekatjun.david_nikolic_9419.model.EventEntity
import rs.raf.projekatjun.david_nikolic_9419.model.Resource
import rs.raf.projekatjun.david_nikolic_9419.model.TimeEntity

interface EventRepository {

  //  fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<EventEntity>>
    fun insert(event: EventEntity): Completable

    fun getTimeForCity(city: String): Observable<TimeEntity>
    fun fetchTimeZone(city: String): Observable<Resource<Unit>>

}