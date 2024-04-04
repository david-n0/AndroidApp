package rs.raf.projekatjun.david_nikolic_9419.ui.main.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.david_nikolic_9419.ui.states.AddEventState
import rs.raf.projekatjun.david_nikolic_9419.R
import rs.raf.projekatjun.david_nikolic_9419.databinding.FragmentAddEventBinding
import rs.raf.projekatjun.david_nikolic_9419.model.EventEntity
import rs.raf.projekatjun.david_nikolic_9419.ui.main.MainFragment
import rs.raf.projekatjun.david_nikolic_9419.viewmodel.EventViewModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*


class AddEventFragment : Fragment(R.layout.fragment_add_event) {
    private var _binding: FragmentAddEventBinding? = null
    private val binding get() = _binding!!

    private val eventViewModel: MainContract.ViewModel by sharedViewModel<EventViewModel>()

    // private lateinit var menuAdapter: MenuAdapter

    var cal = Calendar.getInstance()
    private lateinit var city: String

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEventBinding.inflate(inflater, container, false)
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
    }

    private fun initListeners() {

        //SPINNER
        var list = resources.getStringArray(R.array.cities)
        var adapter = ArrayAdapter<String>(
                this.requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                list
        )

        binding.cityTv.setAdapter(adapter)

        //BACK BUTTON
        binding.btnBack.setOnClickListener {

            val cl = binding.addEventFragment
            cl.removeAllViews()

            val transaction = this.childFragmentManager.beginTransaction()
            transaction.add(R.id.addEventFragment, MainFragment())
            transaction.commit()
        }

        //DATE
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                    view: DatePicker, year: Int, monthOfYear: Int,
                    dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }


        binding.btnSetDate.setOnClickListener {

            DatePickerDialog(
                    this.requireContext(),
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

        //TIME
        binding.btnSetTime.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                binding.btnSetTime.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(
                    this.requireContext(),
                    timeSetListener,
                    cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE),
                    true
            ).show()
        }

        //TIMEZONE
        binding.btnCheckTime.setOnClickListener {
            city = binding.cityTv.text.toString()

            eventViewModel.getTimeForCity(city)
            eventViewModel.fetchTimeForCity(city)
        }

        //ADD EVENT
        binding.btnSaveEvent.setOnClickListener {

            if (binding.eventNameTv.text.toString().isNotEmpty() && binding.descriptionTv.text.toString().isNotEmpty() && binding.urlTv.text.toString().isNotEmpty()) {
                if (!(binding.btnSetDate.text.toString().equals("Set Date")) || !(binding.btnSetTime.text.toString().equals("Set Time"))) {
                    val name = binding.eventNameTv.text.toString()
                    val description = binding.descriptionTv.text.toString()
                    val date = binding.btnSetDate.text.toString()
                    val time = binding.btnSetTime.text.toString()
                    val url = binding.urlTv.text.toString()
                    val priority = binding.prioritySpinner.getSelectedItem().toString()

                    var newEvent = EventEntity(
                            name,
                            description,
                            date,
                            time,
                            url,
                            priority)
                    eventViewModel.addEvent(newEvent)
                        Log.d("SAVING EVENT", newEvent.name + "   " + newEvent.description + "   " + newEvent.url)
                } else {
                    Log.d("FILLFIELDS", "time or Date not filled")
                    Toast.makeText(this.context, R.string.dateTimefieldsEmptyException, Toast.LENGTH_LONG).show()
                }
            } else {
                Log.d("FILLFIELDS", "some are not filled")

                Toast.makeText(this.context, R.string.fieldsEmptyException, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initObservers() {
        eventViewModel.addEventState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
    }

    private fun renderState(state: AddEventState) {
        when (state) {
            is AddEventState.Success -> {
                binding.btnCheckTime.text = state.time.datetime
                Log.d("TIMEZONE2/ADDFRAG", state.time.datetime)
            }
            is AddEventState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.btnSetDate.text = sdf.format(cal.time)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}