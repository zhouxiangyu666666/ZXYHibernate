package src;

import java.sql.SQLException;

/**
 * Created by Jeremy on 2017/7/31.
 */
public class loginService {

    public Result login(String username, String password) {

        Result result = new Result();
        UserDao myUserDao = new UserDaoImpl();
        try {
            /**
             * 先判断用户名是否存在
             */
            if (myUserDao.queryUserName(username) == 0) {
                result.setCode(-1);
                return result;
            }
            else {
               if( myUserDao.queryPassword(username,password)==0){
                   result.setCode(0);
               }
               else {
                   result.setCode(-2);
               }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}