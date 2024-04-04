package rs.raf.projekatjun.david_nikolic_9419.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.david_nikolic_9419.ui.states.EventsState
import rs.raf.projekatjun.david_nikolic_9419.R
import rs.raf.projekatjun.david_nikolic_9419.databinding.FragmentShowEventsBinding
import rs.raf.projekatjun.david_nikolic_9419.databinding.MainFragmentBinding
import rs.raf.projekatjun.david_nikolic_9419.model.EventEntity
import rs.raf.projekatjun.david_nikolic_9419.ui.main.MainFragment
import rs.raf.projekatjun.david_nikolic_9419.ui.main.fragments.adapter.EventAdapter
import rs.raf.projekatjun.david_nikolic_9419.viewmodel.EventViewModel
import timber.log.Timber


class ShowEventsFragment : Fragment(R.layout.fragment_show_events) {
    private var _binding: FragmentShowEventsBinding? = null
    private val binding get() = _binding!!

    private val eventViewModel: MainContract.ViewModel by sharedViewModel<EventViewModel>()

    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initListeners()
        initRecycler()

    }

    private fun initListeners() {
        //BACKBUTTON
        binding.btnBack.setOnClickListener {

            val cl = binding.showEventsFragment
            cl.removeAllViews()

            val transaction = this.childFragmentManager.beginTransaction()
            transaction.add(R.id.showEventsFragment, MainFragment())
            transaction.commit()
        }


    }

    private fun initRecycler() {
        binding.EventListRv.layoutManager = LinearLayoutManager(this.context)
        eventAdapter = EventAdapter(this)
        binding.EventListRv.adapter = eventAdapter

    }

    private fun initObservers() {
        eventViewModel.showEventState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })

        eventViewModel.getAllEvents()

    }

    private fun renderState(state: EventsState) {
        when (state) {
            is EventsState.Success -> {
                eventAdapter.submitList(state.events)
            }
            is EventsState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is EventsState.DataFetched -> {
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG)
                        .show()
            }
            is EventsState.Loading -> {
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}