package es.tipolisto.msxquiz.view.activities


import android.os.Bundle

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

import es.tipolisto.msxquiz.MediaPlayerClient
import es.tipolisto.msxquiz.R
import es.tipolisto.msxquiz.model.database.RecordDatabaseClient

import es.tipolisto.msxquiz.model.preferences.PreferenceManager
import es.tipolisto.msxquiz.view.interfaces.QuizListener
import es.tipolisto.msxquiz.viewModel.RecordViewModel



class ContainerActivity : AppCompatActivity(), QuizListener {
    private val recordViewModel : RecordViewModel by viewModels()
    lateinit var recordsDBClient : RecordDatabaseClient
    lateinit var mediaPlayerClient:MediaPlayerClient
    lateinit var mediaPlayerEffect: MediaPlayerClient
    lateinit var mediaPlayerTikTok: MediaPlayerClient
    lateinit var preferenceManager:PreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        //En el containerActivity inicializamos la base de datos
        //y rellenamos la tabla records si está vacía y los almacenamos en el ProviderList
        recordsDBClient=RecordDatabaseClient(this)
        //Chekeamos que la lista esté vacía, si lo está le metemos 10 recodrs inventado
        recordViewModel.checkEmptyList()
        //Es necesario actuzar ya que la lista está realmente en un List que está en RecordProvider
        //recordViewModel.updateRanking()
        //Inicializamos música
        mediaPlayerClient= MediaPlayerClient(this)
        //inicializamos efectos
        mediaPlayerEffect= MediaPlayerClient(this)
        mediaPlayerTikTok= MediaPlayerClient(this)
        //Las preferencias
        preferenceManager= PreferenceManager(this)
        //Ponemos el tema en el estado guardado
        if(preferenceManager.darkOnOff){
            setTheme(R.style.ThemeDark);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            setTheme(R.style.Theme_MSXQuiz);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        //Log.d(Constants.TAG, "El estado de la musica es "+preferenceManager.musicOnOff.toString())
        //Log.d(Constants.TAG, "El modo noche es "+preferenceManager.darkOnOff.toString())
    }




    override fun onStop() {
        super.onStop()
        //Log.d(Constants.TAG, "Llamado on stop en ContentActivity")
        if (preferenceManager.musicOnOff)mediaPlayerClient.stop()
        if (preferenceManager.musicOnOff)mediaPlayerTikTok.stopTikTok()
    }

    override fun playIntro() {
        if (preferenceManager.musicOnOff) mediaPlayerClient.playIntro()
    }

    override fun playInGame() {
        if (preferenceManager.musicOnOff) mediaPlayerClient.playIngame()
    }

    override fun playButton(value:String) {
        if (preferenceManager.musicOnOff) mediaPlayerClient.playButton(value)
    }

    override fun playTikTok() {
        if (preferenceManager.musicOnOff) mediaPlayerClient.playTikTok()
    }

    override fun playGameOver() {
        //nada
    }

    override fun stopMusic() {
        mediaPlayerClient.stop()
    }
    override fun stopTikTok() {
        mediaPlayerTikTok.stop()
    }


    override fun setPreferenceMusic(value: Boolean) {
        if(value){
            preferenceManager.saveMusicOnOff(false);
        }else{
            preferenceManager.saveMusicOnOff(true);
        }
    }

    override fun setPreferenceTheme(value: Boolean) {
        if(value){
            preferenceManager.saveDarkOnOff(true)
            setTheme(R.style.ThemeDark)
        }else{
            preferenceManager.saveDarkOnOff(false)
            setTheme(R.style.Theme_MSXQuiz)
        }
    }

}



