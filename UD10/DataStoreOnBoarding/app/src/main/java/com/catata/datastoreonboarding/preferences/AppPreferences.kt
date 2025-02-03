package com.catata.datastoreonboarding.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.catata.datastoreonboarding.model.UserData
import kotlinx.coroutines.flow.map

class AppPreferences(val context: Context) {
    //Variables estáticas para representar las calves de los valores a guardar
    companion object{
        val NAME = stringPreferencesKey("NAME")
        val EMAIL = stringPreferencesKey("EMAIL")
    }

    //Variable DataStore a la que se le pasa el nombre del archivo de preferencias "preferences"
    //Al declararla con el delegado "by" la variable será un singleton: solo habrá una instancia
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

    //Función que recibe un String y lo almacena en el archivo de preferencias declarado en el DataStore
    //El guardado se debe realizar en segundo plano por lo que necesitará una corrutina para ello
    //se añade la palabra suspend para indicar que cuando se realice la llamada se use una corrutina
    suspend fun saveUser(user: UserData){
        context.dataStore.edit {preferences ->
            preferences[NAME] = user.name ?: ""
            preferences[EMAIL] = user.email ?: ""
        }
    }

    //Función que recupera un String del archivo de preferencias declarado en el DataStore.
    //Esta función devuelve un Flow
    fun loadUser()= context.dataStore.data.map { preferences ->
        UserData(
            name = preferences[NAME] ?: "",
            email = preferences[EMAIL] ?: ""
        )

    }

}