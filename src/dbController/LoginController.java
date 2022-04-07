package dbController;

import java.util.List;

import model.Account;

public class LoginController {
	
	public static boolean verifyCredentials(Account a) {
		if (UserController.isUserPresent(a.getUsername())) {
			List<Account> la = UserController.searchSpecificUser(a.getUsername());
			if (la == null || la.isEmpty()) {
				return false;
			} else {
				Account reqAcc = la.get(0);
				if (reqAcc.getPassword().equals(a.getPassword())) {
					return true;
				} else {
					return false;
				}
			}	
		}
		return false;
	}

}
