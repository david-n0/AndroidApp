package rs.raf.projekatjun.david_nikolic_9419.viewmodel

import android.app.usage.EventStats
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.david_nikolic_9419.ui.states.AddEventState
import rs.raf.projekat2.david_nikolic_9419.ui.states.EventsState
import rs.raf.projekatjun.david_nikolic_9419.data.repository.EventRepository
import rs.raf.projekatjun.david_nikolic_9419.model.Event
import rs.raf.projekatjun.david_nikolic_9419.model.EventEntity
import rs.raf.projekatjun.david_nikolic_9419.model.Resource
import timber.log.Timber
import java.util.concurrent.TimeUnit

class EventViewModel(
    private val eventRepository: EventRepository,

) : ViewModel(),
MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    override val showEventState: MutableLiveData<EventsState> = MutableLiveData()
    override val addEventState:  MutableLiveData<AddEventState> = MutableLiveData()

//    init {
//        val subscription = publishSubject
//            .debounce(200, TimeUnit.MILLISECONDS)
//            .distinctUntilChanged()
//            .switchMap {
//                eventRepository
//                    .getAll()
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnError {
//                        Timber.e("Error in publish subject")
//                        Timber.e(it)
//                    }
//            }
//            .subscribe(
//                {
//                    eventState.value = EventsState.Success2(it)
//                },
//                {
//                    eventState.value =
//                        EventsState.Error("Error happened while fetching data from db")
//                    Timber.e(it)
//                }
//            )
//        subscriptions.add(subscription)
 //   }

    override fun getTimeForCity(city: String){
        val subscription = eventRepository
                .getTimeForCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            addEventState.value = AddEventState.Success(it)
                        },
                        {
                            addEventState.value =
                                    AddEventState.Error("Error happened while fetching data from db")
                            Timber.e(it)
                        }
                )
        subscriptions.add(subscription)
    }

    override fun fetchTimeForCity(city: String) {
        val subscription = eventRepository
                .fetchTimeZone(city)
                .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            when (it) {
                                is Resource.Loading -> addEventState.value = AddEventState.Loading
                                is Resource.Success -> addEventState.value = AddEventState.DataFetched
                                is Resource.Error -> addEventState.value =  AddEventState.Error("Error happened while fetching data from the server")
                            }
                        },
                        {
                            addEventState.value =
                                    AddEventState.Error("Error happened while fetching data from the server")
                            Timber.e(it)
                        }
                )
        subscriptions.add(subscription)
    }

    override fun getAllEvents() {
        val subscription = eventRepository
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            showEventState.value = EventsState.Success(it)
                        },
                        {
                            showEventState.value =
                                    EventsState.Error("Error happened while fetching data from db")
                            Timber.e(it)
                        }
                )
        subscriptions.add(subscription)
    }
//
//    override fun fetchAllEvents() {
//        val subscription = eventRepository
//                .fetchAll()
//                .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        {
//                            when (it) {
//                                is Resource.Loading -> showEventState.value = EventsState.Loading
//                                is Resource.Success -> showEventState.value = EventsState.DataFetched
//                                is Resource.Error -> showEventState.value =
//                                        EventsState.Error("Error happened while fetching data from the server")
//                            }
//                        },
//                        {
//                            showEventState.value =
//                                    EventsState.Error("Error happened while fetching data from the server")
//                            Timber.e(it)
//                        }
//                )
//        subscriptions.add(subscription)
//    }


    override fun addEvent(event: EventEntity) {
        val subscription = eventRepository
                .insert(event)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            showEventState.value = EventsState.Success2(event)
                        },
                        {
                            showEventState.value = EventsState.Error("Error happened while adding movie")
                            Timber.e(it)
                        }
                )
        subscriptions.add(subscription)
    }



}