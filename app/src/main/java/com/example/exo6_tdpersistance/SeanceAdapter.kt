package com.example.exo6_tdpersistance

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class SeanceAdapter(items : List<Seance>) : RecyclerView.Adapter<SeanceAdapter.ViewHolder>(),Filterable{

    private var listSeances:MutableList<Seance> = items.toMutableList()
    private var exampleListFull: List<Seance>? = null


    override fun getItemCount(): Int {
        return listSeances.size
    }

    fun SeanceAdapter(exampleList: MutableList<Seance>) {
        listSeances = exampleList
        exampleListFull = ArrayList(listSeances)
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        // Get each  element from one item
        val ensTextView = view.findViewById(R.id.ens) as TextView
        val moduleleTextView = view.findViewById(R.id.module) as TextView
        val groupeTextView = view.findViewById(R.id.groupe_filter) as TextView
        val anneeTextView = view.findViewById(R.id.anee) as TextView
        val jourTextView = view.findViewById(R.id.jour_filter) as TextView
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = listSeances[position]
        holder.ensTextView.setText("Enseignant: "+listItem.enseignant)
        holder.moduleleTextView.setText("Module: "+listItem.module)
        holder.groupeTextView.setText("Groupe: "+listItem.groupe.toString())
        holder.anneeTextView.setText("Annee: "+listItem.anee.toString())
        holder.jourTextView.setText("Jour: "+listItem.jour.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item,parent,false))
    }


    override fun getFilter(): Filter? {
       // Toast.makeText(parent.getContext(),"from getFilter: ",Toast.LENGTH_LONG )

        return exampleFilter
    }

    private val exampleFilter: Filter = object : Filter() {


        protected override fun performFiltering(constraint: CharSequence?): FilterResults? {

           // Toast.makeText(ctx,"from perform filtering: ",Toast.LENGTH_LONG )
            val filteredList: MutableList<Seance> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                if (exampleListFull != null) {
                   // Toast.makeText(ctx,"exempleFullList is not null: ",Toast.LENGTH_LONG )
                    filteredList.addAll(exampleListFull!!)

                }
            } else {
               // Toast.makeText(ctx,"exempleFullList is  null: ",Toast.LENGTH_LONG )
                println("exampleFullList:"+exampleListFull)
                val filterPattern = constraint.toString().toLowerCase()
                if (exampleListFull != null) {
                    for (item in exampleListFull!!) {
                      //  Toast.makeText(ctx,"filterPattern: "+filterPattern,Toast.LENGTH_LONG )
                        println("FilteredList items one by one :"+item)
                            if(item.enseignant.toLowerCase().contains(filterPattern) || item.module.toLowerCase().contains(filterPattern)  || item.groupe.equals(filterPattern.toInt()) || item.salle.equals(filterPattern.toInt() )){
                                filteredList.add(item)

                            }


                        //item.enseignant.toLowerCase().contains(filterPattern) || item.module.toLowerCase().contains(filterPattern)  || item.groupe.equals(filterPattern.toInt()) || item.salle.equals(filterPattern.toInt() )
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        protected override fun publishResults(
            constraint: CharSequence?,
            results: FilterResults
        ) {
            listSeances.clear()

            println("results.values "+results.values.toString())
            println("isEmpty "+results.values.toString().isNullOrEmpty())
            for (item in results.values as ArrayList<Seance>) {
                println("item:  "+results.values)
                listSeances.add( item);

            }

           // listSeances.addAll( results.values as MutableList<Seance>);
            notifyDataSetChanged()
        }
    }


}


