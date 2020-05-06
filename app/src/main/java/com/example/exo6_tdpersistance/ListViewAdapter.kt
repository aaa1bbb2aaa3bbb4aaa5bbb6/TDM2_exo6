package com.example.exo6_tdpersistance

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.LayoutRes

/**
 * Created by Gokul on 2/11/2018.
 */
class ListViewAdapter(context: MainActivity, @LayoutRes private val layoutResource: Int,  var dataSource: List<Seance>) :  ArrayAdapter<Seance>(context, layoutResource, dataSource),
    Filterable {

    private val mInflator: LayoutInflater = LayoutInflater.from(context)
    private var mSeance: List<Seance> = dataSource
    override fun getCount(): Int {
        return dataSource.size
    }
    override fun getItem(position: Int): Seance? {
        return dataSource[position]
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        //val vh: ViewHolder


            view = this.mInflator.inflate(R.layout.list_view_item, parent, false)
          //  vh = ViewHolder(view)

            // Get each  element from one item
            val ensTextView = view.findViewById(R.id.ens) as TextView
            val moduleleTextView = view.findViewById(R.id.module) as TextView
            val groupeTextView = view.findViewById(R.id.groupe_filter) as TextView
            val anneeTextView = view.findViewById(R.id.anee) as TextView
            val jourTextView = view.findViewById(R.id.jour_filter) as TextView


        // 1
        val seance = getItem(position) as Seance

// 2
        ensTextView.setText("Enseignant: "+seance.enseignant)
        moduleleTextView.setText("Module: "+seance.module)
        groupeTextView.setText("Groupe: "+seance.groupe.toString())
        anneeTextView.setText("Annee: "+seance.anee.toString())
        jourTextView.setText("Jour: "+seance.jour.toString())


        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: Filter.FilterResults
            ) {
                if(filterResults.values != null)
                            mSeance = filterResults.values as List<Seance>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()

                val filterResults = Filter.FilterResults()




                filterResults.values = if (queryString == null || queryString.isEmpty()){
                    dataSource
                    Toast.makeText(context, "I'm not  in the filter treat", Toast.LENGTH_LONG).show()

                }

                else
                    dataSource.filter {
                        it.enseignant.contains(queryString) ||
                                it.module.contains(queryString)
                                || it.salle.equals(queryString.toInt())
                                || it.groupe.equals(queryString.toInt())
                                || it.heure.equals(queryString.toInt())

                    }
                Toast.makeText(context, "I'm in the filter treat", Toast.LENGTH_LONG).show()

                return filterResults
            }
        }
    }
}



/**
 * View holder
 *
 *
private class ViewHolder(row: View?) {
public val label: AppCompatTextView
init {
this.label = row?.findViewById<AppCompatTextView>(R.id.li_list_textview)!!
}
}
 */





