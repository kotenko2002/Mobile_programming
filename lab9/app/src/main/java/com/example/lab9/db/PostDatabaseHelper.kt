package com.example.lab9.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Database Helper for DbPost
class PostDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "postDatabase.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_POSTS = "posts"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_BODY = "body"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_POSTS (
                $COLUMN_ID TEXT PRIMARY KEY,
                $COLUMN_TITLE TEXT,
                $COLUMN_BODY TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_POSTS")
        onCreate(db)
    }
}