import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
class Account {
    Scanner input = new Scanner(System.in);
    String user_username;
    int user_password;
    Account() {
        File file = new File(".\\file\\account.txt");
        if(file.exists()) {
            try {
                Scanner ifile = new Scanner(new File(".\\file\\account.txt"));
                int lines = 0;
                while(ifile.hasNextLine()) {
                    String line = ifile.nextLine();
                    if(lines == 0) {
                        user_username = line;
                    } else if (lines == 1) {
                        String Str_user_username = line;
                        user_password = Integer.parseInt(Str_user_username);
                    }
                    lines++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            checkAccount();
        } else {
            createAccount();
        }
    }
    void createAccount() {
        String username;
        int password;
        int confirm;

        System.out.println("\tSign Up");
        System.out.print("Enter Username: ");
        username = input.nextLine();
        System.out.print("Enter Password: ");
        password = input.nextInt();
        System.out.print("Confirm Password: ");
        confirm = input.nextInt();

        if(password != confirm) {
            createAccount();
        } else {
            try {
                FileOutputStream ofile = new FileOutputStream(".\\file\\account.txt");
                ofile.write(username.getBytes());

                ofile.write("\n".getBytes());

                String Str_password = Integer.toString(password);
                ofile.write(Str_password.getBytes());

                ofile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    void checkAccount() {
        String username;
        int password;

        System.out.println("\tSign in");
        System.out.print("Username: ");
        username = input.nextLine();
        System.out.print("Password: ");
        password = input.nextInt();

        if(!((username.equals(user_username)) && (password == user_password))) {
            System.out.println("Message: Invalid username or password!");
            checkAccount();
        }
    }
}
class CurrentDate {
    //this class is used to get the current date from the system and assign it to variables to be used in other classes to access date
    //creating objects of class SimpleDateFormat and assigning them values of the day, month, year or all together as date from pc
    private final Date today = new Date();
    private final SimpleDateFormat String_day = new SimpleDateFormat("dd");
    private final SimpleDateFormat String_month = new SimpleDateFormat("MM");
    private final SimpleDateFormat String_year = new SimpleDateFormat("yyyy");
    private final SimpleDateFormat String_date = new SimpleDateFormat("dd/MM/yyyy");

    //this is what is giving it an acceptable and readable format, without this it's just a random bunch of symbols that cant be read properly in the console
    String currentDay = String_day.format(today);
    String currentMonth = String_month.format(today);
    String currentYear = String_year.format(today);
    String currentDate = String_date.format(today);

}
class AllowanceDate {
    //Overall, This class deals with things related to the monthly allowance of user
    int day;
    int month;
    int year;
    int round;
    Scanner input = new Scanner(System.in);
    AllowanceDate() {
        //helps us read the default set time of the allowance date and assign it to variables such as day, month, year when an object for this class is instantiated
        File file = new File(".\\file\\date.txt");
        if(file.exists()) {
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
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                allowanceDateStatus();
            }
        } else {
            //if the file:date doesn't exist then that means allowance date hasnt been set or file has been deleted. Hence, we create a new one!
            createFirstAllowanceDate();
        }
    }
    private void setAllowanceAmount() {
        //This method is used to set the allowance amount to be received monthly
        double allowanceAmount;
        System.out.print("Please enter the amount you receive as an allowance: ");
        allowanceAmount = input.nextDouble();
        String Str_allowanceAmount = Double.toString(allowanceAmount);
        try {
            FileOutputStream ofile = new FileOutputStream(".\\file\\allowance.txt");

            byte[] buffer = Str_allowanceAmount.getBytes();
            ofile.write(buffer, 0, buffer.length);

            ofile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void createFirstAllowanceDate() {
        //this helps us set the current date as the allowance date and save it to file or accepts custom date from user.
        try {
            CurrentDate today = new CurrentDate();

            System.out.println("\t\tSet Allowance Date");
            System.out.println("\t1. Set current date");
            System.out.println("\t2. create a custom date");
            int choice = input.nextInt();

            if(choice == 1) {
                try {
                    //didn't use append here because only one date can be set as default so cant append next to the old one as it is useless afterwards
                    //adding multiple dates for multiple users in the future assuming the program will be used by more than 1 user
                    FileOutputStream ofile = new FileOutputStream(".\\file\\date.txt");

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
                    e.printStackTrace();
                } finally{
                    setAllowanceAmount();
                }
            } else if (choice == 2) {
                int newDay;
                int newMonth;
                int newYear;

                System.out.print("Enter day: ");
                newDay = input.nextInt();

                System.out.print("Enter month: ");
                newMonth = input.nextInt();

                System.out.print("Enter year: ");
                newYear = input.nextInt();

                FileOutputStream ofile = new FileOutputStream(".\\file\\date.txt");
                try {
                    String Str_newDay = Integer.toString(newDay);
                    String Str_newMonth = Integer.toString(newMonth);
                    String Str_newYear = Integer.toString(newYear);

                    byte[] buffer = Str_newDay.getBytes();
                    ofile.write(buffer, 0, buffer.length);

                    buffer = "\n".getBytes();
                    ofile.write(buffer, 0, buffer.length);

                    buffer = Str_newMonth.getBytes();
                    ofile.write(buffer, 0, buffer.length);

                    buffer = "\n".getBytes();
                    ofile.write(buffer, 0, buffer.length);

                    buffer = Str_newYear.getBytes();
                    ofile.write(buffer, 0, buffer.length);

                    ofile.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    setAllowanceAmount();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void updateAllowanceDate() {
        //update balance after either deposit or withdraw
        try {
            FileOutputStream ofile = new FileOutputStream(".\\file\\date.txt");
            CurrentDate latestAllowanceTaken = new CurrentDate();

            byte[] buffer = latestAllowanceTaken.currentDay.getBytes();
            ofile.write(buffer, 0, buffer.length);

            buffer = "\n".getBytes();
            ofile.write(buffer, 0, buffer.length);

            buffer = latestAllowanceTaken.currentMonth.getBytes();
            ofile.write(buffer, 0, buffer.length);

            buffer = "\n".getBytes();
            ofile.write(buffer, 0, buffer.length);

            buffer = latestAllowanceTaken.currentYear.getBytes();
            ofile.write(buffer, 0, buffer.length);

            ofile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void allowanceDateStatus() {
        //this method helps to know the status of the user in receiving his/her allowance
        //if received then says "already received" if not then asks user if they want to receive it
        CurrentDate today = new CurrentDate();
        CurrentBalance allowance = new CurrentBalance();

        int currentMonth = Integer.parseInt(today.currentMonth);
        //int currentDay = Integer.parseInt(today.currentDay); no use as of now, hence commented out!
        int currentYear = Integer.parseInt(today.currentYear);

        if (currentYear == year) {
            if(currentMonth == month) {
                System.out.println("Message: Allowance already taken this month on " + day + "/" + month + "/" + year + " !");
            } else if(currentMonth > month) {
                //counts the number of times the allowance must be received. (counts per month)
                // if the month has passed without taking out the allowance next time the program is run it will add the missed monthly allowances
                round = currentMonth - month;
                System.out.println("Message: Allowance date has ARRIVED!");
                //depositing allowance through this method
                if(round >= 2) {
                    System.out.println("Total rounds of allowance available: " + round);
                }
                System.out.println("receive your allowances!");
                for (int i = 0; i < round; i++) {
                    System.out.println("deposit for month " + (currentMonth - (i + month) - 1) + "(y/n)?");
                    String choice = input.nextLine();

                    if(choice.equals("y")) {
                        try {
                            Scanner ifile = new Scanner(new File(".//file//allowance.txt"));
                            String StrAllowance = ifile.nextLine();
                            allowance.appendDepositHistory(today.currentDate, StrAllowance, "Monthly allowance!");
                            updateAllowanceDate();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else if(currentMonth < month) {
                System.out.println("Message: Either Invalid latest allowance date or wrong current date readings!");
            }
        } else if (currentYear > year) {
            if(currentMonth < month) {
                int last = 12 - month;
                round = last + currentMonth;

                for (int i = 0; i < round; i++) {
                    System.out.println("deposit for month " + i + "(y/n)?");
                    String choice = input.nextLine();
                    input.nextLine();
                    if(choice.equals("y")) {
                        try {
                            Scanner ifile = new Scanner(new File(".//file//allowance.txt"));
                            String StrAllowance = ifile.nextLine();
                            allowance.appendDepositHistory(today.currentDate, StrAllowance, "Monthly allowance!");
                            updateAllowanceDate();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            System.out.println("Message: Either Invalid latest allowance date or wrong current date readings!");
        }

    }
}
class CurrentBalance {
    //this class deals with adding and subtracting the deposit and withdrawals of the user from the balance.
    //the money flow history is created and updated here
    CurrentDate today = new CurrentDate();
    Scanner input = new Scanner(System.in);
    double balance;
    double amount;
    CurrentBalance() {
        //if file doesn't exist it will create a file and either asks for default starter balance or just gives it 0
        //if file does exist them it read from file and assign it to global variable balance
        try {
            File file= new File(".//file//balance.txt");
            if(file.exists()) {
                Scanner ifile = new Scanner(new File(".//file//balance.txt"));
                String StrAmount = ifile.nextLine();
                balance = Double.parseDouble(StrAmount);
            } else {
                FileOutputStream ofile = new FileOutputStream(".//file//balance.txt");
                System.out.print("Would you like to add a starter amount to your new account(y/n)? ");
                String choice = input.nextLine();
                if(choice.equals("y") || choice.equals("Y")) {
                    System.out.print("Enter amount: ");
                    amount = input.nextDouble();
                } else if (choice.equals("n") || choice.equals("N")) {
                    amount = 0;
                }
                balance = amount;
                String StrBalance = Double.toString(balance);
                ofile.write(StrBalance.getBytes());

                ofile.close();
                System.out.println("Account Created!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateBalance(double newBalance) {
        try {
            FileOutputStream ofile = new FileOutputStream(".//file//balance.txt");
            String StrNewBalance = Double.toString(newBalance);
            ofile.write(StrNewBalance.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void appendDepositHistory(String depo_date, String depo_amount, String depo_reason) {
        try {
            //we wrote true here in append so that we can add more records to the file and not remove the old ones that exist before
            FileOutputStream ofile = new FileOutputStream(".//file//deposit.txt", true);

            ofile.write(depo_date.getBytes());
            ofile.write(" - ".getBytes());
            ofile.write(depo_amount.getBytes());
            ofile.write(" - ".getBytes());
            ofile.write(depo_reason.getBytes());
            ofile.write("\n".getBytes());


            ofile.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            double double_depo_amount = Double.parseDouble(depo_amount);
            balance += double_depo_amount;
            updateBalance(balance);
        }
    }
    void appendWithdrawalHistory(String with_date, String with_amount, String with_reason) {
        try {
            //we wrote true here in append so that we can add more records to the file and not remove the old ones that exist before
            FileOutputStream ofile = new FileOutputStream(".//file//withdraw.txt", true);

            ofile.write(with_date.getBytes());
            ofile.write(" - ".getBytes());
            ofile.write(with_amount.getBytes());
            ofile.write(" - ".getBytes());
            ofile.write(with_reason.getBytes());
            ofile.write("\n".getBytes());


            ofile.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            double double_with_amount = Double.parseDouble(with_amount);
            balance -= double_with_amount;
            updateBalance(balance);
        }
    }

    void createDepositHistory() {
        //just accepts the data and passes it to the appendDepsoiteHistory which is where the appending to file happens
        //here we create the deposit history by writing the date, amount and reasons related to money to a file called deposit.txt
        double depo_amount;
        String Str_depo_amount;
        String depo_reason;
        String depo_date = today.currentDate;

        System.out.print("Enter amount: ");
        depo_amount = input.nextDouble();
        Str_depo_amount = Double.toString(depo_amount);

        System.out.print("Enter reason: ");
        input.nextLine();
        depo_reason = input.nextLine();

        appendDepositHistory(depo_date, Str_depo_amount, depo_reason);
    }
    void createWithdrawalHistory() {
        //just accepts the data and passes it to the appendWithdrawHistory which is where the appending to file happens
        //here we create the withdrawal history by writing the date, amount and reasons related to money to a file called withdraw.txt
        double with_amount;
        String Str_with_amount;
        String with_reason;
        String with_date = today.currentDate;

        System.out.print("Enter amount: ");
        with_amount = input.nextDouble();
        Str_with_amount = Double.toString(with_amount);

        System.out.print("Enter reason: ");
        input.nextLine();
        with_reason = input.nextLine();

        appendWithdrawalHistory(with_date, Str_with_amount, with_reason);
    }
}
class FileAccess {
    //This class is used to read from the file and display to user: current balance, deposits and withdrawals
    void ViewCurrentBalance() {
        File file = new File(".\\file\\balance.txt");
        if(file.exists()) {
            try {
                Scanner ifile = new Scanner(new File(".\\file\\balance.txt"));
                String balance = ifile.nextLine();
                System.out.println(balance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    void ViewDepositHistory() {
        File file = new File(".\\file\\deposit.txt");
        if(file.exists()) {
            try {
                Scanner ifile = new Scanner(new File(".//file//deposit.txt"));
                while(ifile.hasNext()) {
                    String record = ifile.nextLine();
                    System.out.println(record);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    void ViewWithdrawHistory() {
        File file = new File(".\\file\\withdraw.txt");
        if(file.exists()) {
            try {
                Scanner ifile = new Scanner(new File(".//file//withdraw.txt"));
                while(ifile.hasNext()) {
                    String record = ifile.nextLine();
                    System.out.println(record);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
public class Main {
    static Scanner input = new Scanner(System.in);
    public static void othersmenu() {
        FileAccess view = new FileAccess();

        System.out.println("\t********************************");
        System.out.println("\t*\t\tOTHER                  *");
        System.out.println("\t********************************");
        System.out.println("\t*\t1. View Deposit History    *");
        System.out.println("\t*\t2. View Withdrawal History *");
        System.out.println("\t*\t3. Allowance status        *");
        System.out.println("\t*\t4. Back to MainMenu        *");
        System.out.println("\t********************************");

        int ch = input.nextInt();

        switch (ch) {
            case 1:
                view.ViewDepositHistory();
                try {
                    System.out.println("Press any key to continue...");
                    System.in.read();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                othersmenu();
                break;
            case 2:
                view.ViewWithdrawHistory();
                try {
                    System.out.print("Press any key to continue...");
                    System.in.read();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                othersmenu();
                break;
            case 3:
                new AllowanceDate();
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                othersmenu();
                break;
            case 4:
                mainmenu();
                break;
        }
    }
    public static void mainmenu() {
        //created this method so that i can call it when needed since there is no goto keyword in java
        FileAccess view = new FileAccess();
        CurrentBalance user = new CurrentBalance();

        System.out.println("\t**************************");
        System.out.println("\t*\t\tMENU             *");
        System.out.println("\t**************************");
        System.out.println("\t*\t1. Deposit           *");
        System.out.println("\t*\t2. Withdraw          *");
        System.out.println("\t*\t3. Balance           *");
        System.out.println("\t*\t4. Others            *");
        System.out.println("\t*\t5. Exit              *");
        System.out.println("\t**************************");

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                user.createDepositHistory();
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mainmenu();
                break;
            case 2:
                user.createWithdrawalHistory();
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mainmenu();
                break;
            case 3:
                view.ViewCurrentBalance();
                try {
                    System.out.print("Press any key to continue...");
                    System.in.read();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                mainmenu();
                break;
            case 4:
                othersmenu();
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mainmenu();
                break;
            case 5:
                break;
        }
    }
    public static void main(String[] argvs) {
        Account account = new Account();
        mainmenu();
    }
}