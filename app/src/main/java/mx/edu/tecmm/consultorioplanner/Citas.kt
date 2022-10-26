package mx.edu.tecmm.consultorioplanner


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Citas(
    @PrimaryKey (autoGenerate = true) val idCita: Int,
    @ColumnInfo(name = "Fecha") val fechaCita: String?,
    @ColumnInfo(name = "Hora") val horaCita: String?,
    @ColumnInfo(name = "IdDoctor") val idD: String?,
    @ColumnInfo(name = "NombreP") val nombreP: String?,
    @ColumnInfo(name = "Telefono") val telefono: String?
)
