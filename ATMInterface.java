import java.util.*;
class ATMInterface
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int balance=10000,withdraw,deposit;
        while(true)
        {
            System.out.println("______Welcome to the ATM Machine______");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw amount");
            System.out.println("3. Deposit amount");
            System.out.println("4. Exit");
            System.out.print("Choose the operation you used to perform: ");
            int choice=sc.nextInt();
            switch(choice)
            {
                case 1:
                System.out.println("Check Balance: " + balance);
                System.out.println(" ");
                break;
                
                case 2:
                System.out.print("Enter the amount to withdraw: ");
                withdraw=sc.nextInt();
                if(balance >= withdraw)
                {
                    balance=balance-withdraw;
                    System.out.println("Please collect your money");
                }
                else
                {
                  System.out.println("Insufficient balance");
                }
                System.out.println(" ");
                break;

                case 3:
                System.out.print("Enter the amount to be deposited: ");
                deposit=sc.nextInt();
                balance=balance+deposit;
                System.out.println("Your money has been deposited successfully");
                System.out.println("");
                break;

                case 4:
                System.exit(0);
            }
        }
        
    }
}