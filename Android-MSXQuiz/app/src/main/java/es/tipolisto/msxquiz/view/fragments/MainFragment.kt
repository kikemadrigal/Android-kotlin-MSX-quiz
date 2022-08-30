package es.tipolisto.msxquiz.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import es.tipolisto.msxquiz.R
import es.tipolisto.msxquiz.databinding.FragmentMainBinding
import es.tipolisto.msxquiz.model.extensions.showDialogProblemGetDataInternet
import es.tipolisto.msxquiz.model.provaiders.QuizProvider
import es.tipolisto.msxquiz.util.Util
import es.tipolisto.msxquiz.view.interfaces.QuizListener
import es.tipolisto.msxquiz.viewModel.QuizViewModel


class MainFragment : Fragment(){
    private lateinit var binding: FragmentMainBinding
    private val quizViewModel : QuizViewModel by viewModels()
    private lateinit var navController: NavController
    //La interface
    private lateinit var quizListener:QuizListener


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding= FragmentMainBinding.inflate(inflater,container, false)
        initButtons()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)
        quizListener.playIntro()
        //También pedimos la lista de preguntas por internet y rellenamos la lista que está en QuizViewModel
        if (Util.isNetworkConnected(requireContext())){
            quizViewModel.quizListInternetLiveData.observe(requireActivity()) {
                QuizProvider.setQuizList(it)
                //Log.d("Mensaje", "Vista rellenada con "+it.size.toString()+" preguntas")
            }
            quizViewModel.getQuidListInternet()
        }else{
            showDialogProblemGetDataInternet(requireActivity())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            quizListener = context as QuizListener
        } catch (castException: ClassCastException) {}
    }



    private fun initButtons() {
        //1 Forma
        /*binding.btnPlay.setOnClickListener {
            QuizApp.playButton(context, "click")
            findNavController().navigate(R.id.action_mainFragment_to_quizFragment)
        }*/
        //2 forma
        //binding.btnPlay.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_quizFragment))
        //3 Forma (hemos tenido que crear un objeto navController)
        binding.btnPlay.setOnClickListener {
            quizListener.playButton("click")
            navController.navigate(R.id.action_mainFragment_to_quizFragment)
        }

        binding.btnInformation.setOnClickListener {
            quizListener.playButton("click")
            navController.navigate(R.id.action_mainFragment_to_settingsFragment)
        }

        binding.btnRanking.setOnClickListener {
            quizListener.playButton("click")
            navController.navigate(R.id.action_mainFragment_to_rankingFragment)
        }


    }



}