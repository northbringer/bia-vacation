package ru.northbringer.bia_vacation.android.gant_view

import android.os.Parcel
import android.os.Parcelable
import android.view.View

class SavedState: View.BaseSavedState {
    var translationX: Float = 0f

    constructor(superState: Parcelable?) : super(superState)

    constructor(source: Parcel?) : super(source) {
        source?.apply {
            translationX = readFloat()
        }
    }

    override fun writeToParcel(out: Parcel?, flags: Int) {
        super.writeToParcel(out, flags)
        out?.writeFloat(translationX)
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
            override fun createFromParcel(source: Parcel?): SavedState {
                return SavedState(source)
            }

            override fun newArray(size: Int): Array<SavedState?> {
                return arrayOfNulls(size)
            }
        }
    }
}