package mx.edu.tecmm.consultorioplanner

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Doctores(
    @PrimaryKey @NonNull var idDoctor: String,
    @ColumnInfo(name = "Nombre") val nombre: String?
)

