package br.com.raveline.appnew.presentation.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
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

    private val args by navArgs<HomeFragmentArgs>()

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

        if(!args.isBacking){
            getData()
        }

        return mBinding!!.root
    }

    private fun getData(){
        lifecycleScope.launchWhenStarted {
            charactersViewModel.charactersLocalLiveData.observe(viewLifecycleOwner,{
                    databaseCharacters ->
                try{
                    if(databaseCharacters.isNotEmpty()){
                        mAdapter.setData(databaseCharacters[0].characters.results!!)
                        Log.i("TAGFRAGMENT", "getData: from database")
                        hideProgressBar()
                    }else{
                        requestDataFromApi()
                        Log.i("TAGFRAGMENT", "getData: from api")
                    }
                }catch (e:Exception){
                    mAdapter.setData(databaseCharacters[0].characters.results!!)
                    Log.i("TAGFRAGMENT", "getData: from database")
                    hideProgressBar()
                }
            })
        }
    }

    private fun requestDataFromApi() {
        charactersViewModel.getAllCharacters()
        charactersViewModel.charactersLiveData.observe(viewLifecycleOwner, { response ->
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

        mBinding!!.toolbarFragmentHome.apply {
            title = "Rick And Morty"
            subtitle = "Bitches, Wabalabdabdub!"
            setTitleTextColor(Color.WHITE)
            setSubtitleTextColor(Color.WHITE)
            textAlignment = TEXT_ALIGNMENT_CENTER
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }


}