  package com.example.exo6_tdpersistance




import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.AutoCompleteTextView
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.*


  class MainActivity : AppCompatActivity() {
      val ADD_NOTE_REQUEST = 1
      private val EDIT_NOTE_REQUEST = 3
      private var appDatabase: AppDatabase? = null
      private var adapter:SeanceAdapter?= null
      lateinit var seances: List<Seance>
      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_main)
          appDatabase = AppDatabase.buildDatabase(this)!!
          val jsonList = "[\n" +
                  "\t\n" +
                  "\t{\n" +
                  "\t\t\tgroupe:1,\n" +
                  "\t\t\tanee:2020,\n" +
                  "\t\t\tjour:1,\n" +
                  "\t\t\theure:8,\n" +
                  "\t\t\tmodule:\"TDM1\",\n" +
                  "\t\t\tsalle:2,\n" +
                  "\t\t\tenseignant:\"Mokadam\"\n" +
                  "\n" +
                  "\t\t\t\t\n" +
                  "\t\t\t},\n" +
                  "\n" +
                  "\t\t\t{\n" +
                  "\t\t\tgroupe:2,\n" +
                  "\t\t\tanee:2020,\n" +
                  "\t\t\tjour:1,\n" +
                  "\t\t\theure:10,\n" +
                  "\t\t\tmodule:\"TDM2\",\n" +
                  "\t\t\tsalle:2,\n" +
                  "\t\t\tenseignant:\"Batata\"\n" +
                  "\t\t\t\t\n" +
                  "\t\t\t},\n" +
                  "\t\n" +
                  "\t\t{\n" +
                  "\t\t\tgroupe:1,\n" +
                  "\t\t\tanee:2020,\n" +
                  "\t\t\tjour:8,\n" +
                  "\t\t\theure:18,\n" +
                  "\t\t\tmodule:\"TDM3\",\n" +
                  "\t\t\tsalle:4,\n" +
                  "\t\t\tenseignant:\"Mostefai\"\n" +
                  "\t\t\t\t\n" +
                  "\t\t\t},\n" +
                  "\t{\n" +
                  "\t\t\tgroupe:2,\n" +
                  "\t\t\tanee:2020,\n" +
                  "\t\t\tjour:5,\n" +
                  "\t\t\theure:1,\n" +
                  "\t\t\tmodule:\"TDW1\",\n" +
                  "\t\t\tsalle:3,\n" +
                  "\t\t\tenseignant:\"Mokadam\"\n" +
                  "\t\t\t\t\n" +
                  "\t\t\t},\n" +
                  "\t\t\t{\n" +
                  "\t\t\tgroupe:1,\n" +
                  "\t\t\tanee:2020,\n" +
                  "\t\t\tjour:5,\n" +
                  "\t\t\theure:1,\n" +
                  "\t\t\tmodule:\"TDW2\",\n" +
                  "\t\t\tsalle:3,\n" +
                  "\t\t\tenseignant:\"Batata\"\n" +
                  "\t\t\t\t\n" +
                  "\t\t\t},\n" +
                  "\t\t\t{\n" +
                  "\t\t\tgroupe:2,\n" +
                  "\t\t\tanee:2020,\n" +
                  "\t\t\tjour:5,\n" +
                  "\t\t\theure:1,\n" +
                  "\t\t\tmodule:\"TDW3\",\n" +
                  "\t\t\tsalle:3,\n" +
                  "\t\t\tenseignant:\"Mostefai\"\n" +
                  "\t\t\t\t\n" +
                  "\t\t\t},\n" +
                  "\t\t\t{\n" +
                  "\t\t\tgroupe:3,\n" +
                  "\t\t\tanee:2020,\n" +
                  "\t\t\tjour:9,\n" +
                  "\t\t\theure:1,\n" +
                  "\t\t\tmodule:\"TDD1\",\n" +
                  "\t\t\tsalle:3,\n" +
                  "\t\t\tenseignant:\"Mokadam\"\n" +
                  "\t\t\t\t\n" +
                  "\t\t\t},\n" +
                  "\t\t\t{\n" +
                  "\t\t\tgroupe:2,\n" +
                  "\t\t\tanee:2020,\n" +
                  "\t\t\tjour:11,\n" +
                  "\t\t\theure:1,\n" +
                  "\t\t\tmodule:\"TDD2\",\n" +
                  "\t\t\tsalle:1,\n" +
                  "\t\t\tenseignant:\"Batata\"\n" +
                  "\t\t\t\t\n" +
                  "\t\t\t},\n" +
                  "\t\t\t\t{\n" +
                  "\t\t\tgroupe:1,\n" +
                  "\t\t\tanee:2020,\n" +
                  "\t\t\tjour:3,\n" +
                  "\t\t\theure:1,\n" +
                  "\t\t\tmodule:\"TDD3\",\n" +
                  "\t\t\tsalle:2,\n" +
                  "\t\t\tenseignant:\"Mostefai\"\n" +
                  "\t\t\t\t\n" +
                  "\t\t\t},\n" +
                  "\t\t\t\t{\n" +
                  "\t\t\tgroupe:3,\n" +
                  "\t\t\tanee:2020,\n" +
                  "\t\t\tjour:11,\n" +
                  "\t\t\theure:16,\n" +
                  "\t\t\tmodule:\"Science1\",\n" +
                  "\t\t\tsalle:1,\n" +
                  "\t\t\tenseignant:\"Mokadam\"\n" +
                  "\t\t\t\t\n" +
                  "\t\t\t},\n" +
                  "\t\t\t\t{\n" +
                  "\t\t\tgroupe:1,\n" +
                  "\t\t\tanee:2020,\n" +
                  "\t\t\tjour:11,\n" +
                  "\t\t\theure:8,\n" +
                  "\t\t\tmodule:\"Science2\",\n" +
                  "\t\t\tsalle:1,\n" +
                  "\t\t\tenseignant:\"Batata\"\n" +
                  "\t\t},\n" +
                  "\n" +
                  "\t\t{\n" +
                  "\t\t\tgroupe:2,\n" +
                  "\t\t\tanee:2020,\n" +
                  "\t\t\tjour:9,\n" +
                  "\t\t\theure:1,\n" +
                  "\t\t\tmodule:\"Science3\",\n" +
                  "\t\t\tsalle:3,\n" +
                  "\t\t\tenseignant:\"Mostefai\"\n" +
                  "\t\t\t\t\n" +
                  "\t\t\t}\t\t\n" +
                  "\t\t\t\n" +
                  "\n" +
                  "\n" +
                  "\n" +
                  "\n" +
                  "\n" +
                  "\n" +
                  "\n" +
                  "\t\t]"
          val gson = Gson()
          val arrayTutorialType = object : TypeToken<Array<Seance>>() {}.type
          var jsonString = load()
          val seancesArray:Array<Seance> = gson.fromJson(jsonList, arrayTutorialType)
            seances = seancesArray.toMutableList()
          seances?.forEachIndexed { idx, tut -> println("> Item ${idx}:\n${tut}") }
          seances?.let { it1 -> InsertSeances(this, it1).execute() }
          setUpRecyclerView();
          save(jsonList)
          val db = Room.databaseBuilder(
              applicationContext,
              AppDatabase::class.java, "emploi.db"
          ).build()

          //module_filter.setAdapter(adapter)












          GlobalScope.launch {

            //load initialjson data
              val jsonSeaces= load()

              val data = db.seanceDao().getAll()
              data?.forEach {
                  println("this is a seance"+it)
                  Log.d("SEANCE","seance"+it)
                // tvDatafromDb.setText("le module"+it.module)

              }
        }

       /* btnDisplay.setOnClickListener {
              GetDataFromDb(this).execute()
          }*/


      }


      private class InsertSeances(var context: MainActivity, val seances: List<Seance>) :
          AsyncTask<Void, Void, Boolean>() {
          override fun doInBackground(vararg params: Void?): Boolean {

              context.appDatabase!!.seanceDao().insertAll(seances)
              return true
          }

          override fun onPostExecute(bool: Boolean?) {
              if (bool!!) {
                  Toast.makeText(context, "Added to Database", Toast.LENGTH_LONG).show()
              }
          }
      }

      private class GetDataFromDb(var context: MainActivity) :
          AsyncTask<Void, Void, List<Seance>>() {
          override fun doInBackground(vararg params: Void?): List<Seance> {
              return context.appDatabase!!.seanceDao().getAll()
          }

          override fun onPostExecute(seanceList: List<Seance>?) {
              if (seanceList!!.size > 0) {
                  for (i in 0..seanceList.size - 1) {
                     // context.tvDatafromDb.append(seanceList[i].module)
                  }
              }
          }
      }

      fun save(jsonFile:String) {

          var fos: FileOutputStream? = null
          try {
              fos = openFileOutput("seances.json", Context.MODE_PRIVATE)
              fos.write(jsonFile.toByteArray())

              Toast.makeText(
                  this, "Saved to $filesDir/seances.json",
                  Toast.LENGTH_LONG
              ).show()
          } catch (e: FileNotFoundException) {
              e.printStackTrace()
          } catch (e: IOException) {
              e.printStackTrace()
          } finally {
              if (fos != null) {
                  try {
                      fos.close()
                  } catch (e: IOException) {
                      e.printStackTrace()
                  }
              }
          }
      }


          fun load(): String {
              var fis: FileInputStream? = null
              var response = ""
              try {
                  fis = openFileInput("seances.json")
                  val isr = InputStreamReader(fis)
                  val br = BufferedReader(isr)
                  print("br variable : "+br)
                  val sb = StringBuilder()
                  var text: String?
                  while (br.readLine().also { text = it } != null) {
                      sb.append(text).append("\n")
                  }
                  /*Toast.makeText(
                  this, sb.toString(),
                  Toast.LENGTH_LONG
              ).show()*/

                  response = sb.toString()


              } catch (e: FileNotFoundException) {
                  e.printStackTrace()
              } catch (e: IOException) {
                  e.printStackTrace()
              } finally {
                  if (fis != null) {
                      try {
                          fis.close()
                      } catch (e: IOException) {
                          e.printStackTrace()
                      }
                  }
              }


              return response
          }

      fun loadAsJson(): String {
          var fis: FileInputStream? = null
          var response = ""
          try {
              fis = openFileInput("seances.json")
              val isr = InputStreamReader(fis)
              val br = BufferedReader(isr)

              print("br variable : "+br)
              val sb = StringBuilder()
              var text: String?
              while (br.readLine().also { text = it } != null) {
                  sb.append(text).append("\n")
              }
              /*Toast.makeText(
              this, sb.toString(),
              Toast.LENGTH_LONG
          ).show()*/

              response = sb.toString()


          } catch (e: FileNotFoundException) {
              e.printStackTrace()
          } catch (e: IOException) {
              e.printStackTrace()
          } finally {
              if (fis != null) {
                  try {
                      fis.close()
                  } catch (e: IOException) {
                      e.printStackTrace()
                  }
              }
          }


          return response
      }

      private fun setUpRecyclerView() {
          val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
          recyclerView.setHasFixedSize(true)
          val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
          adapter = SeanceAdapter(seances)
          recyclerView.layoutManager = layoutManager
          recyclerView.setAdapter(adapter)
      }


      override fun onCreateOptionsMenu(menu: Menu): Boolean {
          val inflater = menuInflater
          inflater.inflate(R.menu.example_menu, menu)
          val searchItem: MenuItem = menu.findItem(R.id.action_search)
          val searchView: SearchView = searchItem.getActionView() as SearchView
          searchView.setImeOptions(EditorInfo.IME_ACTION_DONE)

          searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
              override fun onQueryTextSubmit(query: String?): Boolean {
                  return false
              }

              override fun onQueryTextChange(newText: String?): Boolean {
                  Toast.makeText(applicationContext, "newText: "+newText, Toast.LENGTH_LONG).show()
                  adapter?.getFilter()?.filter(newText)
                  return false
              }
          })
          return true
      }
  }



