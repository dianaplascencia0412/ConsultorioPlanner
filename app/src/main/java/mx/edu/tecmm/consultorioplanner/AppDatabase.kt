package mx.edu.tecmm.consultorioplanner

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Doctores::class ,Citas::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun DoctoresDao(): Sentencias
    abstract fun CitasDao(): SentenciasCitas

}