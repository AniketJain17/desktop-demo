import android.content.Context.
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class MyDB (context: Context): SQLiteOpenHelper (context, name: "testDB", factory: null, version: 1) {
    override fun onCreate (db: SQLite Database?) {
    db?.execSQL ( sql: "CREATE TABLE USERS (Name TEXT, Password TEXT,File BLOB)")
}
    override fun onUpgrade (p0: SQLite Database?, pl: Int, p2: Int) {
}