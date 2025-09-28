package Service;

import DAO.UserDAO;
import Exceptions.ExceptionMessages;
import Exceptions.RentACarException;
import Models.Enums.UserRoles;
import Models.Users.User;
import Models.Vehicles.Vehicle;
import Utils.PasswordUtil;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserDAO userDao;

    public UserService() {
        this.userDao = new UserDAO();
    }

    public  void createUser (String name, String surname, String email, String password, UserRoles role, Integer age, String companyName) throws RentACarException {

        boolean isExist = userDao.existByEmail(email);

        if(isExist){
            throw new RentACarException(ExceptionMessages.USER_EMAIL_ALREADY_EXIST);
        }
            User user = new User(name, surname, email, PasswordUtil.hash(password), role, age, companyName);

            userDao.saveUser(user);

        System.out.println("You have successfully registered!");
        }


    public User loginUser(String email, String password) {

        boolean isExist = userDao.existByEmail(email);

        if (!isExist) {
            try {
                throw new RentACarException(ExceptionMessages.USER_EMAIL_DOES_NOT_EXIST);    //Burada ise kendi tanımladığım exception'ı ve mesajını fırlatıyorum.
            } catch (RentACarException e) {
                throw new RuntimeException();
            }
        }

        String hashedPassword = PasswordUtil.hash(password);

        User foundUser = userDao.findByEmail(email);

        if (foundUser != null) {
            boolean passwordEquals = foundUser.getPassword().equals(hashedPassword);
            if (passwordEquals) {


                System.out.println("You have successfully logged in!");

            } else {
                try {
                    throw new RentACarException(ExceptionMessages.USER_PASSWORD_DOES_NOT_EXIST);
                } catch (RentACarException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return foundUser;
    }




}

