package com.shuklavijay249.unitconverterapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.shuklavijay249.unitconverterapp.data.ConverterRepository
import javax.inject.Inject

class ConverterViewModelFactory @Inject constructor(private val repository: ConverterRepository) : NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ConverterViewModel(repository) as T

}

