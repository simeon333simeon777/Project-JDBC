package com.cg.paytmwallet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;

import com.cg.paytmwallet.bean.Account;
import com.cg.paytmwallet.exception.PaytmWalletException;


public class PaytmWalletDb {
	public static Connection getConnection() throws PaytmWalletException{
		
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection(url,"system","root");
			}catch(ClassNotFoundException e){
			throw new PaytmWalletException(e.getMessage());
			}catch(SQLException e1){
			throw new PaytmWalletException(e1.getMessage());
			}
			 
			 
			}
}