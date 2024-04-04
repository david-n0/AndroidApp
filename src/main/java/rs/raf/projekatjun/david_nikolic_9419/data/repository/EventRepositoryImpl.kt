package rs.raf.projekatjun.david_nikolic_9419.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.david_nikolic_9419.data.datasources.remote.EventService
import rs.raf.projekatjun.david_nikolic_9419.data.datasources.local.EventDao
import rs.raf.projekatjun.david_nikolic_9419.model.Event
import rs.raf.projekatjun.david_nikolic_9419.model.EventEntity
import rs.raf.projekatjun.david_nikolic_9419.model.Resource
import rs.raf.projekatjun.david_nikolic_9419.model.TimeEntity
import timber.log.Timber

class EventRepositoryImpl(
    private val localDataSource: EventDao,
    private val remoteDataSource: EventService
) : EventRepository {


    override fun getTimeForCity(city: String): Observable<TimeEntity> {
        return localDataSource
                .getAllTimeZone()
                .map {
                   TimeEntity(
                           it.datetime
                   )
                }
    }

    override fun fetchTimeZone(city: String): Observable<Resource<Unit>> {
        return remoteDataSource
                .getTime(city)
                .doOnNext {
                    Timber.e("TIMEZONE INPUT IN DB")
                    val timeEntity = TimeEntity(
                            it.datetime
                    )
                    localDataSource.deleteAndInsertTime(timeEntity)

                }
                .map {
                    Resource.Success(Unit)
                }
    }

    override fun insert(event: EventEntity): Completable {
        val eventEntity =
                EventEntity(
                        event.name,
                        event.description,
                        event.date,
                        event.time,
                        event.url,
                        event.priority
                )
        return localDataSource
                .insert(eventEntity)
    }

//    override fun fetchAll(): Observable<Resource<Unit>> {
//        return remoteDataSource
//                .getAll()
//                .doOnNext {
//                    Timber.e("Upis u bazu")
//                    val entities = it.
//                    localDataSource.deleteAndInsertAll(entities)
//
//                }
//                .map {
//                    Resource.Success(Unit)
//                }
//    }

    override fun getAll(): Observable<List<EventEntity>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    EventEntity(it.name, it.description, it.date, it.time,it.url, it.priority)
                }
            }
    }


}
