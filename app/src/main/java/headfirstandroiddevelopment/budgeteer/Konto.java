package headfirstandroiddevelopment.budgeteer;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by bklemr on 26.06.2015.
 */
public class Konto {

    private int day;
    private int month;
    private int year;
    private Double amount;
    private String category;
    private String description;

    public Konto() {
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



    public String toString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        String formamount = formatter.format(getAmount());
        String strOutput = getDay() + "." + getMonth() + "." + getYear() + ": " +formamount+ " ("+getCategory()+") " + getDescription();
        /*if(getDescription()==""){
            strOutput+=getDescription();
        }*/
        return strOutput;
    }
}