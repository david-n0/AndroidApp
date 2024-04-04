package rs.raf.projekatjun.david_nikolic_9419.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekatjun.david_nikolic_9419.model.Event
import rs.raf.projekatjun.david_nikolic_9419.model.EventEntity
import rs.raf.projekatjun.david_nikolic_9419.model.TimeEntity

@Dao

abstract class EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(eventEntity: EventEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(entities: List<EventEntity>): Completable

    @Query("SELECT * FROM events")
    abstract fun getAll(): Observable<List<EventEntity>>

    @Query("DELETE FROM events")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<EventEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    //TIMEYZONE
    @Query("DELETE FROM time")
    abstract fun deleteAllTimeEntity()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTimeEntity(timeEntity: TimeEntity): Completable

    @Query("SELECT * FROM time")
    abstract fun getAllTimeZone(): Observable<TimeEntity>

    @Transaction
    open fun deleteAndInsertTime(timeEntity: TimeEntity) {
        deleteAllTimeEntity()
        insertTimeEntity(timeEntity).blockingAwait()
    }

}