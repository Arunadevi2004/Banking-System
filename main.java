import java.util.Scanner;

public class main {
    private static final String ADMIN_PASSWORD = "JMAD@Bank2026";

    public static void main (String[] args) {
        bankmanagement bankSystem = new bankmanagement();
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("---------------------***************************---------------------");
            System.out.println("                        WELCOME TO JMAD BANK                         ");
            System.out.println("---------------------***************************---------------------");
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int mainChoice = scanner.nextInt();

            if (mainChoice == 1) {
                System.out.print("Enter Admin Password: ");
                String inputPassword = scanner.next();

                if (ADMIN_PASSWORD.equals(inputPassword)) {
                    boolean adminLoggedIn = true;
                    while (adminLoggedIn) {
                        System.out.println("\n--- ADMIN MENU ---");
                        System.out.println("1. Create Account");
                        System.out.println("2. View All Accounts");
                        System.out.println("3. Search Account");
                        System.out.println("4. Delete Account");
                        System.out.println("5. Total Accounts");
                        System.out.println("6. Logout");
                        System.out.print("Enter choice: ");

                        int adminChoice = scanner.nextInt();

                        switch (adminChoice) {
                            case 1:
                                System.out.print("Enter Account Holder Name: ");
                                scanner.nextLine(); 
                                String name = scanner.nextLine();
                                System.out.println("Enter Initial Deposit Amount: ");
                                double amount = scanner.nextDouble();
                                if(amount<1000){
                                    System.out.println("Minimum Initial Deposit Amount : 1000 , Account Cannot be Created");
                                    break;
                                }
                                bankSystem.addAcc(name,amount);
                                break;
                            case 2:
                                bankSystem.viewAcc();
                                break;
                            case 3:
                                System.out.print("Enter Account Number to Search: ");
                                String searchNum = scanner.next();
                                account found = bankSystem.searchAcc(searchNum);
                                if (found != null) {
                                    System.out.println(found.display());
                                } else {
                                    System.out.println("No Account Found");
                                }
                                break;
                            case 4:
                                System.out.print("Enter Account Number to Delete: ");
                                String deleteNum = scanner.next();
                                bankSystem.deleteAcc(deleteNum);
                                break;
                            case 5:
                                bankSystem.totalacc();
                                break;
                            case 6:
                                adminLoggedIn = false;
                                System.out.println("Admin logged out successfully.");
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    }
                } else {
                    System.out.println("Incorrect password. Access denied.");
                }

            } else if (mainChoice == 2) {
                System.out.print("Enter Your Account Number: ");
                String loginAccNum = scanner.next();
                account userAccount = bankSystem.searchAcc(loginAccNum);

                if (userAccount != null) {
                    boolean userLoggedIn = true;
                    System.out.println("Welcome inside your account menu!");

                    while (userLoggedIn) {
                        System.out.println("\n--- USER MENU ---");
                        System.out.println("1. Deposit Money");
                        System.out.println("2. Withdraw Money");
                        System.out.println("3. Transfer Money");
                        System.out.println("4. View Account Details");
                        System.out.println("5. Transaction History");
                        System.out.println("6. Logout");
                        System.out.print("Enter choice: ");

                        int userChoice = scanner.nextInt();

                        switch (userChoice) {
                            case 1:
                                System.out.print("Enter Deposit Amount: ");
                                double depAmount = scanner.nextDouble();
                                bankSystem.depositMoney(loginAccNum, depAmount);
                                break;
                            case 2:
                                System.out.print("Enter Withdrawal Amount: ");
                                double withAmount = scanner.nextDouble();
                                bankSystem.withdrawMoney(loginAccNum, withAmount);
                                break;
                            case 3:
                                System.out.print("Enter Target Account Number: ");
                                String targetAccNum = scanner.next();
                                if (targetAccNum.equals(loginAccNum)) {
                                    System.out.println("Transfer failed: Cannot transfer to self.");
                                    break;
                                }
                                System.out.print("Enter Transfer Amount: ");
                                double transAmount = scanner.nextDouble();
                                bankSystem.transferMoney(loginAccNum, targetAccNum, transAmount);
                                break;
                            case 4:
                                bankSystem.viewAccDetails(loginAccNum);
                                break;
                            case 5:
                                bankSystem.TransactionHistory(loginAccNum);
                                break;
                            case 6:
                                userLoggedIn = false;
                                System.out.println("User logged out successfully.");
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    }
                } else {
                    System.out.println("Account not found.");
                }

            } else if (mainChoice == 3) {
                System.out.println("Exiting system. Goodbye!");
                return;
            } else {
                System.out.println("Invalid entry. Try again.");
            }
        }
    }
}
