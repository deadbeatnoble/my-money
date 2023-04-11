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
            new AllowanceDate();
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
            input.nextLine();
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
    int amount;
    int round;
    Scanner input = new Scanner(System.in);
    CurrentDate today = new CurrentDate();
    AllowanceDate() {
        File file = new File(".\\file\\date.txt");
        if(file.exists()) {
            readLatestAllowanceDate();
        } else {
            //method to create the file...?????
            //call back the constructor???
            setAllowanceDate();
        }
        file = new File(".\\file\\amount.txt");
        if(file.exists()) {
            try {
                Scanner ifile = new Scanner(new File(".\\file\\amount.txt"));
                amount = Integer.parseInt(ifile.nextLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //method to create the file...?????
            //call back the constructor???
            setAllowanceAmount();
        }
        //call allowanceDateStatus...!!!!
    }
    private void readLatestAllowanceDate() {
        try {
            Scanner ifile = new Scanner(new File(".\\file\\date.txt"));
            int lines = 0;
            while (ifile.hasNextLine()) {
                String line = ifile.nextLine();
                if(lines == 0) {
                    day = Integer.parseInt(line);
                } else if (lines == 1) {
                    month = Integer.parseInt(line);
                } else if (lines == 2) {
                    year = Integer.parseInt(line);
                }
                lines++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setAllowanceDate() {
        System.out.println("\t\tSet Allowance Date");
        System.out.println("\t1. Set Current Date");
        System.out.println("\t2. Set Custom Date");
        int ch = input.nextInt();

        switch (ch) {
            case 1:
                try {
                    FileOutputStream ofile = new FileOutputStream(".\\file\\date.txt");
                    byte[] buffer = today.currentDay.getBytes();
                    ofile.write(buffer, 0, buffer.length);

                    ofile.write("\n".getBytes());

                    buffer = today.currentMonth.getBytes();
                    ofile.write(buffer, 0, buffer.length);

                    ofile.write("\n".getBytes());

                    buffer = today.currentYear.getBytes();
                    ofile.write(buffer, 0, buffer.length);

                    ofile.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                int Aday;
                int Amonth;
                int Ayear;

                System.out.print("Enter day: ");
                Aday = input.nextInt();
                System.out.print("Enter month: ");
                Amonth = input.nextInt();
                System.out.print("Enter year: ");
                Ayear = input.nextInt();

                String SAday = Integer.toString(Aday);
                String SAmonth = Integer.toString(Amonth);
                String SAyear = Integer.toString(Ayear);

                try {
                    FileOutputStream ofile = new FileOutputStream(".\\file\\date.txt");
                    byte[] buffer = SAday.getBytes();
                    ofile.write(buffer, 0, buffer.length);

                    ofile.write("\n".getBytes());

                    buffer = SAmonth.getBytes();
                    ofile.write(buffer, 0, buffer.length);

                    ofile.write("\n".getBytes());

                    buffer = SAyear.getBytes();
                    ofile.write(buffer, 0, buffer.length);

                    ofile.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    private void setAllowanceAmount() {
        System.out.print("Please enter the amount you receive as an allowance: ");
        amount = input.nextInt();
        try {
            FileOutputStream ofile = new FileOutputStream(".\\file\\amount.txt");
            ofile.write(Integer.toString(amount).getBytes());

            ofile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void allowanceDateStatus() {
        CurrentBalance depo = new CurrentBalance();

        if((Integer.parseInt(today.currentYear)) >= year) {
            if((Integer.parseInt(today.currentYear)) == year) {
                if((Integer.parseInt(today.currentMonth)) >= month) {
                    if((Integer.parseInt(today.currentMonth)) == month) {
                        System.out.println("Message: allowance has already been received on " + day + "/"+ month + "/"+ year + "!");
                    } else if ((Integer.parseInt(today.currentMonth)) > month) {
                        round = (Integer.parseInt(today.currentMonth)) - month;
                        for (int i = 0; i < round ; i++) {
                            System.out.println("Message: " + (round - i) + " Month worth allowance left to be received.");
                            System.out.println("Would you like to receive it(y/n)? ");
                            String ch = input.nextLine();
                            if(ch.equals("Y") || ch.equals("y")) {
                                String date = today.currentDay + "/" + (month + 1) + "/" + today.currentYear;
                                depo.appendDepositHistory(today.currentDate, Integer.toString(amount),"Allowance of " + date);
                                updateAllowanceDate(today.currentDay, Integer.toString(month+1), today.currentYear);
                                readLatestAllowanceDate();
                            } else if (ch.equals("N") || ch.equals("n")) {
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("Message: Invalid current date or allowance date!");
                }
            } else if ((Integer.parseInt(today.currentYear)) > year) {
                round = (12 - month) + Integer.parseInt(today.currentMonth);
                for (int i = 0; i < round ; i++) {
                    System.out.println("Message: " + (round - i) + " Month worth allowance left to be received.");
                    System.out.println("Would you like to receive it(y/n)? ");
                    String ch = input.nextLine();
                    if(ch.equals("Y") || ch.equals("y")) {
                        int newmonth;
                        int newyear;
                        if(month > 12) {
                            newmonth = month - 12;
                            newyear = year + 1;
                            year = newyear;
                            month = newmonth;
                        }
                        String date = today.currentDay + "/" + month + "/" + year;
                        depo.appendDepositHistory(today.currentDate,Integer.toString(amount),"Allowance of " + date);
                        updateAllowanceDate(today.currentDay,Integer.toString(month+1), Integer.toString(year));
                        readLatestAllowanceDate();
                    } else if (ch.equals("N") || ch.equals("n")) {
                        break;
                    }
                }
            }
        } else {
            System.out.println("Message: Invalid current date or allowance date!");
        }
    }
    private void updateAllowanceDate(String newday, String newmonth, String newyear) {
        try {
            FileOutputStream ofile = new FileOutputStream(".\\file\\date.txt");

            byte[] buffer = newday.getBytes();
            ofile.write(buffer,0, buffer.length);

            ofile.write("\n".getBytes());

            buffer = newmonth.getBytes();
            ofile.write(buffer,0, buffer.length);

            ofile.write("\n".getBytes());

            buffer = newyear.getBytes();
            ofile.write(buffer,0, buffer.length);

            ofile.close();
        } catch (Exception e) {
            e.printStackTrace();
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
                AllowanceDate allowance = new AllowanceDate();
                allowance.allowanceDateStatus();
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
        new Account();
        mainmenu();
    }
}