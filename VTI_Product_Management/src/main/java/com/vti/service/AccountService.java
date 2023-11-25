package com.vti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForUpdating;
import com.vti.repository.IAccountRepository;

@Service
public class AccountService implements IAccountService{

	@Autowired
	private IAccountRepository accountRepository;
	
	@Override
	public List<Account> getAllAccounts() {
		
		return accountRepository.findAll();
	}
	
//	Dùng để mã hóa Password
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public Account createNewAccount(AccountFormForCreating accountNewForm) {
//		Tạo account cần thêm mới từ thông tin nhận được accountNewForm
		Account account = new Account();
		account.setEmail(accountNewForm.getEmail());
		account.setUsername(accountNewForm.getUsername());
		account.setFullname(accountNewForm.getFullname());
		account.setAvatarImageName(accountNewForm.getAvatarImageName());
		account.setMobile(accountNewForm.getMobile());
		account.setAddress(accountNewForm.getAddress());
		account.setPassword(accountNewForm.getPassword());

		Account accountNew = accountRepository.save(account);
		return accountNew;
	}

	@Override
	public Account getAccountByID(short id) {
		return accountRepository.getById(id);
	}
	

	@Override
	public Account updateAccount(short id, AccountFormForUpdating accountUpdateForm) {
//	Tìm Account cần update trên DB
		Account account = accountRepository.getById(id);
//	Thực hiện Update thông tin
		account.setFullname(accountUpdateForm.getFullname());
		account.setAvatarImageName(accountUpdateForm.getAvatarImageName());
		account.setMobile(accountUpdateForm.getMobile());
		account.setAddress(accountUpdateForm.getAddress());
//  Lưu lại Account xuống DB
		Account accountUpdate = accountRepository.save(account);
		return accountUpdate;
	}

	@Override
	public void deleteAccountById(short id) {
		accountRepository.deleteById(id);
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////		Tìm thông tin Account theo Username nhận được
//		Account account = accountRepository.findByUsername(username);
//
////		Kiểm tra xem có dữ liệu dưới DB?
//		if (account != null) {
//			return new User(account.getUsername(),account.getPassword(),AuthorityUtils.createAuthorityList("user"));
//					
//
//		} else {
//			throw new UsernameNotFoundException(username);
//
//		}
//
//	}


//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//

//	}

	@Override
	public Account getAccountByUsername(String username) {

		return accountRepository.findByUsername(username);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Tìm thông tin Account theo Username nhận được
		Account account = accountRepository.findByUsername(username);

//		Kiểm tra xem có dữ liệu dưới DB?
		if (account != null) {
			return new User(account.getUsername(),account.getPassword(),AuthorityUtils.createAuthorityList("user"));
					

		} else {
			throw new UsernameNotFoundException(username);

		}
	}


	@Override
	public Account createNewAccountNewRegister(Account accountNewRegisterForm) {
//		Tạo account cần thêm mới từ thông tin nhận được accountNewForm
		Account account = new Account();
		account.setEmail(accountNewRegisterForm.getEmail());
		account.setAddress(accountNewRegisterForm.getAddress());
		account.setAvatarImageName(accountNewRegisterForm.getAvatarImageName());
		account.setFullname(accountNewRegisterForm.getFullname());
		account.setMobile(accountNewRegisterForm.getMobile());
		account.setUsername(accountNewRegisterForm.getUsername());
//		Thực hiện mã hóa password trước khi lưu trữ
		String passEncode = passwordEncoder.encode(accountNewRegisterForm.getPassword());
		account.setPassword(passEncode);
		
		Account accountNew = accountRepository.save(account);
		return accountNew;
	}

}
