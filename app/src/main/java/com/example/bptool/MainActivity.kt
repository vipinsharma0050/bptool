package com.example.bptool

//import android.support.v7.app.AppCompatActivity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bptool.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityMainBinding
    private var listTitle = ArrayList<ListTitle>()
     var combinedListData = ArrayList<CombinedTitle>()
     var combinedListDataRSA = ArrayList<CombinedTitle>()
     var combinedListDataDES = ArrayList<CombinedTitle>()
     var combinedListDataTDES = ArrayList<CombinedTitle>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        val json = getJsonFromAsset(this, "Sample.json")
        val gson = Gson() //get Gson class into a variable gson
        val response = gson.fromJson(json, ResponseModel::class.java) // get both algo from respnse Model
        if (response != null) {
            //for hashAlgo
            for (i in 0 until response.HashAlgo?.size!!)
            {
                combinedListData.clear()
                listTitle.add(ListTitle("MD_hash", combinedListData , response.HashAlgo!!.get(i).MD_hash, emptyList(), false))
                        listTitle.add(ListTitle("CRC_hash",combinedListData, response.HashAlgo!!.get(i).CRC_hash, emptyList(), false))
                        listTitle.add(ListTitle("Generic_hash",combinedListData, response.HashAlgo!!.get(i).Generic_hash, emptyList(), false))
                        listTitle.add(ListTitle("SHA_hash",combinedListData, response.HashAlgo!!.get(i).SHA_hash, emptyList(), false))

            }


            //for encrption....
          for (i in 0 until response.encrption?.size!!) {
              combinedListData.clear()
                println("====>New List encryption Data " + gson.toJson(response.encrption))
                for (j in 0 until response.encrption!!.get(i).AES?.size!!) {
                    combinedListData.clear()
                    combinedListDataDES.clear()
                    combinedListDataTDES.clear()
                    combinedListDataRSA.clear()
                    val myList = ArrayList<String>()
                    myList.add(response.encrption!!.get(i).AES?.get(j)?.bit_size.toString())
                    myList.add(response.encrption!!.get(i).AES?.get(j)?.mode.toString())

                    val myListDES = ArrayList<String>()
                    myListDES.add(response.encrption!!.get(i).DES?.get(j)?.padding_type.toString())

                    val myListTDES = ArrayList<String>()
                    myListTDES.add(response.encrption!!.get(i).TDES?.get(j)?.mode.toString())
                    myListTDES.add(response.encrption!!.get(i).TDES?.get(j)?.padding_type.toString())

                    val myListRSA = ArrayList<String>()
                    myListRSA.add(response.encrption!!.get(i).RSA?.get(j)?.key_size.toString())

                    combinedListData.add(CombinedTitle("MODE" , response.encrption!!.get(i).AES?.get(j)?.mode!!))
                    combinedListData.add(CombinedTitle("BIT_SIZE" , response.encrption!!.get(i).AES?.get(j)?.bit_size!!))
                    listTitle.add(ListTitle("AES",combinedListData, myList, emptyList(), true))

                   /* combinedListDataRSA.add(CombinedTitle("Key Size" , response.encrption!!.get(i).RSA?.get(j)?.key_size!!))
                    listTitle.add(ListTitle("RSA",combinedListDataRSA, myListRSA, emptyList(), true))

                    combinedListDataTDES.add(CombinedTitle("MODE" , response.encrption!!.get(i).TDES?.get(j)?.mode!!))
                    combinedListDataTDES.add(CombinedTitle("Padding" , response.encrption!!.get(i).TDES?.get(j)?.padding_type!!))
                    listTitle.add(ListTitle("TDES",combinedListDataTDES, myListTDES, emptyList(), true))

                    combinedListDataDES.add(CombinedTitle("Padding" , response.encrption!!.get(i).DES?.get(j)?.padding_type!!))
                    listTitle.add(ListTitle("DES",combinedListDataDES, myListDES, emptyList(), true))
              */

                }
            }
        }

        // getting the recyclerview by its id
        recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = DataItemAdapter(this, listTitle)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        /*mBinding.parseButton.setOnClickListener {

            val json = getJsonFromAsset(this, "Sample.json")
            val gson = Gson()
            val response = gson.fromJson(json, ResponseModel::class.java)
            var responses = ArrayList<ResponseModel>()
            if (response != null) {
                for (i in 0 until response.HashAlgo?.size!!) {
                    println("--->")
                }
                for (i in 0 until response.encrption?.size!!) {
                    println("====>New List encryption Data " + gson.toJson(response.encrption))
                    for (j in 0 until response.encrption!!.get(i).AES?.size!!) {
                        list.addAll(response.encrption!!.get(i).AES!!)
                        println("====>New List AES Data " + gson.toJson(response.encrption!!.get(i).AES))
                    }
                }
            }

            // getting the recyclerview by its id

            // this creates a vertical layout Manager
            recyclerview.layoutManager = LinearLayoutManager(this)

            // ArrayList of class ItemsViewModel
          //  val data = ArrayList<AES>()
            // This will pass the ArrayList to our Adapter
            val adapter = DataItemAdapter(this , list)

            // Setting the Adapter with the recyclerview
            recyclerview.adapter = adapter





            Log.e(
                "my response",
                response.encrption?.get(0)?.RSA?.get(0)?.key_size?.get(0).toString()
            )

            Log.e(
                "my response 2",
                response.encrption?.get(0)?.DES?.get(0)?.padding_type?.get(0).toString()
            )
        }*/

    }

    /*override fun onResume() {
        super.onResume()
        val json = getJsonFromAsset(this, "Sample.json")
        val gson = Gson() //get Gson class into a variable gson
        val response = gson.fromJson(json, ResponseModel::class.java) // get both algo from respnse Model
        var responses = ArrayList<ResponseModel>()
        if (response != null) {
            for (i in 0 until response.HashAlgo?.size!!) {
                println("--->")
            }
            for (i in 0 until response.encrption?.size!!) {
                println("====>New List encryption Data " + gson.toJson(response.encrption))
                for (j in 0 until response.encrption!!.get(i).AES?.size!!) {
                    for (k in 0 until response.encrption!!.get(j).AES?.size!!) {
                        var strList = ArrayList<String>()
                        strList.add(response.encrption!!.get(i).AES?.get(j)?.mode.toString())
                        strList.add(response.encrption!!.get(i).AES?.get(j)?.bit_size.toString())
                        listTitle.add(ListTitle("AES" , strList, emptyList()))
                        listTitle.add(ListTitle("RSA" , response.encrption!!.get(i).RSA?.get(j)?.key_size, response.encrption!!.get(i).RSA?.get(j)?.key_size))
                        listTitle.add(ListTitle("TDES" , response.encrption!!.get(i).TDES?.get(j)?.mode, response.encrption!!.get(i).TDES?.get(j)?.padding_type))
                        listTitle.add(ListTitle("DES" , response.encrption!!.get(i).DES?.get(j)?.padding_type,  emptyList()))
                    }
                }
            }
        }

    }
}*/

    //Get data from assets folder
    fun getJsonFromAsset(context: Context, fileName: String?): String? {
        var json: String? = null
        json = try {
            val json: InputStream = context.assets.open("sample.json")
            val size: Int = json.available()
            val buffer = ByteArray(size)
            json.read(buffer)
            json.close()
            String(buffer)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return json
    }
}