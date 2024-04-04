package rs.raf.projekat2.david_nikolic_9419.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rs.raf.projekatjun.david_nikolic_9419.model.TimeEntity
import rs.raf.projekatjun.david_nikolic_9419.model.TimeResponse
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

interface EventService {

    @GET("{city}")
    fun getTime(@Path("city") city: String): Observable<TimeResponse>


}