import java.text.SimpleDateFormat;
import java.util.Date;

class CurrentDate {
    //assigning them all private so that the instance object can't cant access them and alter data
    private Date today = new Date();

    //creating objects of class SimpleDateFormat and assigning them values of the day, month, year of current time from pc
    private SimpleDateFormat String_day = new SimpleDateFormat("dd");
    private SimpleDateFormat String_month = new SimpleDateFormat("MM");
    private SimpleDateFormat String_year = new SimpleDateFormat("yyyy");

    //maybe this is what is giving it an acceptable and readable format?
    private String currentDay = String_day.format(today);
    private String currentMonth = String_month.format(today);
    private String currentYear = String_year.format(today);

    //converting the objects value we got (Which is a STRING) into an int
    int day = Integer.parseInt(currentDay);
    int month = Integer.parseInt(currentMonth);
    int year = Integer.parseInt(currentYear);
}
public class Main {
    public static void main(String[] argvs) {
        CurrentDate today = new CurrentDate();

        System.out.println("Date: " + today.day);
        System.out.println("Month: " + today.month);
        System.out.println("Year: " + today.year);

    }
}