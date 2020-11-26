package it.unipi.dii.lsmsd.pokeMongo.bean;

public class AdminUser extends GeneralUser {

    public AdminUser(boolean admin, String username, String email, String password) {
        this.admin = admin;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
