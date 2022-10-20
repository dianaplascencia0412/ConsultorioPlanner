package mx.edu.tecmm.consultorioplanner

import android.app.Application
import androidx.room.Room

class consultorioplanner  : Application()  {
    lateinit var room: AppDatabase

    override fun onCreate() {
        super.onCreate()
        room =  Room.databaseBuilder(this, AppDatabase::class.java, "doctor").build()
    }
}