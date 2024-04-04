package rs.raf.projekatjun.david_nikolic_9419.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time")
data class TimeEntity (
    @PrimaryKey
    val datetime: String
)