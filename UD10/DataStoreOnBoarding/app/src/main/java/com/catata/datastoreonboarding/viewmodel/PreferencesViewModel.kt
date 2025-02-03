package com.catata.datastoreonboarding.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.catata.datastoreonboarding.model.UserData
import com.catata.datastoreonboarding.preferences.AppPreferences
import kotlinx.coroutines.launch

//Como se necessita el contexto el viewModel se extiende de AndroidViewModel para que el propio
//Sistema operativa le pase el objeto Application del que se recuperará el contexto
class PreferencesViewModel(application: Application): AndroidViewModel(application) {
    //Declaración del objeto para guardar/almacenar preferencias
    private val preferences = AppPreferences(application.applicationContext)

    private val _user = MutableLiveData<UserData>(UserData("",""))
    val user: LiveData<UserData> = _user


    //Método que llama a AppPreferences para almacenar la preferencia en el archivo de preferencias
    fun saveUser(user: UserData){
        viewModelScope.launch {
            preferences.saveUser(user)
        }
    }
    
    //Método que llama a Appreferences para recuperar la preferencia del archivo de preferencias
    fun loadFullName(){
        viewModelScope.launch { 
            //Como loadName devuelve un Flow para recuparar sus datos se usa el método collect()
            preferences.loadUser().collect(){
                _user.postValue(it)
            }
        }
    }
}