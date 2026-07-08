import java.util.ArrayList;

public class bankmanagement {
    ArrayList<account> accountlist = new ArrayList<>();

    //admin operation

    public void addAcc(String accountHolderName, double amount){
        account obj= new account(accountHolderName, amount);
        accountlist.add(obj);
        System.out.println("Account Created Successfully");
    }

    public void viewAcc(){
        if(accountlist.isEmpty()){
            System.out.println("No Account Listed");
        }
        else{
            for(account obj: accountlist){
                System.out.println(obj.display());
            }
        }
    }

    public account searchAcc(String accountnum){
        for(account obj : accountlist){
            if(obj.getacc_num().equals(accountnum)){
                return obj;
            }
        }
        return null;
    }

    public void deleteAcc(String accountnum){
        account obj=searchAcc(accountnum);
        if(obj==null){
             System.out.println("No Account Found");
             return;
        }
        else{
            accountlist.remove(obj);
            System.out.println("Account Deleted Successfully");
        }
    }

    public void totalacc(){
        System.out.println("Total Number of Accounts: "+accountlist.size());
    }

    //user operation

    public void depositMoney(String accountnum, double amount){
        account obj=searchAcc(accountnum);
        if(obj==null){
            System.out.println("Invalid Account Number");
            return;
        }
        else{
            obj.deposit(amount);
        }
    }

    public void withdrawMoney(String accountnum, double amount){
        account obj=searchAcc(accountnum);
        if(obj==null){
            System.out.println("Invalid Account Number");
            return;
        }
        else{
            obj.withdraw(amount);
        }
    }

    public void transferMoney(String accountnum1, String accountnum2, double amount){
        account obj1 = searchAcc(accountnum1);
        account obj2=searchAcc(accountnum2);
        if(obj1 != null && obj2 != null){
            if(obj1.getbalance()>=amount && amount>0){
                obj1.setbalance(obj1.getbalance()- amount);
                obj1.addTransferLog("Transfer Sent to " + accountnum2, amount);
                obj2.setbalance(obj2.getbalance()+ amount);
                obj2.addTransferLog("Transfer Recv from " + accountnum1, amount);
                System.out.println("Amount transfered Successfully");
            }
            else{
                System.out.println("Insufficient Balance");
                return;
            }
        }
        else{
            System.out.println("Invalid Account Number");
        }  
    }

    public void viewAccDetails(String accountnum){
        account obj=searchAcc(accountnum);
        if(obj==null){
            System.out.println("No Account found");
        }
        else{
            System.out.println(obj.display());
        }
    }

    public void TransactionHistory(String accountnum){
        account obj= searchAcc(accountnum);
        if(obj==null){
            System.out.println("No Account found");
        }
        else{
            obj.displayTransactionHistory();
        }
    }
}