package headfirstandroiddevelopment.budgeteer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by bklemr on 26.06.2015.
 */
public class Konto {
    private int id;
    private int day;
    private int month;
    private int year;
    private Double amount;
    private String category;
    private String description;
    private Integer repeatMonth;

   /* private void LoadPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String data = sharedPreferences.getString("name", "08:00");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApllicationContext());
          }
*/

    public Konto() {
    }

    public int getId() {
        return id;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {return description;}

    public void setDay(int day) {
        this.day = day;
    }

    public Integer getRepeatMonth() {
        return repeatMonth;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {this.category = category;}

    public void setDescription(String description) {this.description = description;}

    public void setRepeatMonth(Integer repeatMonth) {
        this.repeatMonth = repeatMonth;
    }


    public String toString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        String formamount = formatter.format(getAmount());
        String strOutput = getDay() + "." + getMonth() + "." + getYear() + ": " +formamount+ " ("+getCategory()+") " + getDescription();
        return strOutput;
    }

    public void setId(int id) {
        this.id = id;
    }
}