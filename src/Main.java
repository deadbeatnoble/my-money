import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class CurrentDate {
    //assigning them all private so that the instance object can't cant access them and alter data
    private Date today = new Date();

    //creating objects of class SimpleDateFormat and assigning them values of the day, month, year of current time from pc
    private SimpleDateFormat String_day = new SimpleDateFormat("dd");
    private SimpleDateFormat String_month = new SimpleDateFormat("MM");
    private SimpleDateFormat String_year = new SimpleDateFormat("yyyy");

    //maybe this is what is giving it an acceptable and readable format?
    String currentDay = String_day.format(today);
    String currentMonth = String_month.format(today);
    String currentYear = String_year.format(today);

}
class AllowanceDate {

    int day;
    int month;
    int year;


    void readLatestAllowanceDate() {
        try {
            Scanner ifile = new Scanner(new File(".\\file\\date.txt"));
            int i = 0;
            while(ifile.hasNextLine())
            {
                String line = ifile.nextLine();
                if(i == 0) {
                    day = Integer.parseInt(line);
                } else if (i == 1) {
                    month = Integer.parseInt(line);
                } else if (i == 2) {
                    year = Integer.parseInt(line);
                }
                i++;
                //do stuff
            }
        } catch (Exception e) {
            return;
        }
    }
    void createFirstAllowanceDate() throws IOException {
        CurrentDate today = new CurrentDate();

        FileOutputStream ofile = null;
        try {
            ofile = new FileOutputStream(".\\file\\date.txt");

            byte[] buffer = today.currentDay.getBytes();
            ofile.write(buffer, 0, buffer.length);

            buffer = "\n".getBytes();
            ofile.write(buffer, 0, buffer.length);

            buffer = today.currentMonth.getBytes();
            ofile.write(buffer, 0, buffer.length);

            buffer = "\n".getBytes();
            ofile.write(buffer, 0, buffer.length);

            buffer = today.currentYear.getBytes();
            ofile.write(buffer, 0, buffer.length);

            ofile.close();
        } catch (Exception e) {
            return;
        } finally{
            if(ofile != null)
                ofile.close();
        }
    }
    void allowanceDateStatus() {
        AllowanceDate latest = new AllowanceDate();
        latest.readLatestAllowanceDate();
        CurrentDate today = new CurrentDate();

        int currentMonth = Integer.parseInt(today.currentMonth);
        int currentDay = Integer.parseInt(today.currentDay);
        int currentYear = Integer.parseInt(today.currentYear);

        if(currentYear >= latest.year) {
            if(currentMonth >= latest.month) {
                if((currentDay >= 7) && (currentDay <= 11)) {
                    System.out.println("Message: Allowance date has ARRIVED!");
                } else if (currentDay > 11) {
                    System.out.println("WARNING: Allowance date has PASSED!");
                } else if (currentDay < 7) {
                    System.out.println("Message: Allowance date is NEAR!");
                }
            } else {
                System.out.println("Message: Allowance not available yet!");
            }
        } else {
            System.out.println("Message: Allowance not available yet!");
        }

    }
}
public class Main {
    public static void main(String[] argvs) {

    }
}