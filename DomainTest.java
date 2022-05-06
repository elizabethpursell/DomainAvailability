/* 
Name:Elizabeth Pursell
Date: 4/24/2022
CSE 007 Spring 2022: Homework 7
Produce DomainTest program that will validate a user's domain name and provide similar domain names if theirs is not available
IDE Used: Visual Studio Code
*/
import java.util.Scanner;       //import scanner class for user input
public class DomainTest {
    public static void main(String [] args){
        Scanner myScan = new Scanner(System.in);        //create scanner for user input
        boolean quit = false;
        do{         //loops until user enters blank string
            System.out.println("Enter a Domain Name To Check (Enter to Exit):");
            String domainName = getString(myScan);              //gets user's domain name
            if(domainName.equals("")){          //breaks loop if blank string
                quit = true;
                System.out.println("Goodbye");
            }
            else{
                DomainAvailability domain = new DomainAvailability(domainName);     //creates new object to check domain's availability
                int domainValid = domain.checkIfDomainNameIsValid(domain.getDomainName());      //calls method to determine if domain name is valid
                switch(domainValid){        //switch statement for each int possibility for previous method
                    case 0:
                        System.out.println("Valid Domain Name.");
                        break;
                    case -1:
                        System.out.println("Invalid Domain Name: Invalid Number of Periods.");
                        break;
                    case -2:
                        System.out.println("Invalid Domain Name: Invalid Period Position.");
                        break;
                    case -3:
                        System.out.println("Invalid Domain Name: Invalid Second-Level Domain Name.");
                        break;
                    case -4:
                        System.out.println("Invalid Domain Name: Invalid Top-Level Domain Name.");
                        break;
                    default:
                        break;
                }
                if (domainValid == 0){      //executes if domain name is valid
                    if (domain.hasAvailableDomainName(domain.getDomainName())){      //calls method to check domain's availability
                        System.out.println("Domain name is available.");
                    }
                    else{
                        System.out.println("Domain name is not available.");        //if not available provide similar domain names
                        System.out.print("Similar Domain Names: ");
                        System.out.println(domain.getSimilarDomainNames(domain.getDomainName()));
                    }
                }
            }
            System.out.println();
        }while(!quit);
    }
    public static String getString(Scanner myScan){     //method to get user's string
        return myScan.nextLine();
    }
}
