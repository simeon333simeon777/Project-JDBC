package com.cg.paytmwallet.test;
import static org.junit.Assert.*;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cg.paytmwallet.bean.Account;
import com.cg.paytmwallet.exception.PaytmWalletException;
import com.cg.paytmwallet.service.PaytmWalletService;
import com.cg.paytmwallet.service.PaytmWalletServiceImpl;

public class PaytmWallet {
	private PaytmWalletService service;
	@Before
	public void init() {
	service = new PaytmWalletServiceImpl();
	}
	@Test
	public void testCreateAccountForName() {
	Account ac = new Account();
	ac.setMobileNo("1234567890");
	ac.setName("simeon777");
	ac.setEmail("simeon777@gmail.com");
	ac.setBalance(500.0);
	try {
	service.createAccount(ac);
	} catch (PaytmWalletException e) {
	assertEquals("Name should start with capital letter and should contain only alphabets", e.getMessage());
	}
	}
	/*@Test
	public void testCreateAccountForEmailId() {
	Account account = new Account();
	account.setMobileNo("1234567890");
	account.setName("Simeon");
	account.setEmail("simeon777gmail");
	account.setBalance(500.0);
	try {
	service.createAccount(account);
	} catch (PaytmWalletException e) {
	assertEquals("Enter valid emailid", e.getMessage());
	}
	}*/
	@Test
	public void testCreateAccountForNameIsEmpty() {
	Account ac = new Account();
	ac.setMobileNo("7981123927");
	ac.setName("");
	ac.setEmail("simeon777@gmail.com");
	ac.setBalance(500.0);
	try {
	service.createAccount(ac);
	} catch (PaytmWalletException e) {
	assertEquals("Name cannot be empty", e.getMessage());
	}
	}
	@Test
	public void testShowBalanceForMobileNo() {
	try {
	service.showBalance("95059");
	} catch (PaytmWalletException e) {
	assertEquals("Mobile number should contain 10 digits",e.getMessage());
	}
	}
	@Test
	public void testCreateAccount() {
	Account account = new Account();
	account.setMobileNo("7981123927");
	account.setName("Simeon");
	account.setEmail("simeon777@gmail.com");
	account.setBalance(500.0);
	}
/*	@Test
	public void testShowBalanceForName() {
	Account account=new Account();
	account.setMobileNo("9505929555");
	try {
	service.showBalance(account.getMobileNo());
	assertEquals("Simeon", account.getName());
	} catch (PaytmWalletException e) {
	assertEquals("The mobile number does not exist",e.getMessage());
	}
	}*/
	/*@Test
	public void testShowBalanceForMobileNoDoesNotExist() {
	try {
	service.showBalance("9849807500");
	} catch (PaytmWalletException e) {
	assertEquals("The mobile number does not exist",e.getMessage());
	}
	}*/
	@Test
	public void testDepositForMobileNo() {
	Account account=new Account();
	account.setMobileNo("79811239");
	try {
	service.deposit(account.getMobileNo(), 230);
	} catch (PaytmWalletException e) {
	assertEquals("Mobile number should contain 10 digits",e.getMessage());
	}
	}
	/*@Test
	public void testDepositForMobileNoDoesNotExist() {
	Account account=new Account();
	account.setMobileNo("9948167788");
	try {
	service.deposit(account.getMobileNo(), 230);
	} catch (PaytmWalletException e) {
	assertEquals("The mobile number does not exist",e.getMessage());
	}
	}*/
	@Test
	public void testDeposit() {
	Account account=new Account();
	account.setMobileNo("9505928555");
	try {
	Account account1=service.deposit(account.getMobileNo(), 230);
	assertNotNull(account1);
	} catch (PaytmWalletException e) {
	 
	System.out.println(e.getMessage());
	}
	}
	 
