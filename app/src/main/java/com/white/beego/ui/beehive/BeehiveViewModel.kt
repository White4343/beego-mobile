package com.white.beego.ui.beehive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.white.beego.repository.BeehiveRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeehiveViewModel  @Inject constructor(private val beehiveRepository: BeehiveRepository): ViewModel(){

    val beehiveLiveData get() = beehiveRepository.beehiveLiveData

    val statusLiveData get() = beehiveRepository.statusLiveData

    fun getBeehives(apiaryId: String){
        viewModelScope.launch {
            beehiveRepository.getBeehives(apiaryId)
        }
    }


}