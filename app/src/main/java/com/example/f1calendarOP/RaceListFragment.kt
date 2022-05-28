package com.example.f1calendarOP

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f1calendarOP.databinding.ActivityMainBinding
import kotlinx.coroutines.NonDisposableHandle.parent
import retrofit2.HttpException
import java.io.IOException

const val TAG = "RaceListFragment"

class RaceListFragment : Fragment(R.layout.fragment_race_list), RacesAdapter.MyOnClickListener {

    private val racesAdapter: RacesAdapter by lazy { RacesAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val listItems : View = inflater.inflate(R.layout.fragment_race_list, container, false)
        val recyclerView = listItems.findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            itemAnimator = DefaultItemAnimator()
            adapter = racesAdapter
        }
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        return binding.root
        return listItems
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getRaceInfo()
            } catch (e: IOException){
                Log.e(TAG, e.message.toString())
                return@launchWhenCreated
            } catch(e: HttpException){
                Log.e(TAG, e.message.toString())
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null){

                val raceResponse : RaceResponse = response.body()!!
                val responseRaceList : List<Race> = raceResponse.MRData.RaceTable.Races
                racesAdapter.raceList = responseRaceList
            } else{
                Log.e(TAG, "Response not successful")
            }
        }
    }
    override fun onClick(race: Race, position: Int) {
//        val raceDetailFragment = RaceDetailFragment()
//        val bundle = Bundle()
        Toast.makeText(context,"aboba", Toast.LENGTH_SHORT).show()

//        bundle.putSerializable(RACE_DATE_KEY, race)
//        raceDetailFragment.arguments = bundle
//        val fragmentTransaction = parentFragmentManager.beginTransaction()
//        fragmentTransaction.apply {
//            add(R.id.flFragment, raceDetailFragment)
//            addToBackStack(null)
//            commit()
//        }
    }
    companion object{
        const val RACE_DATE_KEY = "RACETRACK"
    }
}