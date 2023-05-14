package com.torregrosa.faunaval

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.torregrosa.faunaval.model.Animal
import com.torregrosa.faunaval.repository.AnimalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
    private val animalRepositoryImp: AnimalRepository,

) : ViewModel() {

    suspend fun getAnimalList() :List<Animal> {
        var animalList = viewModelScope.async(Dispatchers.IO) {
            return@async animalRepositoryImp.getAnimalList()
        }


        Log.d("VIEW_MODEL", "Animals Received: ${animalList.await().size}")
        return animalList.await()
    }
}