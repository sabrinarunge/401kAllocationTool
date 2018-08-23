package com.company;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class AllocationTool
{
    private BigDecimal[] percentages = new BigDecimal[4];           //Hold the percent of money invested in each fund
    private List<BigDecimal> dollarAmounts = new ArrayList<>();     //Hold the amount of money invested in each fund
    private String employeeName;                                    //Initialize variables
    private int employeeIdNumber;
    private BigDecimal totalAmountToInvest;
    private DecimalFormat df = new DecimalFormat("#.##");   //Set decimal format

    public static void main(String[] args)
    {
      AllocationTool allocationTool = new AllocationTool();
      allocationTool.run();
    }

    private void run()
    {
        boolean finished = false;

        do
        {
            printIntro();
            getEmployeeInfo();
            printMenu();
            allocateFunds();
            printSelections();
            reset();

        } while (!finished);

    }

    private void printIntro()                                       //Print introduction to program
    {
        System.out.println("Welcome to WallabyTech's 401k Allocation Tool");
        System.out.println("Please enter your name, employee ID number, and the amount you want to invest per pay period (Must be between $10 and $200 inclusive.)");
    }



    private void getEmployeeInfo()                                  //Get name, ID number, and investment amount from employee
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Name: ");
        employeeName = in.nextLine();
        System.out.print("Employee ID: ");
        employeeIdNumber = in.nextInt();
        System.out.print("Amount per pay period: ");
        totalAmountToInvest = in.nextBigDecimal();
        in.nextLine();
    }

    private void printMenu()                                        //Print fund options for employee to invest in
    {
        System.out.println();
        System.out.println("There are four funds available in which you can invest:");
        System.out.println("0) End of the World 2012");
        System.out.println("1) End of Time 2038");
        System.out.println("2) Y2K Survivors");
        System.out.println("3) Super Risky Optimists");
        System.out.println("\n");
    }

    private void allocateFunds()                                    //Add percentage and amount invested to respective Array and ArrayList
    {
        Scanner in = new Scanner(System.in);

        BigDecimal TOTAL_GOAL = new BigDecimal(100);            //Set goal to 100% allocated
        boolean goalReached = false;

        for (int i = 0; i < percentages.length; i++)                //Set indices to zero
        {
            percentages[i] = BigDecimal.ZERO;
        }

        do                                                          //Ask employee to choose what funds to invest in
        {
            System.out.println("Enter the name or number of the fund and the percentage of your money you would like to invest.");

            String commandLine = in.nextLine();
            String[] words = commandLine.split(" ");
            int index = 0;
            String fundChoice = (words[0]);
            if (fundChoice.equals("End of World 2012") || fundChoice.equals("EOW2012") || fundChoice.equals("eow2012") || fundChoice.equals("0"))
            {
                index = 0;
                System.out.println("You selected End of World 2012.");
            }
            else if (fundChoice.equals("End of Time 2038") || fundChoice.equals("EOT2038") || fundChoice.equals("eot2038") || fundChoice.equals("1"))
            {
                index = 1;
                System.out.println("You selected End of Time 2038.");
            }

            else if (fundChoice.equals("Y2K Survivors") || fundChoice.equals("Y2K") || fundChoice.equals("y2k") || fundChoice.equals("2"))
            {
                index = 2;
                System.out.println("You selected Y2K Survivors.");
            }

            else if (fundChoice.equals("Super Risky Optimists") || fundChoice.equals("SRO") || fundChoice.equals("sro") || fundChoice.equals("3"))
            {
                index = 3;
                System.out.println("You selected Super Risky Optimists.");
            }

            BigDecimal enteredPercentage = new BigDecimal(words[1]); //Ask employee what percentage of amount contributed they want to invest in each fund
            percentages[index] = enteredPercentage;
            BigDecimal total = new BigDecimal(0);

            for (BigDecimal percentage : percentages)               //Total the percentage of money invested
            {
                total = total.add(percentage);
            }

            System.out.println("You have invested " + total + "% of your money.");

            if (total.compareTo(TOTAL_GOAL) == 0)                   //If total equals 100%, exit the loop
            {
                goalReached = true;
            }
        } while (!goalReached);

        BigDecimal multiplicand = new BigDecimal(".01");

        for (BigDecimal percentage : percentages)                   //Calculate the dollar amount invested in each fund
        {
            BigDecimal intermediateValue = percentage.multiply(multiplicand);
            BigDecimal amountToInvest = intermediateValue.multiply(totalAmountToInvest);
            dollarAmounts.add(amountToInvest);
        }
    }

    private void printSelections()                                  //Print employee's information and fund selections
    {
        System.out.println("\n");
        System.out.print("Employee Name: ");
        System.out.println(employeeName);

        System.out.print("Employee ID: ");
        System.out.println(employeeIdNumber);
        System.out.println("\n");
        System.out.println("Fund" + "\t\t\t\t\t\t" + "Percent" + "\t\t\t" + "Amount/Pay Period");
        System.out.println("-------------------------------------------------------------");
        System.out.println("End of World 2012" + "\t\t\t\t" + percentages[0] + "\t\t\t\t\t" + df.format(dollarAmounts.get(0)));
        System.out.println("End of Time 2038" + "\t\t\t\t" + percentages[1] + "\t\t\t\t\t" + df.format(dollarAmounts.get(1)));
        System.out.println("Y2K Survivors" + "\t\t\t\t\t" + percentages[2] + "\t\t\t\t\t" + df.format(dollarAmounts.get(2)));
        System.out.println("Super Risky Optimists" + "\t\t\t" + percentages[3] + "\t\t\t\t\t" + df.format(dollarAmounts.get(3)));
        System.out.println("\n");
        System.out.println("Total Contribution Per Pay Period: " + totalAmountToInvest);
        System.out.println("\n");
    }

    private void reset()                                            //Clear Array and List to prepare for next employee
    {
        Arrays.fill(percentages, null);
        dollarAmounts.clear();
    }

}
