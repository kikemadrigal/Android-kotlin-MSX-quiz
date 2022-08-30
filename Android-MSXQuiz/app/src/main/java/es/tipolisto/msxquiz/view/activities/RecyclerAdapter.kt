package es.tipolisto.msxquiz.view.activities

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.tipolisto.msxquiz.R
import es.tipolisto.msxquiz.model.database.RecordEntity
import es.tipolisto.msxquiz.util.Constants

/*
** Coded by David Montes on 21/03/2022.
** https://github.com/davidthar
*/

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var ranking: List<RecordEntity>  = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapter(ranking : List<RecordEntity>, context: Context){
        this.ranking = ranking
        this.context = context
        //Log.d(Constants.TAG,"Obtenidos dentro del adapter ${ranking.size.toString()}")
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = ranking[position]
        holder.bind(item, position)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.ranking_item, parent, false))
    }
    override fun getItemCount(): Int {
        return ranking.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val position = view.findViewById(R.id.tvPosition) as TextView
        val name = view.findViewById(R.id.tvName) as TextView
        private val points = view.findViewById(R.id.tvPoints) as TextView

        fun bind(ranking:RecordEntity, positionNumber : Int){
            position.text = (positionNumber+1).toString()
            name.text = ranking.name
            points.text = ranking.score.toString()
        }
    }
}