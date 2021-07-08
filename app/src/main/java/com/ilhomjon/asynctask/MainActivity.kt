package com.ilhomjon.asynctask

import Adapters.UserAdapter
import DB.AppDatabase
import Entity.User
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ilhomjon.asynctask.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var appDatabase: AppDatabase
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)

        RvTask().execute()

        binding.btnSave.setOnClickListener {
            val user = User()
            user.userName = binding.edt1.text.toString()
            user.password = binding.edt2.text.toString()

            MyAsyncTask().execute(user)
        }

    }

    inner class RvTask:AsyncTask<Void, Void, List<User>>(){

        override fun onPreExecute() {
            super.onPreExecute()
            binding.progressbar.visibility = View.VISIBLE
        }
        override fun doInBackground(vararg params: Void?): List<User> {
            try {
                //Database tez ishlagani uchun to'xtatib turailmoqda. AsyncTask ni tekshirish uchun
                TimeUnit.SECONDS.sleep(3)
            }catch (e:Exception){
                e.printStackTrace()
            }
            return appDatabase.userDao().getAllUsers()
        }

        override fun onPostExecute(result: List<User>?) {
            super.onPostExecute(result)
            userAdapter = UserAdapter(result!!)
            binding.rv.adapter = userAdapter
            binding.progressbar.visibility = View.GONE
        }

    }

    inner class MyAsyncTask : AsyncTask<User, Void, Void>(){

        //taskni bajarishdan oldin ishlaydi
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: User): Void? {
            appDatabase.userDao().addUser(params[0])
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            Toast.makeText(this@MainActivity, "Saqlandi", Toast.LENGTH_SHORT).show()
        }

    }
}