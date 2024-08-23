package es.tipolisto.msxquiz.view.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import es.tipolisto.msxquiz.R
import es.tipolisto.msxquiz.databinding.FragmentQuizBinding
import es.tipolisto.msxquiz.model.Quiz
import es.tipolisto.msxquiz.model.extensions.*
import es.tipolisto.msxquiz.util.Constants
import es.tipolisto.msxquiz.util.Util
import es.tipolisto.msxquiz.view.interfaces.QuizListener
import es.tipolisto.msxquiz.viewModel.QuizViewModel
import java.lang.Exception
import java.text.DecimalFormat
import java.text.NumberFormat


class QuizFragment : Fragment(){
    private lateinit var binding: FragmentQuizBinding
    private lateinit var timer: CountDownTimer
    private val quizViewModel : QuizViewModel by viewModels()
    private lateinit var quizListener: QuizListener
    private lateinit var quizList:List<Quiz>
    private lateinit var navController: NavController
    private lateinit var arrayAnswerButtons : Array<Button>

    private var correctAnswer = 0
    //private var questionNumber = 1
    private var totalPoints = 0
    private var fails=5

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding= FragmentQuizBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        quizViewModel.getQuiz()
        //Utilizamos un array para recorrer los botones ya que queremos cambiarles el texto ty aignarles estilos
        arrayAnswerButtons = arrayOf(binding.btnAnswer1, binding.btnAnswer2, binding.btnAnswer3)
        //quizViewModel.getQuiz()

