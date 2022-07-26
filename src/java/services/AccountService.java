package services;

import dataaccess.UserDB;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class AccountService {

    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();

        try {
            User user = userDB.get(email);
            // if the user is valied
            if (password.equals(user.getPassword())) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);

                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                
                GmailService.sendMail(to, subject, template, tags);
                
                // the final argument is a boolean flag. whether the body contains html or not. its not. so its false.
                //GmailService.sendMail(to, subject, "New login to notes app!", false);
                // can be "<h1></h1>", true
                
                
                return user;
            }
        } catch (Exception e) {
        }

        return null;
    }
    
    public void resetPassword(String email, String path, String url) {
        String uuid = UUID.randomUUID().toString();
        
                UserDB userDB = new UserDB();

        try {
            // to get a user if the user is valied
            User user = userDB.get(email);
            // use the random uuid
            user.setResetPasswordUuid(uuid);
            // store it in the DB
            userDB.update(user);

                String to = user.getEmail();
                String subject = "Reset Password";
                String template = path + "/emailtemplates/resetpassword.html";
                String link = url + "?uuid=" + uuid;
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("link", link);
                
                GmailService.sendMail(to, subject, template, tags);
                

        } catch (Exception e) {
        }
    }
    
       public boolean changePassword(String uuid, String password) {
       UserDB userDB = new UserDB();
        try {
            User user = userDB.getByUUID(uuid);
            user.setPassword(password);
            user.setResetPasswordUuid(null);
            userDB.update(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
