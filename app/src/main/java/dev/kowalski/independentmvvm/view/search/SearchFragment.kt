package dev.kowalski.independentmvvm.view.search

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import dev.kowalski.independentmvvm.MainActivity
import dev.kowalski.independentmvvm.R
import dev.kowalski.independentmvvm.api.api.model.LyricsSearchResult
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.layout_search_form.*

class SearchFragment : Fragment() {

    private val navigationArguments: SearchFragmentArgs by navArgs()
    private val viewModel: SearchViewModel by viewModels()

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noConnectionTextView.visibility = if (navigationArguments.hasConnection) View.GONE else View.VISIBLE
        searchForm.visibility = if (navigationArguments.hasConnection) View.VISIBLE else View.GONE

        viewModel.lyricsSearchResult.observe(this.activity as MainActivity, Observer<LyricsSearchResult> { result ->
            val test = result.lyrics
        })

        searchFormSearchButton.setOnClickListener {
            viewModel.getLyrics()
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

}