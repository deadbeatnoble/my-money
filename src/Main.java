import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class CurrentDate {
    //assigning them all private so that the instance object can't cant access them and alter data
    private Date today = new Date();

    //creating objects of class SimpleDateFormat and assigning them values of the day, month, year or all together ad date of current time from pc
    private SimpleDateFormat String_day = new SimpleDateFormat("dd");
    private SimpleDateFormat String_month = new SimpleDateFormat("MM");
    private SimpleDateFormat String_year = new SimpleDateFormat("yyyy");
    private SimpleDateFormat String_date = new SimpleDateFormat("dd/MM/yyyy");

    //this is what is giving it an acceptable and readable format, without this it's just a random bunch of symbols
    String currentDay = String_day.format(today);
    String currentMonth = String_month.format(today);
    String currentYear = String_year.format(today);
    String currentDate = String_date.format(today);

}
class AllowanceDate {
    //overall, deals with things related to the monthly allowance of user
    int day;
    int month;
    int year;
    int round;


    AllowanceDate() {
        //helps us read the default set time of the allowance date and assign it to variables such as day, month, year
        //the way we access the variables by calling this constructor the access through the object created
        try {
            Scanner ifile = new Scanner(new File(".\\file\\date.txt"));
            int i = 0;
            while(ifile.hasNextLine())
            {
                // the .nextLine() helps with reading the file from LINE to LINE only
                // .hasNextLine() is useful for knowing the file has a next line ready... returns false if there is none hence, the loop terminates
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
        //WORKING PROGRESS!!!//
        //this helps us set the current date as the allowance date and save it to file.
        //currently only sets default date as allowance date... personal date setting will be added later
        CurrentDate today = new CurrentDate();

        FileOutputStream ofile = null;
        try {
            //didn't use append here because only one date can be set as default so cant append next to the old one as it is useless afterwards
            //adding multiple dates for multiple users in the future assuming the program will be used by more than 1 user
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
        //WORKING PROGRESS!!!//
        //this method helps to know the status of the user in reciving his/her allowance
        //must call this method first to get a value from round.
        AllowanceDate latest = new AllowanceDate();
        CurrentDate today = new CurrentDate();

        int currentMonth = Integer.parseInt(today.currentMonth);
        int currentDay = Integer.parseInt(today.currentDay);
        int currentYear = Integer.parseInt(today.currentYear);

        if(currentYear >= latest.year) {
            if(currentMonth >= latest.month) {
                //counts the number of times the allowance must be received. (counts per month)
                // if the month has passed without taking out the allowance next time the program is run it will add the missed monthly allowances
                round = currentMonth - latest.month;
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
class CurrentBalance {
    //this class deals with adding and substructing the deposit and withdrawals of the user from the balance.
    //the money flow history is created and updated here
    CurrentDate today = new CurrentDate();
    Scanner input = new Scanner(System.in);
    int balance;
    void createDepositeHistory() {
        //here we create the deposite history by writing the date, amount and reasons related to money to a file called deposite.txt
        double depo_amount;
        String Str_depo_amount;
        String depo_reason;
        String depo_date = today.currentDate;

        System.out.println("Enter amount: ");
        depo_amount = input.nextDouble();
        Str_depo_amount = Double.toString(depo_amount);

        System.out.println("Enter reason: ");
        input.nextLine();
        depo_reason = input.nextLine();

        try {
            //we wrote true here in append so that we can add more records to the file and not remove the old ones that exist before
            FileOutputStream ofile = new FileOutputStream(".//file//deposit.txt", true);

            ofile.write(depo_date.getBytes());
            ofile.write(" - ".getBytes());
            ofile.write(Str_depo_amount.getBytes());
            ofile.write(" - ".getBytes());
            ofile.write(depo_reason.getBytes());
            ofile.write("\n".getBytes());

            balance += depo_amount;
            System.out.println(balance);

            ofile.close();
        } catch (IOException e) {
            return;
        }
    }
    void createWithdrawalHistory() {
        //here we create the withdrawal history by writing the date, amount and reasons related to money to a file called withdraw.txt
        double with_amount;
        String Str_with_amount;
        String with_reason;
        String with_date = today.currentDate;

        System.out.println("Enter amount: ");
        with_amount = input.nextDouble();
        Str_with_amount = Double.toString(with_amount);

        System.out.println("Enter reason: ");
        input.nextLine();
        with_reason = input.nextLine();

        try {
            //we wrote true here in append so that we can add more records to the file and not remove the old ones that exist before
            FileOutputStream ofile = new FileOutputStream(".//file//withdraw.txt", true);

            ofile.write(with_date.getBytes());
            ofile.write(" - ".getBytes());
            ofile.write(Str_with_amount.getBytes());
            ofile.write(" - ".getBytes());
            ofile.write(with_reason.getBytes());
            ofile.write("\n".getBytes());

            balance -= with_amount;
            System.out.println(balance);

            ofile.close();
        } catch (IOException e) {
            return;
        }
    }
}
public class Main {
    public static void main(String[] argvs) {
        /*CurrentBalance user = new CurrentBalance();
        Scanner input = new Scanner(System.in);

        int choice;
        do {
            System.out.println("1. D");
            System.out.println("2. W");
            System.out.println("3. E");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("1 " + user.balance);
                    user.createDepositeHistory();
                    System.out.println("1 " + user.balance);
                    break;
                case 2:
                    System.out.println("1 " + user.balance);
                    user.createWithdrawalHistory();
                    System.out.println("1 " + user.balance);
                    break;
            }

        } while (choice != 3);*/

    }
}