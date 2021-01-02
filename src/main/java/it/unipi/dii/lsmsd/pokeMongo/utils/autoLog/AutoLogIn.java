package it.unipi.dii.lsmsd.pokeMongo.utils.autoLog;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.persistence.TeamManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.TeamManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerFactory;

import java.util.ArrayList;

public class AutoLogIn {
    public static void main(String[] args) {
        // TAKE ALL THE USERS
        UserManager userManager = UserManagerFactory.buildManager();
        ArrayList<User> allUsers = userManager.getEveryUser();

        for (User user: allUsers) {
            if (Math.random() < .970)
                continue;
            userManager.login(user.getUsername(),user.getName() + user.getSurname() + "000");

            user.addTeam(retrieveTeam(user));

            // Update the point in mongodb
            userManager.updatePoints(user, user.getPoints());
        }
    }

    private static Pokemon[] retrieveTeam(User user) {
        TeamManager teamManager = TeamManagerFactory.buildManager();
        return teamManager.getUserTeam(user);
    }
}
