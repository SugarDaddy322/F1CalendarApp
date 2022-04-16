package com.example.f1calendarOP

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RaceListFragment : Fragment(R.layout.fragment_race_list), RacesAdapter.MyOnClickListener {

    private val racesAdapter: RacesAdapter by lazy { RacesAdapter(this) }
    private var raceList = ArrayList<RaceF1>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        var layoutManager = LinearLayoutManager(activity)

        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = racesAdapter

        val raceFunctions = RaceFunctions()
        raceFunctions.prepareRaceData(raceList, racesAdapter)
    }
    override fun onClick(race: RaceF1, position: Int) {
        val raceDetailFragment = RaceDetailFragment()
        val bundle = Bundle()

        bundle.putSerializable(RACE_DATE_KEY, race)
        raceDetailFragment.arguments = bundle
        val fragmentTransaction = parentFragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.flFragment, raceDetailFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
    companion object{
        const val RACE_DATE_KEY = "RACETRACK"
    }
}