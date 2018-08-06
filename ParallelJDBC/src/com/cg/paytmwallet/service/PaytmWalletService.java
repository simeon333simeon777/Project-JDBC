package com.cg.paytmwallet.service;

import com.cg.paytmwallet.bean.Account;
import com.cg.paytmwallet.exception.PaytmWalletException;

public interface PaytmWalletService {
	String createAccount(Account acc) throws PaytmWalletException;
	double showBalance(String mobileNo) throws  PaytmWalletException;
	Account deposit(String mobileNo,double depositAmt) throws  PaytmWalletException;
	Account withdraw(String mobileNo,double withdrawAmt) throws  PaytmWalletException;
	boolean fundTransfer(String sourceMobileNo,String destMobileNo,double transferAmt) throws  PaytmWalletException;
	Account printTransactionDetails(String mobileNo) throws  PaytmWalletException;
}
