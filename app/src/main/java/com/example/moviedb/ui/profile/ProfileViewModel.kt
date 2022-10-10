package com.example.moviedb.ui.profile

import android.app.Application
import androidx.lifecycle.*
import com.example.moviedb.model.AccountDao
import com.example.moviedb.model.AccountEntity
import kotlinx.coroutines.launch

class ProfileViewModel (
    val database : AccountDao, application: Application ) : AndroidViewModel(application) {
    fun getAccountByEmail(email: String) : LiveData<AccountEntity> {
        val dummy = MutableLiveData <AccountEntity>()
        viewModelScope.launch {
            dummy.value = getData(email)
        }
        return dummy
    }

    private suspend fun getData(email: String) : AccountEntity? {
        return database.getAccountyByEmail(email)
    }

    fun updateAccount(account: AccountEntity) {
        viewModelScope.launch {
            updateAccount(account)
        }
    }
}