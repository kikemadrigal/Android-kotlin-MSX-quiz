package es.tipolisto.msxquiz.view.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation

import es.tipolisto.msxquiz.R
import es.tipolisto.msxquiz.databinding.FragmentScoreBinding
import es.tipolisto.msxquiz.model.extensions.showToast
import es.tipolisto.msxquiz.view.interfaces.QuizListener

import es.tipolisto.msxquiz.viewModel.RecordViewModel

class ScoreFragment : Fragment() {
    private lateinit var binding: FragmentScoreBinding
    private lateinit var quizListener: QuizListener
    private lateinit var navController: NavController
    private val recordViewModel : RecordViewModel by viewModels()
    private var points:Int=0

    private val rankingViewModel : RecordViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        binding= FragmentScoreBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        //val points:FragmentScoreArgs=FragmentScoreArgs.fromBundle(arguments!!).points
        points= ScoreFragmentArgs.fromBundle(requireArguments()).points
        //Log.d("Mensaje", points.toString())
        binding.tvScore.text = points.toString()
        rankingViewModel.seeProgressBarchLiveData.observe(requireActivity()){
            if(it){
                binding.progressbar.visibility=View.VISIBLE
            }else{
                binding.progressbar.visibility=View.GONE
            }
        }


        binding.buttonRanking.setOnClickListener{
            quizListener.playButton("click")
            if (binding.etRankingName.text.isNotEmpty()){
                val name =binding.etRankingName.text.toString()
                //TODO: borrar a partir de la 10 puntuación maxima todos los registros
                Log.d("Mensaje", "vamos a añadir a "+name+" "+points.toString())
                rankingViewModel.addScore(name, points)
                rankingViewModel.updateRanking()
                Handler(Looper.getMainLooper()).postDelayed({ navController.navigate(R.id.action_scoreFragment_to_rankingFragment) },1000)
            }else{
                showToast(requireContext(),getString(R.string.Please_write_a_name))
            }
        }
        //Mostramos un menú u otro según si es un nuevo record
        recordViewModel.maxPointsLiveData.observe(requireActivity()) { maxPointsInTable ->
            //if (maxPointsInTable < points) initButtonWithEditText()
            if (maxPointsInTable > points) {
                binding.etRankingName.visibility = View.GONE
                binding.buttonRanking.setOnClickListener {
                    //Log.d("Mensaje", "Pulsado")
                    startMainFragment()
                }
            }
        }
        recordViewModel.getmaxPoints()
        quizListener.stopMusic()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            quizListener = context as QuizListener
        } catch (castException: ClassCastException) { }
    }


    /*private fun startRankingFragment(){
        navController.navigate(R.id.action_scoreFragment_to_rankingFragment)
    }*/
    private fun startMainFragment(){
        navController.navigate(R.id.action_scoreFragment_to_mainFragment)
    }




}