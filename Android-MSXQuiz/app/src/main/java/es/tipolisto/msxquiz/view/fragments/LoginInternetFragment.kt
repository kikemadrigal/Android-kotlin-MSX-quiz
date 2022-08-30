package es.tipolisto.msxquiz.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import es.tipolisto.msxquiz.R
import es.tipolisto.msxquiz.databinding.FragmentLoginInternetBinding
import es.tipolisto.msxquiz.model.extensions.createDialog
import es.tipolisto.msxquiz.model.extensions.showDialogProblemGetDataInternet
import es.tipolisto.msxquiz.model.extensions.showToast
import es.tipolisto.msxquiz.view.interfaces.QuizListener
import es.tipolisto.msxquiz.viewModel.LoginInternetViewModel


class LoginInternetFragment : Fragment() {
    private lateinit var binding: FragmentLoginInternetBinding
    private lateinit var quizListener: QuizListener
    private val loginInternetViewModel : LoginInternetViewModel by viewModels()
    /*companion object{
        const val EXTRA_MAX_SCORE="RankingActivity:maxScore"
    }*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        binding= FragmentLoginInternetBinding.inflate(inflater,container, false)
        //val maxScore=intent.getIntExtra(LoginInternetActivity.EXTRA_MAX_SCORE,0)
        val maxScore = LoginInternetFragmentArgs.fromBundle(requireArguments()).maxScore
        if(maxScore!=null){
            binding.etMaxScore.setText(maxScore.toString())
        }
        val name = binding.etUser.text.toString()
        val password=binding.etPassword.text.toString()

        loginInternetViewModel.messageLiveData.observe(requireActivity()) {
            when (it) {
                0 -> createDialog(requireActivity(), getString(R.string.the_user_name_does_not_exist))
                1 -> {
                    createDialog(requireActivity(), getString(R.string.New_record_saved_in_quiz_tiposto_es))
                    findNavController().navigate(R.id.action_loginInternetFragment_to_rankingFragment)
                }
                2 -> createDialog(
                    requireActivity(),
                    getString(R.string.The_user_s_password) + name + getString(R.string.is_incorrect)
                )
                3 -> createDialog(
                    requireActivity(),
                    getString(R.string.The_user_needs_to_be_validated_to_continue_go_to) +"quiz.tipolisto.es/auth/mailValidation/" + name + "/" + password
                )
                4 -> createDialog(requireActivity(), getString(R.string.There_was_a_problem_with_the_database))
                5 -> createDialog(
                    requireActivity(),
                    getString(R.string.Another_saved_high_score_found)
                )
                else -> showDialogProblemGetDataInternet(requireActivity())
            }
            //Log.d(Constants.TAG,"respuesta del servidor "+it.toString())
        }
        //Log.d("Mensaje", "Estas en loginInternetActivity "+maxScore.toString())

        binding.btUploadSore.setOnClickListener {
            quizListener.playButton("click")
            val name = binding.etUser.text.toString()
            val password=binding.etPassword.text.toString()
            if (name.isEmpty() || password.isEmpty()){
                showToast(requireContext(),getString(R.string.Enter_the_required_fields), Toast.LENGTH_LONG)
            }else {
                loginInternetViewModel.saveScore(name, password, maxScore)
                //Log.d(Constants.TAG,"Clic en upload internet login")
            }
        }


        binding.btnBack.setOnClickListener {
            quizListener.playButton("click")
            findNavController().navigate(R.id.action_loginInternetFragment_to_rankingFragment)
        }
        return binding.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            quizListener = context as QuizListener
        } catch (castException: ClassCastException) {}
    }

}