package com.cg.paytmwallet.service;
import com.cg.paytmwallet.bean.Account;
import com.cg.paytmwallet.dao.PaytmWalletDao;
import com.cg.paytmwallet.dao.PaytmWalletImpl;
import com.cg.paytmwallet.exception.PaytmWalletException;

public class PaytmWalletServiceImpl implements PaytmWalletService {
	PaytmWalletDao wdao = new PaytmWalletImpl();

	@Override
	public String createAccount(Account acc) throws PaytmWalletException {
		if (!acc.getMobileNo().matches("\\d{10}")) {
			throw new PaytmWalletException("Mobile number should contain 10 digits");
		}
		if (acc.getName().isEmpty() || acc.getName() == null) {
			throw new PaytmWalletException("Name cannot be empty");
		} else {
			if (!acc.getName().matches("[A-Z][A-Za-z]{3,}")) {
				throw new PaytmWalletException(
						"Name should start with capital letter and should contain only alphabets");
			}
		}
		if (acc.getBalance() < 0) {
			throw new PaytmWalletException("Balance should be greater than zero");
		}
		if (acc.getEmail().matches("[a-z0-9]+@[a-z]+\\.com")) {
			throw new PaytmWalletException("Enter valid emailid");
		}

		return wdao.createAccount(acc);
	}

	@Override
	public double showBalance(String mobileNo) throws PaytmWalletException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new PaytmWalletException("Mobile number should contain 10 digits");
		}
		return wdao.showBalance(mobileNo);
	}

	@Override
	public Account deposit(String mobileNo, double depositAmt)
			throws PaytmWalletException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new PaytmWalletException("Mobile number should contain 10 digits");
		}

		if (depositAmt <= 0) {
			throw new PaytmWalletException(
					"Deposit amount must be greater than zero");
		}

		return wdao.deposit(mobileNo,depositAmt);

	}

	@Override
	public Account withdraw(String mobileNo, double withdrawAmt)
			throws PaytmWalletException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new PaytmWalletException("Mobile number should contain 10 digits");
		}

		if ( withdrawAmt <= 0) {
			throw new PaytmWalletException(
					"The amount to be withdrawn should be greater than available balance and greater than zero");
		}

		Account acc1 = wdao.withdraw(mobileNo,withdrawAmt);
		return acc1;
	}

	@Override
	public boolean fundTransfer(String sourceMobileNo, String destMobileNo,
			double transferAmt) throws PaytmWalletException {
		if (!sourceMobileNo.matches("\\d{10}")) {
			throw new PaytmWalletException("Mobile number should contain 10 digits");
		}
		if (!destMobileNo.matches("\\d{10}")) {
			throw new PaytmWalletException("Mobile number should contain 10 digits");
		}
		PaytmWalletService service = new PaytmWalletServiceImpl();
		Account acc1 = service.withdraw(sourceMobileNo, transferAmt);
		Account d2 = service.deposit(destMobileNo, transferAmt);
		return true;
	}

	@Override
	public Account printTransactionDetails(String mobileNo) throws PaytmWalletException {
		return wdao.printTransactionDetails(mobileNo);
	}


}
