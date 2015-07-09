package headfirstandroiddevelopment.budgeteer;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

import static android.app.PendingIntent.getActivity;

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
    private Locale swiss = new Locale("de","CH");
    private  Context context;


    public Konto(Context context) {
        this.context=context;
		
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

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String currency = preferences.getString("Currency", "0");
        NumberFormat formatter = null;

        if (currency.equals("0")) {
            formatter = NumberFormat.getCurrencyInstance(Locale.US);
        } else if (currency.equals("1")) {
            formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        } else if (currency.equals("2")) {
            formatter = NumberFormat.getCurrencyInstance(Locale.UK);
        } else if (currency.equals("3")) {
            formatter = NumberFormat.getCurrencyInstance(swiss);
        }

        String formamount = formatter.format(getAmount());
        String strOutput = getCategory()+  " \n " +formamount +"\t\t\t\t\t\t"+getDay() + "." + getMonth() + "." + getYear()+"\t\t\t\t\t\t" +"Description: "+ getDescription();
        return strOutput;
    }

    public void setId(int id) {
        this.id = id;
    }


}