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

    @Query("SELECT * FROM Citas WHERE idDoctor IN (:idDoctorr) and Fecha in (:fechaCitaa) ")
    suspend fun getByHora(idDoctorr:String , fechaCitaa:String): List<Citas>



    @Insert
    suspend fun insert(persona: Citas)
}