	@Test
	public void testWithDrawForMobileNo() {
	Account ac=new Account();
	ac.setMobileNo("79811239");
	try {
	service.withdraw(ac.getMobileNo(), 230);
	} catch (PaytmWalletException e) {
    assertEquals("Mobile number should contain 10 digits",e.getMessage());
	}
	}
/*	@Test
	public void testWithdrawForMobileNoDoesNotExist() {
	Account ac=new Account();
	ac.setMobileNo("9396216550");
	try {
	service.withdraw(ac.getMobileNo(), 230);
	} catch (PaytmWalletException e) {
	assertEquals("The mobile number does not exist",e.getMessage());
	}
	}*/
	@Test
	public void testDepositForDepositAmt1() {
	Account ac=new Account();
	ac.setMobileNo("7981123927");
	try {
	service.deposit(ac.getMobileNo(), -500);
	} catch (PaytmWalletException e) {
	assertEquals("Deposit amount must be greater than zero",e.getMessage());
	}
	}
	@Test
	public void testWithdrawForAmt() {
	Account acc=new Account();
	acc.setMobileNo("7981123927");
	try {
	service.withdraw(acc.getMobileNo(), -530);
	} catch (PaytmWalletException e) {
	assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
	}
	}
	 
	@Test
	public void testFundTransferForMobileNo() {
	Account account=new Account();
	Account account1=new Account();
	account.setMobileNo("7981123");
	account1.setMobileNo("1234");
	try {
	service.fundTransfer(account.getMobileNo(),account1.getMobileNo(), 230);
	} catch (PaytmWalletException e) {
	assertEquals("Mobile number should contain 10 digits",e.getMessage());
	}
	}

	@Test
	public void testFundTransferForAmt() {
	Account account=new Account();
	Account account1=new Account();
	account.setMobileNo("8019653338");
	account1.setMobileNo("9059849344");
	try {
	service.fundTransfer(account.getMobileNo(), account1.getMobileNo(),  -500);
	} catch (PaytmWalletException e) {
	assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
	}
	}
	
	@Test
	public void testPrinttransactionDetails() {
	Account account=new Account();
	account.setMobileNo("7997776202");
	try {
	Account acc1=service.printTransactionDetails(account.getMobileNo());
	assertNotNull(acc1);
	} catch (PaytmWalletException e) {
	System.out.println(e.getMessage());
	}
	 
	}
	@Test
	public void testTransfer() {
		Account a=new Account();
		Account a2=new Account();
	a.setMobileNo("9396216550");
	a2.setMobileNo("7416594746");
	try {
	assertTrue(service.fundTransfer(a.getMobileNo(), a2.getMobileNo(),  230));
	} catch (PaytmWalletException e) {
	System.out.println(e.getMessage());
	}
	}
	@Test
	public void testCreateAccountForMobile() {
	Account ac = new Account();
	ac.setMobileNo("9573");
	ac.setName("Simeon");
	ac.setEmail("simeon@gmail.com");
	ac.setBalance(200.0);
	try {
	service.createAccount(ac);
	} catch (PaytmWalletException e) {
	assertEquals("Mobile number should contain 10 digits", e.getMessage());
	}
	}
	/*
		@Test
			public void testPrinttransactionDetailsForIncorrectMobileNo() {
			Account ac=new Account();
			ac.setMobileNo("98492285");
			try {
			Account acc=service.printTransactionDetails(ac.getMobileNo());
			assertNotNull(acc);
			} catch (PaytmWalletException e) {
				assertEquals("Mobile number should contain 10 digits",e.getMessage());
			}
			 
			} */
		/*@Test
			public void testPrinttransactionDetailsForMObileNoDoesExistInDb() {
			Account ac=new Account();
			ac.setMobileNo("9875486310");
			try {
			Account acc=service.printTransactionDetails(ac.getMobileNo());
			assertNotNull(acc);
			} catch (PaytmWalletException e) {
				assertEquals("The mobile number does not exist",e.getMessage());
			}
			 
			} */ 
		@Test
			public void testWithDrawForMobileNoInDb() {
				Account acc=new Account();
				acc.setMobileNo("944167487");
				try {
				service.withdraw(acc.getMobileNo(), 600);
				} catch (PaytmWalletException e) {
				// TODO Auto-generated catch block
				assertEquals("Mobile number should contain 10 digits",e.getMessage());
				}
				} 


	
}
