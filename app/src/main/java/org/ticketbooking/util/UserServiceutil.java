package org.ticketbooking.util;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceutil {

    public static String hashPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword){
        return BCrypt.checkpw(plainPassword, hashedPassword);
        // local db mein hashedPassword store kiya hai
        // when a user logins toh they enter the plain password
        // the plain password is matched with the hashedPassword stored in localDB
    }
}
