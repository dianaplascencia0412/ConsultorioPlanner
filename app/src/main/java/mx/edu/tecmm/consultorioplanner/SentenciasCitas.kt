package mx.edu.tecmm.consultorioplanner

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface SentenciasCitas {
    @Query("SELECT * FROM Citas")
    suspend fun getAll(): List<Citas>

    @Query("SELECT * FROM Citas WHERE idDoctor IN (:idDoctorr) ")
    suspend fun getAllByIdDoctor(idDoctorr:String): List<Citas>

    @Query("SELECT * FROM Citas WHERE idDoctor IN (:idDoctorr) and Fecha in (:fechaCitaa) ")
    suspend fun getByFecha(idDoctorr:String , fechaCitaa:String): List<Citas>

    @Query("SELECT * FROM Citas WHERE idCita IN (:idCitaa) and Fecha in (:fechaCitaa)  and Hora in (:horaCitaa) ")
    suspend fun getByHora(idCitaa:String , fechaCitaa:String , horaCitaa:String):Citas

    @Insert
    suspend fun insert(persona: Citas)
}