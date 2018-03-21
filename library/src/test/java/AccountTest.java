import com.thoughtworks.account.*;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {
    Account account;
    @Before
    public void setUp() throws Exception, LowBalanceException, InvalidAccountNumException {
        account = new Account ( "Manish" , "1234-5636" , 1000 );
    }
    @Test
    public void getBalanceTest(){
        assertThat(account.getBalance () , is((double) 1000));
    }

    @Test
    public void getAccountNumberTest() {
        assertThat ( account.getAccountNumber (), is ("1234-5636" ) );
    }


    @Test
    public void getHolderName() {
        assertThat ( account.getHolderName(), is ( "Manish" ));
    }

    @Test
    public void creditTest() throws LowBalanceException, InvalidAccountNumException {
        Account account = new Account ( "manish" , "1234-5636" , 5000 );
        assertThat ( account.credit(1000), is ( (double) 6000 ) );
        assertThat ( account.credit ( 100.5 ),is ( (double) 6100.5 ) );
    }

    @Test
    public void debitTest() throws LowBalanceException, InsufficientBalanceExceptoin, InvalidAccountNumException {
        Account account = new Account ( "manu" , "1234-5636" , 2000 );
        assertThat ( account.debit(200), is ((double) 1800 ) );
    }

    @Test(expected = LowBalanceException.class)
    public void lowBalanceTest() throws LowBalanceException, InvalidAccountNumException {
        new Account ( "manu","1234-5636", 200 );
    }

    @Test(expected = InsufficientBalanceExceptoin.class)
    public void insufficientBalTest() throws LowBalanceException, InsufficientBalanceExceptoin, InvalidAccountNumException {
        Account account = new Account ( "manu" , "1234-5636" , 2000 );
        account.debit(2000);
    }

    @Test
    public void getSummaryTest () throws InvalidAccountNumException, LowBalanceException {
        Account account = new Account ( "manish Yadav" , "1234-5678" , 3000 );
        assertThat ( account.getSummary (),is ( "accountNumber=1234-5678, accountBalance=3000.0, holderName=manish Yadav" ) );
    }

    @Test()
    public void checkIfWithdrawIsAllowed() throws InvalidAccountNumException, LowBalanceException, InsufficientBalanceExceptoin {
        final Account account1 = new Account ( "manish" , "1234-5432" , 4000 );
        account1.debit(1000);
        assertThat ( account1.getBalance (),is ( 3000.0d ) );
    }
}
