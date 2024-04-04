package rs.raf.projekat2.david_nikolic_9419.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.david_nikolic_9419.data.datasources.remote.EventService
import rs.raf.projekatjun.david_nikolic_9419.data.datasources.local.EventDataBase
import rs.raf.projekatjun.david_nikolic_9419.data.repository.EventRepository
import rs.raf.projekatjun.david_nikolic_9419.data.repository.EventRepositoryImpl
import rs.raf.projekatjun.david_nikolic_9419.viewmodel.EventViewModel

val recipeModule = module {

    viewModel { EventViewModel(eventRepository = get()) }

    single<EventRepository> { EventRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<EventDataBase>().getEventDao() }

    single<EventService> { create(retrofit = get()) }

}

