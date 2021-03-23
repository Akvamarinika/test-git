import java.util.HashMap;
import java.util.Random;

public class Bank
{
    private HashMap<String, Account> accounts;
    private final Random random = new Random();
    private static final int MAX_AMOUNT = 50_000;

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount, boolean isBlocked)
    {
        if (amount > MAX_AMOUNT && isBlocked){
            try {
                boolean isFraud = isFraud(fromAccountNum, toAccountNum, amount);
                if (isFraud){
                    long newAmount = accounts.get(fromAccountNum) - amount;
                   accounts.put(fromAccountNum, newAmount);
                }
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Sorry, you account is blocked!");
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum)
    {
        return 0;
    }
}
