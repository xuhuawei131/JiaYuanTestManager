package jiayuan.huawei.com.jiayuantestmanager.db;

import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class ExcelDBHelper extends SQLiteOpenHelper {
    
    public ExcelDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
