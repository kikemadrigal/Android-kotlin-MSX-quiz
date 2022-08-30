package es.tipolisto.msxquiz.model.preferences

import android.content.Context
import android.content.SharedPreferences


class PreferenceManager(val context: Context) {

        private val sharedPref: SharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        private val editor: SharedPreferences.Editor = sharedPref.edit()

        fun deletePreferences() {
            editor.clear()
            editor.commit()
        }

        val highRecord: Int
            get() = sharedPref.getInt("highscore", 0)

        fun saveNewRecord(score: Int) {
            editor.putInt("highscore", score)
            editor.commit()
        }

        fun getnameRecord(): String? {
            return sharedPref.getString("namescore", "Nameless")
        }

        fun saveNameNewRecord(name: String?) {
            editor.putString("namescore", name)
            editor.commit()
        }

        fun saveMusicOnOff(musicOnOff: Boolean) {
            editor.putBoolean("musicOnOff", musicOnOff)
            editor.commit()
        }

        val musicOnOff: Boolean
            get() = sharedPref.getBoolean("musicOnOff", true)

        fun saveDarkOnOff(darkOnOff: Boolean) {
            editor.putBoolean("darkOnOff", darkOnOff)
            editor.commit()
        }

        val darkOnOff: Boolean
            get() = sharedPref.getBoolean("darkOnOff", false)




}
