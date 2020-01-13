package com.example.remainder.database.entity

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.room.*
import com.example.remainder.R
import com.example.remainder.application.App
import com.example.remainder.database.entity.MemoEntity.Companion.TABLE_NAME
import com.example.remainder.databinding.MemoEntityBinding
import kotlinx.android.synthetic.main.memo_entity.view.*
import org.json.JSONObject
import java.sql.Time
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName =  TABLE_NAME, indices = arrayOf(Index(value = ["userId", "writeTime"], unique = true)))
data class MemoEntity(@PrimaryKey(autoGenerate = true) val idx: Int?,
                      @ColumnInfo(name = "userId") var userId: String,
                      @ColumnInfo(name = "writeTime") var writeTime: String,
                      @ColumnInfo(name = "question_idx") var question_idx: Int,
                      @ColumnInfo(name = "answer") var answer: String) : BaseEntity {

    companion object {
        const val TABLE_NAME = "memo"
    }

    private class MemoView @JvmOverloads
    constructor(memoEntity: MemoEntity, context: Context, parent: ViewGroup?, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attributeSet, defStyleAttr) {

        init {
            val bind: MemoEntityBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.memo_entity, parent, true)
            attributeSet.let {

            }
            bind.memoEntity = memoEntity
        }

        override fun setOnClickListener(l: OnClickListener?) {
            super.setOnClickListener(l)
        }
    }

    override fun toJsonObject(): JSONObject {
        val entityObject = JSONObject()
        entityObject.put("idx", idx)
        entityObject.put("userId", userId)
        entityObject.put("writeTime", writeTime)
        entityObject.put("question_idx", question_idx)
        entityObject.put("answer", answer)
        return entityObject
    }

    fun getMemoView(context: Context, parent: ViewGroup): View {
        return MemoView(this, context, parent)
    }
}