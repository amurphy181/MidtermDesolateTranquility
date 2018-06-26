package com.skilldistillery.jpadesolatemidterm.data;



public class SecurityDAOImpl implements SecurityDAO {
	
		private static int workload = 17;
		
		public static String hashPassword(String password_plaintext) {
			String salt = BCrypt.gensalt(workload);
			String hashed_password = BCrypt.hashpw(password_plaintext, salt);
		}
		
}
