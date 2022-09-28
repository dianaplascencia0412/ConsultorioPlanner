package mx.edu.tecmm.consultorioplanner

import android.os.Parcel
import android.os.Parcelable

data class Persona(val Nombre: String, val Id: String): Parcelable {
    constructor(parcel: Parcel) : this(

        parcel.readString()!! ,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeString(Nombre)
        parcel.writeString(Id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Persona> {
        override fun createFromParcel(parcel: Parcel): Persona {
            return Persona(parcel)
        }

        override fun newArray(size: Int): Array<Persona?> {
            return arrayOfNulls(size)
        }
    }
}
