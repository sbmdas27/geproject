package com.ge.exercise3;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bank {

    private static final Logger logger = LogManager.getLogger(Bank.class);
    private Map<String, Account> accountMap;

    public Bank() {
        accountMap = new HashMap<>();
    }

    public Account getAccount(String accountNumber) {
        return accountMap.get(accountNumber);
    }

    public void addAccount(Account account) {
        accountMap.put(account.getAccountNumber(), account);
    }

    public void depositToAccount(String accountNumber, float amount) {
        getAccount(accountNumber).deposit(amount);
    }

    public void withdrawFromAccount(String accountNumber, float amount) {
        getAccount(accountNumber).withdraw(amount);
    }

    public int numAccounts() {
        return accountMap.size();
    }
    
    public float currentHoldings(){
    	float sum = 0.0f;
    	for (Map.Entry<String, Account> entry : accountMap.entrySet()) {
    	    sum += entry.getValue().getBalance();
    	}
    	logger.debug("Current holding "+ sum);
    	return sum;
    }
    
    public void predictNextMonthProfitLoss(){
    	float profitOrLoss = 0.0f;
    	for (Map.Entry<String, Account> entry : accountMap.entrySet()) {
    		Account account = entry.getValue();
    		profitOrLoss += account.valueNextMonth();
    	}
    	if(profitOrLoss>0){
    		logger.debug("Bank will produce profit of "+ profitOrLoss +" next month");
    	}else{
    		logger.debug("Bank will produce loss of "+ profitOrLoss +" next month");
    	}
    }
    
}
