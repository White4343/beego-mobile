package com.white.beego.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.white.beego.api.BeehiveAPI
import com.white.beego.models.BeehiveResponse
import com.white.beego.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class BeehiveRepository @Inject constructor(private val beehiveAPI: BeehiveAPI) {

    private val _beehiveLiveData = MutableLiveData<NetworkResult<List<BeehiveResponse>>>()
    val beehiveLiveData: LiveData<NetworkResult<List<BeehiveResponse>>>
        get() = _beehiveLiveData

    private val _statusLiveData = MutableLiveData<NetworkResult<String>>()
    val statusLiveData: LiveData<NetworkResult<String>>
        get() = _statusLiveData


    suspend fun getBeehives(apiaryId: String) {
        _statusLiveData.postValue(NetworkResult.Loading())
        val response = beehiveAPI.getBeehives(apiaryId)
        if (response.isSuccessful && response.body() != null) {
            _beehiveLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _beehiveLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _beehiveLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }


}