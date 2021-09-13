package br.com.raveline.appnew.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.raveline.appnew.databinding.FragmentHomeBinding
import br.com.raveline.appnew.domain.util.Resource
import br.com.raveline.appnew.presentation.adapter.CharactersAdapter
import br.com.raveline.appnew.presentation.viewmodels.CharactersViewModel
import br.com.raveline.appnew.presentation.viewmodels.CharactersViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var mBinding: FragmentHomeBinding? = null

    private lateinit var charactersViewModel: CharactersViewModel

    @Inject
    lateinit var factory: CharactersViewModelFactory

    @Inject
    lateinit var mAdapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        charactersViewModel = ViewModelProvider(this, factory)[CharactersViewModel::class.java]
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        mBinding!!.lifecycleOwner = this

        setHasOptionsMenu(true)

        setupRecyclerView()

        lifecycleScope.launchWhenStarted {
            requestDataFromApi()
        }

        return mBinding!!.root
    }

    private fun requestDataFromApi() {
        charactersViewModel.getAllCharacters()
        charactersViewModel.charactersMutableLiveData.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    response.data.let {
                        mAdapter.setData(it?.results!!)
                    }
                    hideProgressBar()
                }

                is Resource.Loading -> {
                    showProgressBar()
                }

                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("TAGError", response.message.toString())
                }
            }


        })
    }

    private fun showProgressBar() {
        mBinding!!.progressBarHomeFragment.visibility = VISIBLE
    }

    private fun hideProgressBar() {
        mBinding!!.progressBarHomeFragment.visibility = GONE
    }

    private fun setupRecyclerView() {

        mBinding!!.recyclerViewHomeFragment.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }


}