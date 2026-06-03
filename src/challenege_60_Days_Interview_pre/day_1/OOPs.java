package challenege_60_Days_Interview_pre.day_1;

public class OOPs {
    public static void main(String[] args) {
        //Encapsulation
        BankAccount acc = new BankAccount();
        acc.setBalance(1000);
        System.out.println(acc.getBalance());
        acc.setBalance(2000);
        System.out.println(acc.getBalance());

        //

    }
}

class BankAccount{

    private double balance;//data hiding

    //getter
    public double getBalance(){
        return balance;
    }

    //setter + validation
    public void setBalance(double amount){
        if(amount > 0){
            balance += amount;
        }
    }


}