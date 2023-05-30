package com.torregrosa.faunaval

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.torregrosa.faunaval.model.Animal
import com.torregrosa.faunaval.repository.AnimalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
    private val animalRepositoryImp: AnimalRepository,

    ) : ViewModel() {

    suspend fun getAnimalList(id: Int): List<Animal> {
        var animalList = viewModelScope.async(Dispatchers.IO) {
            return@async animalRepositoryImp.getAnimalList(id)
        }
        Log.d("VIEW_MODEL", "Animals Received: ${animalList.await().size}")
        return animalList.await()
    }

    suspend fun getAnimalListFiltered(id: Int, filter: String?): List<Animal> {
        var animalList = viewModelScope.async(Dispatchers.IO) {
            return@async animalRepositoryImp.getAnimalListFiltered(id, filter)
        }
        Log.d("VIEW_MODEL", "Animals Received: ${animalList.await().size}")
        return animalList.await()
    }

    suspend fun getAnimal(id: Int): Animal {
        var animal = viewModelScope.async(Dispatchers.IO) {
            return@async animalRepositoryImp.getAnimalById(id)
        }
        Log.d("VIEW_MODEL", "Animal: ${animal.await().nombreComun}")
        return animal.await()
    }
}