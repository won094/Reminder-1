package com.example.remainder.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.remainder.database.entity.QuestionEntity
import io.reactivex.Single

@Dao
interface QuestionDao : BaseDao<QuestionEntity> {

    @Query("SELECT * FROM ${QuestionEntity.TABLE_NAME} WHERE ${QuestionEntity.TABLE_NAME}.idx = :idx")
    fun getQuestion(idx: Int): Single<QuestionEntity>

    @Query("SELECT ${QuestionEntity.TABLE_NAME}.idx FROM ${QuestionEntity.TABLE_NAME} WHERE ${QuestionEntity.TABLE_NAME}.content = :content")
    fun getQuestionIndexByContent(content: String): Single<Int>

    @Query("SELECT * FROM ${QuestionEntity.TABLE_NAME}")
    override fun getAll(): Single<List<QuestionEntity>>

}