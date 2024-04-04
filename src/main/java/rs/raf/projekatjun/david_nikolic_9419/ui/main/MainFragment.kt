package rs.raf.projekatjun.david_nikolic_9419.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.bind
import rs.raf.projekatjun.david_nikolic_9419.R
import rs.raf.projekatjun.david_nikolic_9419.databinding.MainFragmentBinding
import rs.raf.projekatjun.david_nikolic_9419.ui.main.fragments.AddEventFragment
import rs.raf.projekatjun.david_nikolic_9419.ui.main.fragments.ShowEventsFragment


class MainFragment : Fragment(R.layout.main_fragment) {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    // private val menuViewModel: MainContract.ViewModel by sharedViewModel<RecipeViewModel>()

    // private lateinit var menuAdapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        initListeners()

    }

    private fun initListeners() {
        binding.btnAddEvent.setOnClickListener {

            val cl = binding.mainFragment
            cl.removeAllViews()

            val transaction = this.childFragmentManager.beginTransaction()
            transaction.add(R.id.mainFragment, AddEventFragment())
            transaction.commit()
        }

        binding.btnShowEvents.setOnClickListener {

            val cl = binding.mainFragment
            cl.removeAllViews()

            val transaction = this.childFragmentManager.beginTransaction()
            transaction.add(R.id.mainFragment, ShowEventsFragment())
            transaction.commit()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}