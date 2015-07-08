package headfirstandroiddevelopment.budgeteer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
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
        values.put("amount", konto.getAmount());
        values.put("category", konto.getCategory());
        values.put("description", konto.getDescription());
        values.put("repeat", konto.getRepeatMonth());

        return db.insert("konto", null, values);
    }

    public void alterKontoNoRepeat(int id){
        ContentValues values = new ContentValues();
        values.put("repeat", 0);
        String actualId = Integer.toString(id);
        db.update("konto", values, "idkonto=?", new String[]{actualId});
    }

    public HashMap<Integer, Konto> getAllHash() {
        /*     db.query = SELECT: Tablename, Stringarray: (PK, where, where values, group by, filter by row groups, sort) */
        Cursor cursor = db.query("konto", new String[]{"idkonto","day", "month", "year", "amount", "category","description","repeat"}, null, null, null, null, null, null);
        HashMap<Integer, Konto> kontiAll = new HashMap<>();

        while (cursor.moveToNext()) {
            Konto konto = new Konto();
            konto.setId(cursor.getInt(0));
            konto.setDay(cursor.getInt(1));
            konto.setMonth(cursor.getInt(2));
            konto.setYear(cursor.getInt(3));
            konto.setAmount(cursor.getDouble(4));
            konto.setCategory(cursor.getString(5));
            konto.setDescription(cursor.getString(6));
            konto.setRepeatMonth(cursor.getInt(7));
            kontiAll.put(konto.getId(), konto);
        }
        return kontiAll;
    }

    public List<Konto> getAll() {
        /*     db.query = SELECT: Tablename, Stringarray: (PK, where, where values, group by, filter by row groups, sort) */
        Cursor cursor = db.query("konto", new String[]{"idkonto","day", "month", "year", "amount", "category","description","repeat"}, null, null, null, null, null, null);
        List<Konto> kontiAll = new ArrayList<>();

        while (cursor.moveToNext()) {
            Konto konto = new Konto();
            konto.setId(cursor.getInt(0));
            konto.setDay(cursor.getInt(1));
            konto.setMonth(cursor.getInt(2));
            konto.setYear(cursor.getInt(3));
            konto.setAmount(cursor.getDouble(4));
            konto.setCategory(cursor.getString(5));
            konto.setDescription(cursor.getString(6));
            konto.setRepeatMonth(cursor.getInt(7));
            kontiAll.add(konto);
        }
        return kontiAll;
    }

    public List<Konto> getKontoByDate(int month, int year){
        Cursor cursor = db.query("konto", new String[]{"day", "month", "year", "amount", "category","description","repeat"},"month = ? AND year = ?", new String[]{String.valueOf(month), String.valueOf(year)}, null, null, null, null);

        List<Konto> kontoByDate = new ArrayList<>();

        while (cursor.moveToNext()){
            Konto konto = new Konto();
            konto.setDay(cursor.getInt(0));
            konto.setMonth(cursor.getInt(1));
            konto.setYear(cursor.getInt(2));
            konto.setAmount(cursor.getDouble(3));
            konto.setCategory(cursor.getString(4));
            konto.setDescription(cursor.getString(5));
            konto.setRepeatMonth(cursor.getInt(6));
            kontoByDate.add(konto);
        }
        return kontoByDate;
    }

    public List<Konto> getKontoByCategory(String category){
        Cursor cursor = db.query("konto", new String[]{"day", "month", "year", "amount", "category", "description", "repeat"}, "category = ?", new String[]{category}, null, null, null);

        List<Konto> kontoByCategory = new ArrayList<>();

        while (cursor.moveToNext()){
            Konto konto = new Konto();
            konto.setDay(cursor.getInt(0));
            konto.setMonth(cursor.getInt(1));
            konto.setYear(cursor.getInt(2));
            konto.setAmount(cursor.getDouble(3));
            konto.setCategory(cursor.getString(4));
            konto.setDescription(cursor.getString(5));
            konto.setRepeatMonth(cursor.getInt(6));
            kontoByCategory.add(konto);
        }
        return kontoByCategory;
    }

    public List<Konto> getKontoByDateAndCategory(int month, int year, String category){
        Cursor cursor = db.query("konto", new String[]{"day", "month", "year", "amount", "category","description","repeat"},"month = ? AND year = ? AND category = ?", new String[]{String.valueOf(month), String.valueOf(year), category}, null, null, null, null);

        List<Konto> kontoByDateAndCategory = new ArrayList<>();

        while (cursor.moveToNext()){
            Konto konto = new Konto();
            konto.setDay(cursor.getInt(0));
            konto.setMonth(cursor.getInt(1));
            konto.setYear(cursor.getInt(2));
            konto.setAmount(cursor.getDouble(3));
            konto.setCategory(cursor.getString(4));
            konto.setDescription(cursor.getString(5));
            konto.setRepeatMonth(cursor.getInt(6));
            kontoByDateAndCategory.add(konto);
        }
        return kontoByDateAndCategory;
    }

    public void deleteKonto(int id){
        String actualId = Integer.toString(id);
        db.delete("konto", "idkonto=?", new String[]{actualId});
    }

}
