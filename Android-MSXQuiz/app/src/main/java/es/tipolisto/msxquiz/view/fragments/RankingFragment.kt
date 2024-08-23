package es.tipolisto.msxquiz.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import es.tipolisto.msxquiz.R
import es.tipolisto.msxquiz.databinding.FragmentRankingBinding
import es.tipolisto.msxquiz.model.database.RecordEntity
import es.tipolisto.msxquiz.view.activities.RecyclerAdapter
import es.tipolisto.msxquiz.view.interfaces.QuizListener
import es.tipolisto.msxquiz.viewModel.RecordViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RankingFragment : Fragment() {
    private lateinit var binding: FragmentRankingBinding
    private lateinit var quizListener: QuizListener
    private lateinit var navController: NavController
    private val recordViewModel : RecordViewModel by viewModels()
    private var maxScore:Int =0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentRankingBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        initButtons()
        //Log.d(Constants.TAG,"Hola desde Rankingfragment")
        //Rellenamos la tabla records
        //lyfecyclescope nos permite utilizar bilders de corrutinas, el bilder principal se llama launch que utiliza el hilo principal
        lifecycleScope.launch {
            //Para sacar la petición del hilo principal utilizamos withContext y entre paréntesis el tipo de hilo que queremos que se ejecute, en este caso IO
            withContext(Dispatchers.IO){recordViewModel.setRecyclerView(requireActivity(),binding.rvRanking)}
        }
        //Obtenemos la lista de records con el viewModel
        //var listRecords= emptyList<RecordEntity>()
        //loginInternetViewModel.seeProgressBarchLiveData.observe(requireActivity()){
        /*recordViewModel.listRescordsMutableLiveData.observe(requireActivity()){
            listRecords=it
            fullRecyclerView(it)
            //Log.d(Constants.TAG,"Obtenidos dentro rankinfragment ${it.size.toString()}")
        }*/
        recordViewModel.getAll10Records()
        //Cando cambie el maxScore del viewModel actualizamos este
        recordViewModel.maxPointsLiveData.observe(requireActivity()) {
            maxScore = it
        }
        recordViewModel.getmaxPoints()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            quizListener = context as QuizListener
        } catch (castException: ClassCastException) {}
    }
    private fun fullRecyclerView(list:List<RecordEntity>){
        recordViewModel.getAll10Records()
        binding.rvRanking.addItemDecoration(DividerItemDecoration(binding.rvRanking.getContext(),DividerItemDecoration.HORIZONTAL))
        //Rellenamos el recyclerView
        binding.rvRanking.setHasFixedSize(true)
        binding.rvRanking.layoutManager=LinearLayoutManager(context)
        val recyclerAdapter= RecyclerAdapter()
        recyclerAdapter.RecyclerAdapter(list, requireContext())
        binding.rvRanking.adapter = recyclerAdapter
    }

    private fun initButtons() {
        binding.btnBack.setOnClickListener {
            quizListener.playButton("click")
            navController.navigate(R.id.action_rankingFragment_to_mainFragment)
        }
        binding.btSendMaxPoints.setOnClickListener {
            quizListener.playButton("click")
            val bundle= Bundle()
            bundle.putInt("maxScore",maxScore)
            navController.navigate(R.id.action_rankingFragment_to_loginInternetFragment,bundle)
            //Log.d(Constants.TAG,"Vamos al login")
        }
    }


}