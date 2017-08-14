package src;

import java.sql.SQLException;

/**
 * Created by Jeremy on 2017/8/4.
 */
public class RegisterService {

    public Result save(String username,String password){
        Result result = new Result();
        UserDao myUserDao = new UserDaoImpl();
               try {
                   if (myUserDao.queryUserName(username)==0){
                       User myUser = new User();
                       myUser.setName(username);
                       myUser.setPassword(password);

                       try{
                           myUserDao.save(myUser);
                           result.setCode(0);
                       }
                       catch (SQLException e){
                           e.printStackTrace();
                       }
                   }
                   else {
                       result.setCode(-1);
                   }
               }
               catch (SQLException e){
                   e.printStackTrace();
               }
        return result;
    }
}
