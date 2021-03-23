import java.util.concurrent.atomic.AtomicLong;

public class Account
{
    private AtomicLong money = new AtomicLong();
    private String accNumber;
    private boolean isBlocked;

    public Account(long money, String accNumber){
        this.money.set(money);
        this.accNumber = accNumber;
        isBlocked = true;
    }

    public void replenish(long amount){
        this.money.addAndGet(amount);

    }

    public void withdraw(long amount){
        this.money.accumulateAndGet(amount);
    }

}
