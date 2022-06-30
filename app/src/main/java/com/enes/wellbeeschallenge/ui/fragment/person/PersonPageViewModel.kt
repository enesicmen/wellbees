package com.enes.wellbeeschallenge.ui.fragment.person

import com.enes.wellbeeschallenge.base.viewModel.BaseViewModel
import com.enes.wellbeeschallenge.data.NetworkCallback
import com.enes.wellbeeschallenge.data.Resource
import com.enes.wellbeeschallenge.data.model.PersonModel
import com.enes.wellbeeschallenge.data.repository.PersonRepository
import com.enes.wellbeeschallenge.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonPageViewModel @Inject constructor(
    private val personRepository: PersonRepository
) : BaseViewModel() {

    var personDetailList: SingleLiveEvent<Resource<PersonModel>> = SingleLiveEvent()

    fun getPersonDetails(personId: Int) {
        personDetailList.value = Resource.Loading()
        personRepository.getPersonDetail(
            personId,
            object : NetworkCallback<PersonModel> {
                override fun onSuccess(data: PersonModel) {
                    personDetailList.value = Resource.Success(data)
                }

                override fun onError(message: String) {
                    personDetailList.value = Resource.Error(message)
                }
            }
        )
    }
}
