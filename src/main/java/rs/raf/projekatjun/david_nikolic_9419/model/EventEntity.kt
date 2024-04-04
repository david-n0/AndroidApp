package rs.raf.projekatjun.david_nikolic_9419.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventEntity (
    @PrimaryKey
    val name: String,
    val description: String,
    val date: String,
    val time: String,
    val url: String,
    val priority: String
)