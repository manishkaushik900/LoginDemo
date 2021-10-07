package com.demo.logindemo.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefrences")

class UserPrefrence(private val context: Context) {

    val USERNAME_KEY = stringPreferencesKey("userName")
    val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")

    val readUserName: Flow<String> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USERNAME_KEY] ?: ""
        }

    suspend fun writeUserName(userName: String) {
        context.dataStore.edit { prefrences ->
            prefrences[USERNAME_KEY] = userName
        }
    }

    val readIsLogin: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[IS_LOGIN_KEY]?:false
        }

    suspend fun writeIsLogin(isLogin: Boolean) {
        context.dataStore.edit { prefrences ->
            prefrences[IS_LOGIN_KEY] = isLogin
        }
    }

    @Suppress("unused")
    suspend fun clearDataStore() {
        context.dataStore.edit {
            it.clear()
        }
    }

}