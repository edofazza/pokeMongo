package it.unipi.dii.lsmsd.pokeMongo.bean;

public class GeneralUser implements User {
    protected boolean admin;
    protected String username;
    protected String email;
    protected String password;

    @Override
    public boolean isAdmin() {
        return admin;
    }

    @Override
    public String getUsername() {
        return  username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
