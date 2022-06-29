package mohit.dev.a27jun2022

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.annotation.Nullable

class Databasehelper(var context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "mydb"
        private val DATABASE_VERSION = 1


        private const val TABLE_NAME = "student"
        private const val KEY_ID = "id"
        private const val KEY_USENAME = "username"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
        private const val KEY_MOBILE = "mobile"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE =
            (
                    "CREATE TABLE " + TABLE_NAME + " "
                            + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                            + KEY_USENAME + " TEXT, " + ""
                            + KEY_EMAIL + " TEXT,"
                            + KEY_PASSWORD + " TEXT,"
                            + KEY_MOBILE + " TEXT)"
                    )

        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


    fun insertData(userModel: UserModel): Long {
        var db = this.writableDatabase
        var cv = ContentValues()

        cv.put(KEY_USENAME, userModel.username)
        cv.put(KEY_EMAIL, userModel.userEmail)
        cv.put(KEY_MOBILE, userModel.userMobile)
        cv.put(KEY_PASSWORD, userModel.userPassword)


        var inserData = db.insert(TABLE_NAME, null, cv)
        return inserData

    }

    fun getAllData():MutableList<UserModel>{

        var userlist:MutableList<UserModel> =ArrayList()
        var sel_que="select *from $TABLE_NAME"


        var cursor:Cursor?
        var db=this.readableDatabase

        try {
            cursor=db.rawQuery(sel_que,null)
        }
        catch (Exception:SQLException){
            db.execSQL(sel_que)
            return ArrayList()

        }

        return userlist
    }
}