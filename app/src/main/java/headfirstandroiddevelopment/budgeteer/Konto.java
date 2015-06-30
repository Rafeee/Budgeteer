package headfirstandroiddevelopment.budgeteer;
/**
 * Created by bklemr on 26.06.2015.
 */
public class Konto {

    private int day;
    private int month;
    private int year;
    private Double amount;
    private String category;

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

    public void setCategory(String category) {
        this.category = category;
    }

    public String toString() {
        return getDay() + "." + getMonth() + "." + getYear() + ": " + getCategory() + " -> "+
                getAmount();
    }
}