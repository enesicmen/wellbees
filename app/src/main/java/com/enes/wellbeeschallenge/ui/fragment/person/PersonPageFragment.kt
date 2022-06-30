package com.enes.wellbeeschallenge.ui.fragment.person

import android.os.Bundle
import android.view.View
import com.enes.wellbeeschallenge.base.fragment.BaseVBFragment
import com.enes.wellbeeschallenge.data.Resource
import com.enes.wellbeeschallenge.data.model.PersonModel
import com.enes.wellbeeschallenge.databinding.FragmentPersonDetailBinding
import com.enes.wellbeeschallenge.ui.ext.loadTmdbImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonPageFragment :
    BaseVBFragment<FragmentPersonDetailBinding, PersonPageViewModel>() {

    private var personId: Int = 0
    private lateinit var personName: String
    private var person: PersonModel? = null

    override fun setViewModelClass() =
        PersonPageViewModel::class.java

    override fun setViewBinding(): FragmentPersonDetailBinding =
        FragmentPersonDetailBinding.inflate(layoutInflater)

    override fun initView(savedInstanceState: Bundle?) {
        getViewModel().personDetailList.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    getViewBinding()?.progressBar?.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
                    person = it.data
                    setData()
                }
                is Resource.Error -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
                }
            }
        }
    }

    override fun readDataFromArguments() {
        super.readDataFromArguments()
        arguments?.let {
            val safeArgs = PersonPageFragmentArgs.fromBundle(it)
            personId = safeArgs.personId
            personName = safeArgs.personName
        }
    }

    override fun onResume() {
        super.onResume()
        getViewModel().getPersonDetails(personId = personId)
    }

    private fun setData() {
        getViewBinding()?.ivProfile.loadTmdbImage(person?.imagePath)
        getViewBinding()?.tvName?.text = personName
        getViewBinding()?.tvBiography?.text = person?.biography
    }
}
