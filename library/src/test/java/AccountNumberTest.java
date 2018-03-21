import com.thoughtworks.account.Account;
import com.thoughtworks.account.InvalidAccountNumException;
import com.thoughtworks.account.LowBalanceException;
import org.junit.Test;

public class AccountNumberTest {
    @Test(expected = InvalidAccountNumException.class)
    public void invalidAccNumTestForAlphabates() throws InvalidAccountNumException, LowBalanceException {
        new Account ("manish","1we3-33e2",812375);
    }

    @Test(expected = InvalidAccountNumException.class)
    public void invalidAccNumTest() throws InvalidAccountNumException, LowBalanceException {
        new Account("manish","1823-332",812375);
    }
}
