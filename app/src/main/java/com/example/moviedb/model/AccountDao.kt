package com.example.moviedb.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AccountDao {
    @Query("select * from account where email == :email")
    suspend fun getAccountyByEmail(email: String): AccountEntity

    @Insert
    suspend fun insertAccount(account: AccountEntity)
}