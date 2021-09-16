package br.com.raveline.appnew.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.raveline.appnew.R
import br.com.raveline.appnew.data.model.Character
import br.com.raveline.appnew.databinding.FragmentDetailsBinding
import com.bumptech.glide.Glide

class DetailsFragment : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()

    private lateinit var mBinding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentDetailsBinding.inflate(inflater, container, false)



        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val character = args.character

        loadUI(character)

    }

    private fun loadUI(character: Character) {
        mBinding.apply {

            mBinding.toolbarFragmentDetails.apply {
                title = character.name
                navigationIcon = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_arrow_back_24
                )
                setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.white))

                setNavigationOnClickListener {
                    val action = DetailsFragmentDirections.actionDetailsFragmentToHomeFragment().setIsBacking(true)
                    findNavController().navigate(action)
                }

            }

            textViewFragmentDetailName.text = character.name
            textViewFramentDetailsSpecie.text = character.species
            textViewFragementDetailsOrigin.text = character.origin!!.name
            textViewFragmentDetailStatus.text = character.status

            Glide.with(imageViewFragmentDetail.context).load(character.image)
                .into(imageViewFragmentDetail)

            if (character.gender!!.equals("Male", ignoreCase = true)) {
                Glide.with(imageViewFragmentDetailSex.context)
                    .load(ContextCompat.getDrawable(requireContext(), R.drawable.gender_man))
                    .into(imageViewFragmentDetailSex)
            } else {
                Glide.with(imageViewFragmentDetailSex.context)
                    .load(ContextCompat.getDrawable(requireContext(), R.drawable.gender_woman))
                    .into(imageViewFragmentDetailSex)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {


            Log.i("TAGFRAGMENT", "${item.itemId} = ${android.R.id.home}")
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}