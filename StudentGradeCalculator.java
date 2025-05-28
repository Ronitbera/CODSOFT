import java.util.*;
class StudentGradeCalculator
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int totalmarks=0, ns;
        System.out.println("Enter the number of subjects");
        ns=sc.nextInt();
        for(int i=1; i<=ns; i++)
        {
          System.out.println("Enter the marks obtained in subject " + i + ": ");
          int marks=sc.nextInt();
          totalmarks+=marks;
        }
        double avgperc = (double) totalmarks/ns;
        char grade;
        
        if(avgperc >=90)
        {
            grade='O';
        }
        else if(avgperc >=80 && avgperc<90)
        {
            grade='A';
        }
        else if(avgperc >=70 && avgperc <80)
        {
            grade='B';
        }
        else if(avgperc >=60 && avgperc <70)
        {
            grade='C';
        }
        else if(avgperc >=50 && avgperc <60)
        {
            grade='D';
        }
        else
        {
            grade='F';
        }

        System.out.println("Total marks obtained :" + totalmarks);
        System.out.println("Average Percentage obtained :" + avgperc);
        System.out.println("Grade obtained :" + grade);
    }
}