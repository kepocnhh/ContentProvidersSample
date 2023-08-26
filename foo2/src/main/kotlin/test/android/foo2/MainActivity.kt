package test.android.foo2

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

internal class MainActivity : AppCompatActivity() {
    private val TAG = "[Foo2|${hashCode()}]"

    private fun queryNotes() {
        Log.d(TAG, "query notes...")
        val uri = Uri.parse("content://test.android.foo1.ContentProvider/notes")
        contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                Log.d(TAG, "cursor move to first...")
                do {
                    // todo
                } while (cursor.moveToNext())
            }
        }
    }

    private fun queryNote(text: String) {
        val id = text.toIntOrNull()
        if (id == null) {
            showToast("Id error!")
            return
        }
        Log.d(TAG, "query note \"$id\"...")
        val uri = Uri.parse("content://test.android.foo1.ContentProvider/notes/$id")
        contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                Log.d(TAG, "cursor move to first...")
                do {
                    // todo
                } while (cursor.moveToNext())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context: Context = this
        val root = FrameLayout(context).also {
            it.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
            it.background = ColorDrawable(Color.GREEN)
        }
        LinearLayout(context).also { rows ->
            rows.layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_VERTICAL,
            )
            rows.orientation = LinearLayout.VERTICAL
            Button(context).also {
                it.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                )
                it.text = "query"
                it.setOnClickListener {
                    queryNotes()
                }
                rows.addView(it)
            }
            val idET = EditText(context).also {
                it.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                )
                it.hint = "id"
                rows.addView(it)
            }
            Button(context).also {
                it.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                )
                it.text = "query note"
                it.setOnClickListener {
                    queryNote(idET.text.toString())
                }
                rows.addView(it)
            }
            root.addView(rows)
        }
        setContentView(root)
    }
}
