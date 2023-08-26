package test.android.foo1

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log

internal class Foo1ContentProvider : ContentProvider() {
    companion object {
        private const val NOTES = 42
        private const val NOTE_ID = 43

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        init {
            uriMatcher.addURI(Foo1Contract.CONTENT_AUTHORITY, "notes", NOTES)
            uriMatcher.addURI(Foo1Contract.CONTENT_AUTHORITY, "notes/#", NOTE_ID)
        }
    }

    private val TAG = "[Foo1CP|${hashCode()}]"

    override fun onCreate(): Boolean {
        return true // todo
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        when (uriMatcher.match(uri)) {
            NOTES -> {
                Log.d(TAG, "query notes")
            }
            NOTE_ID -> {
                val id = ContentUris.parseId(uri)
                Log.d(TAG, "query note id: $id")
            }
            else -> error("Cannot query unknown URI $uri")
        }
        return null // todo
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented: getType")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented: insert")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented: delete")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented: update")
    }
}
