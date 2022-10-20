package mx.edu.tecmm.consultorioplanner

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Sentencias {
    @Query("SELECT * FROM Doctores")
    suspend fun getAll(): List<Doctores>

    @Query("SELECT * FROM Doctores WHERE idDoctor IN (:idDoctorr)")
    suspend fun getById(idDoctorr:String):Doctores


    @Update
    suspend fun update(persona:Doctores)

    @Insert
    suspend fun insert(persona: Doctores)
}