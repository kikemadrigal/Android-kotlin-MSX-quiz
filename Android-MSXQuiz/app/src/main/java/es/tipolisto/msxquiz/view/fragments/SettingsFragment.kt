package es.tipolisto.msxquiz.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.Navigation
import es.tipolisto.msxquiz.R
import es.tipolisto.msxquiz.databinding.FragmentSettingsBinding
import es.tipolisto.msxquiz.model.preferences.PreferenceManager
import es.tipolisto.msxquiz.util.Constants
import es.tipolisto.msxquiz.view.interfaces.QuizListener


class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var quizListener: QuizListener
    private lateinit var navController: NavController
    lateinit var preferenceManager:PreferenceManager

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding= FragmentSettingsBinding.inflate(inflater,container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        preferenceManager= PreferenceManager(requireContext())

        //Ponemos los swtches como estén las preferencias
        binding.switchSettingsMusicOnOff.setChecked(preferenceManager.musicOnOff);
        binding.switchSettingsDarkTheme.setChecked(preferenceManager.darkOnOff);

        //Asignamos los clicks a los switches
        binding.switchSettingsMusicOnOff.setOnClickListener {
            quizListener.playButton("click")
            if(preferenceManager.musicOnOff){
                quizListener.setPreferenceMusic(true)
                //Log.d(Constants.TAG,"Como la musica estaba en marcha la paramos, ahhora es "+preferenceManager.musicOnOff.toString())
                quizListener.stopMusic()
                binding.switchSettingsMusicOnOff.setChecked(false);
            }else{
                quizListener.setPreferenceMusic(false)
                //Log.d(Constants.TAG,"Como la musica estaba para la ponemos en marcha, ahhora es "+preferenceManager.musicOnOff.toString())
                quizListener.playIntro()
                binding.switchSettingsMusicOnOff.setChecked(true);
            }
        }
        binding.switchSettingsDarkTheme.setOnClickListener{
            quizListener.playButton("click")
            //if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            if(binding.switchSettingsDarkTheme.isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                binding.switchSettingsDarkTheme.setChecked(true);
                quizListener.setPreferenceTheme(true)
                //Log.d(Constants.TAG,"Cambiado a modo oscuro (el modo oscurodebe de ser true), ahora es  "+preferenceManager.darkOnOff.toString())

            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                binding.switchSettingsDarkTheme.setChecked(false);
                quizListener.setPreferenceTheme(false)
                //Log.d(Constants.TAG,"Cambiado a modo claro, ahora el modo noche es: "+preferenceManager.darkOnOff.toString())
            }
        }
        binding.btnBack.setOnClickListener {
            quizListener.playButton("click")
            navController.navigate(R.id.action_settingsFragment_to_mainFragment)
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            quizListener = context as QuizListener
        } catch (castException: ClassCastException) {
            /** The activity does not implement the listener.  */
        }
    }

}