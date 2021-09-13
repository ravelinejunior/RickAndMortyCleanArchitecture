package br.com.raveline.appnew.presentation.adapter

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.raveline.appnew.R
import br.com.raveline.appnew.data.model.Character
import br.com.raveline.appnew.databinding.ItemCharacterAdapterBinding
import br.com.raveline.appnew.utils.CharactersDiffUtil
import com.bumptech.glide.Glide

class CharactersAdapter(private val context: Application) :
    RecyclerView.Adapter<CharactersAdapter.MyViewHolder>() {

    private var characters = emptyList<Character>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val bindingAdapter =
            ItemCharacterAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(bindingAdapter)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character, context)
    }

    override fun getItemCount(): Int = characters.size

    fun setData(newData: List<Character>) {
        val characterDiffUtil = CharactersDiffUtil(characters, newData)
        val diffUtilResult = DiffUtil.calculateDiff(characterDiffUtil)
        characters = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

    inner class MyViewHolder(private val bindingAdapter: ItemCharacterAdapterBinding) :
        RecyclerView.ViewHolder(bindingAdapter.root) {

        @SuppressLint("SetTextI18n")
        fun bind(character: Character, context: Context) {
            bindingAdapter.apply {
                textViewCharacterName.text = character.name
                textViewCharacterAdapterStatus.text = character.status
                textViewCharacterAdapterSpecie.text = "- ${character.species}"

                if (character.location != null)
                    textViewCharacterAdapterLastKnowLocation.text = character.location.name
                else textViewCharacterAdapterLastKnowLocation.text = "Unknown"

                if (character.episode != null)
                    textViewCharacterAdapterFirstSeenIn.text = character.episode[0].toString()
                else textViewCharacterAdapterFirstSeenIn.text = "Unknown"

                Glide.with(imageViewCharacterAdapter.context).load(character.image)
                    .into(imageViewCharacterAdapter)

                setColorCharacterStatus(character, context)


            }

            bindingAdapter.executePendingBindings()
        }

        private fun setColorCharacterStatus(character: Character, context: Context) {
            if (character.status!!.equals("Dead", ignoreCase = true)) {
                bindingAdapter.viewCharacterAdapterStatus.background = ContextCompat.getDrawable(
                    context, R.drawable.dot_character_adapter_dead
                )
            } else if (character.status.equals("unknown", ignoreCase = true)) {
                bindingAdapter.viewCharacterAdapterStatus.background = ContextCompat.getDrawable(
                    context, R.drawable.dot_character_adapter_unknown
                )
            } else {
                bindingAdapter.viewCharacterAdapterStatus.background = ContextCompat.getDrawable(
                    context, R.drawable.dot_character_adapter
                )
            }
        }
    }
}