        //Cada vez que llamemos a getQuiz se llamará a la función setQuestion
        quizViewModel.quizProviderLiveData.observe(requireActivity()) {
            //Si la pregunta está vacía volvemos al menú principal
            if(it.question.equals("empty")){
                navController.navigate(R.id.action_quizFragment_to_mainFragment)
                showToast(requireContext(),getString(R.string.We_had_trouble_getting_the_list_of_questions_please_try_again))
            }
            else
                setQuestion(it)
        }
        binding.buttonReload?.setOnClickListener {
            quizViewModel.getQuiz()
        }
        //Cada vez que se pida una imagen la asignaremos al ivImage
        quizViewModel.imageInternetLiveData.observe(requireActivity()) {
            Log.d(Constants.TAG,"peticion cambio imagen recibida")
            //Pero solo si hay internet
            //https://quiz.tipolisto.es/media/users/user186/direcciones.PNG
            var urlImage = "https://quiz.tipolisto.es/" + it.path
            /*
            Glide.with(binding.root.context)
                .load(urlImage)
                .into(binding.ivImage)
            */
            Picasso.get()
                .load(urlImage)
                .placeholder(R.drawable.loading)
                .error(R.drawable.without_image)
                .into(binding.ivImage, object : Callback {
                    override fun onSuccess() {
                        if (binding.progressBar != null) {
                            binding.progressBar?.setVisibility(View.GONE)
                            Log.d("Message", "Imagen cargada")
                            binding.buttonReload?.visibility=View.GONE
                        }
                    }

                    override fun onError(e: Exception?) {
                        binding.progressBar?.setVisibility(View.GONE)
                        binding.buttonReload?.visibility=View.VISIBLE
                        binding.ivImage.setImageResource(R.drawable.without_image)
                        quizViewModel.getQuiz()
                    }
                })
        }
    }
    override fun onResume() {
        super.onResume()
        //Esto hará que se llame a setQuestion
        quizViewModel.getQuiz()
        //Iniciamos el contador
        setCountDown()
        //Cuando se pulse el botón volver hiremos hacia atrás
        initBackButton();
        //Actualizamos el marcador
        updateHud()
        //reproducimos la múica del juego
        quizListener.playInGame()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            quizListener = context as QuizListener
        } catch (castException: ClassCastException) {}
    }

    override fun onPause() {
        quizListener.stopMusic()
        timer.cancel()
        super.onPause()
    }


    private fun updateHud() {
        binding.tvPoints.text=totalPoints.toString()
        binding.tvFails.text=fails.toString()
    }

    private fun setQuestion(quiz:Quiz) {
        if(quiz==null)
            //Log.d("Mensaje", "es null")
        else{
            binding.tvQuestion.text=quiz.question
            binding.btnAnswer1.text=quiz.answer1
            binding.btnAnswer2.text=quiz.answer2
            binding.btnAnswer3.text=quiz.answer3
            correctAnswer=quiz.correctAnswer
            binding.tvAuthor.text=quiz.nameUser
            quizViewModel.getImageInternet(quiz.image)
        }
    }
    private fun setCountDown(){
        timer = object : CountDownTimer(31000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //quizListener.playTikTok()
                val f: NumberFormat = DecimalFormat("00")
                val sec = millisUntilFinished / 1000 % 60
                binding.tvTimer.text = f.format(sec)
                binding.progressBarCircle.progress = f.format(sec).toInt()
            }
            //Cuando se termine el tiempo
            override fun onFinish() {
                timer.cancel()
                fails-=1
                try{
                    //Le ponemos a la respuesta correcta el color verde texto blanco
                    arrayAnswerButtons[correctAnswer-1].setBackgroundColor(ResourcesCompat.getColor(resources,R.color.green_color,null))
                    arrayAnswerButtons[correctAnswer-1].setTextColor(ResourcesCompat.getColor(resources,R.color.white,null))
                }catch (ex:IllegalStateException){}
                setDelay()
            }
        }
        setAnswerButtons()
        timer.start()
    }


    private fun setAnswerButtons(){
        //Por cada uno de los botones, obtenmos la respuesta correcta y la comparamos con el texto de los botones
        arrayAnswerButtons.forEach { answerButton ->
            answerButton.setOnClickListener {
                //Paramos el tiempo
                timer.cancel()
                if(answerButton.tag.toString().toInt() == correctAnswer){
                    quizListener.playButton("success")
                    answerButton.correct()
                    totalPoints += binding.tvTimer.text.toString().toInt()
                }else{
                    quizListener.playButton("failure")
                    answerButton.incorrect(arrayAnswerButtons, correctAnswer)
                    fails-=1
                }
                //Unclickable until delay finishes
                arrayAnswerButtons.forEach { it.setOnClickListener {  } }
                //Volver a preguntar por una nueva quiz
                setDelay()
            }

        }
    }

    private fun setDelay(){
        //Este bloque comienza un segundo después
        Handler(Looper.getMainLooper()).postDelayed(
            {
                //Si no quedan preguntas terminamos
                if (quizViewModel.getList().size<=0)
                {
                    //quizListener.stopTikTok()
                    quizViewModel.getQuidListInternet()
                    //val action:NavDirections=QuizFragmentDirections.nextAction("score")
                    //findNavController().navigate(action)
                    val bundle= Bundle()
                    bundle.putInt("points",totalPoints)
                    navController.navigate(R.id.action_quizFragment_to_scoreFragment, bundle)
                }else{
                    //Si no quedan vidas terminamos
                    if(fails <= 0){
                        //quizListener.stopTikTok()
                        quizListener.playButton("failure")
                        val bundle= Bundle()
                        bundle.putInt("points",totalPoints)
                        navController.navigate(R.id.action_quizFragment_to_scoreFragment, bundle)
                    }else{
                        //Si hay internet solicitamos otra pregnta
                        if(Util.isNetworkConnected(requireContext())) {
                            updateHud()
                            quizViewModel.getQuiz()
                            arrayAnswerButtons.forEach { it.clear() }
                            setCountDown()
                            //questionNumber++
                            //binding.tvNumber.text = questionNumber.toString()
                        }else{
                            showDialogProblemGetDataInternet(requireActivity())
                        }
                    }
                }
            },1000)
    }




    private fun initBackButton() {
        binding.btnBack.setOnClickListener {
            quizListener.playButton("click")
            onBackPressed()
        }
    }



    fun onBackPressed() {
        quizListener.playButton("click")
        val builder= AlertDialog.Builder (requireContext())
        builder.setTitle("Quiz says")
        builder.setMessage(getString(R.string.Are_you_sure_you_want_to_finish_the_game))
        builder.setPositiveButton("Yes") { dialog, id ->
            dialog.cancel()
            timer.cancel()
            quizListener.stopMusic()
            navController.navigate(R.id.action_quizFragment_to_mainFragment)
        }
        val dialog: Dialog = builder.create()
        dialog.show()
    }



}