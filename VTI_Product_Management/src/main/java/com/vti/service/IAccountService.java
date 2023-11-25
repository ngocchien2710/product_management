package com.vti.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForUpdating;

public interface IAccountService extends UserDetailsService{//UserDetails  sai
	public List<Account> getAllAccounts();
	public Account getAccountByID(short id);

	public Account createNewAccount(AccountFormForCreating accountNewForm);

	public Account updateAccount(short id, AccountFormForUpdating accountUpdateForm);

	public void deleteAccountById(short id);

	Account getAccountByUsername(String username);
	
	public Account createNewAccountNewRegister(Account accountNewRegisterForm);
	
}
