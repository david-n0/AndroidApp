package rs.raf.projekatjun.david_nikolic_9419.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekatjun.david_nikolic_9419.model.EventEntity
import rs.raf.projekatjun.david_nikolic_9419.model.TimeEntity

@Database(
    entities = [EventEntity::class, TimeEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters()
abstract class EventDataBase: RoomDatabase() {
    abstract fun getEventDao(): EventDao
}