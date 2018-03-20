import com.thoughtworks.account.Account;
import com.thoughtworks.account.LowBalanceException;
import com.thoughtworks.account.InsufficientBalanceExceptoin;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {
    Account account;
    @Before
    public void setUp() throws Exception, LowBalanceException {
        account = new Account ( "Manish" , 12345 , 1000 );
    }
    @Test
    public void getBalanceTest(){
        assertThat(account.getBalance () , is((double) 1000));
    }

    @Test
    public void getAccountNumberTest() {
        assertThat ( account.getAccountNumber (), is ( (long) 12345 ) );
    }


    @Test
    public void getHolderName() {
        assertThat ( account.getHolderName(), is ( "Manish" ));
    }

    @Test
    public void creditTest() throws LowBalanceException {
        Account account = new Account ( "manish" , 123456 , 5000 );
        assertThat ( account.credit(1000), is ( (double) 6000 ) );
        assertThat ( account.credit ( 100.5 ),is ( (double) 6100.5 ) );
    }

    @Test
    public void debitTest() throws LowBalanceException, InsufficientBalanceExceptoin {
        Account account = new Account ( "manu" , 12345 , 2000 );
        assertThat ( account.debit(200), is ((double) 1800 ) );
    }

    @Test(expected = LowBalanceException.class)
    public void lowBalanceTest() throws LowBalanceException {
        new Account ( "manu",1233, 200 );
    }

    @Test(expected = InsufficientBalanceExceptoin.class)
    public void insufficientBalTest() throws LowBalanceException, InsufficientBalanceExceptoin {
        Account account = new Account ( "manu" , 12356 , 2000 );
        account.debit(2000);
    }
}
