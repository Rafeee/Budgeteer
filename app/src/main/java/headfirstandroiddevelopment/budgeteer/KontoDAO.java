package headfirstandroiddevelopment.budgeteer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bklemr on 26.06.2015.
 */
public class KontoDAO extends BaseDAO {

    public KontoDAO(Context context) {
        super(context);
    }

    public long insertKonto(Konto konto) {
        ContentValues values = new ContentValues();
        values.put("day", konto.getDay());
        values.put("month", konto.getMonth());
        values.put("year", konto.getYear());
        values.put("Amount", konto.getAmount());
        values.put("Category", konto.getCategory());

        return db.insert("konto", null, values);
    }

    public List<Konto> getAll() {
        /*     db.query = SELECT: Tablename, Stringarray: (PK, where, where values, group by, filter by row groups, sort) */
        Cursor cursor = db.query("konto", new String[]{"day", "month", "year", "amount", "category"}, null, null, null, null, null);
        List<Konto> kontiAll = new ArrayList<>();

        while (cursor.moveToNext()) {
            Konto konto = new Konto();
            kontiAll.add(konto);
        }
        return kontiAll;
    }

    public List<Konto> getKontoByDate(int month, int year){
        Cursor cursor = db.query("konto", new String[]{"day", "month", "year", "amount", "category"},"month = ? AND year = ?", new String[]{String.valueOf(month), String.valueOf(year)} , null, null, null);

        List<Konto> kontoByDate = new ArrayList<>();


        while (cursor.moveToNext()){
            Konto konto = new Konto();
            konto.setDay(cursor.getInt(0));
            konto.setMonth(cursor.getInt(1));
            konto.setYear(cursor.getInt(2));
            konto.setAmount(cursor.getDouble(3));
            konto.setCategory(cursor.getString(4));
            kontoByDate.add(konto);
        }
        return kontoByDate;
    }

}
