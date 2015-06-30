package headfirstandroiddevelopment.budgeteer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by bklemr on 26.06.2015.
 */
public class BaseDAO {

    private Database dbManager;
    protected SQLiteDatabase db;

    public BaseDAO(Context context) {
        this.dbManager = new Database(context);
    }

    public void openWritable() {
        db = dbManager.getWritableDatabase();
    }

    public void openReadable() {
        db = dbManager.getReadableDatabase();
    }

    public void close() {
        dbManager.close();
    }
}
