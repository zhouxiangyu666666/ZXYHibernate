package src;

import java.sql.SQLException;

/**
 * Created by Jeremy on 2017/8/3.
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void save(User user) throws SQLException {
        try {
        HibernateHelper.add(user);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int queryUserName(String username) throws SQLException {
        int i=0;
        try {
            if (HibernateHelper.select(User.class,username).size()!=0){
                i=-1;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int queryPassword(String username,String password) throws SQLException {
         int resultI = 0;
         User user =HibernateHelper.select(User.class,username).get(0);
         if (!user.getPassword().equals(password)){
             resultI = -1;
         }
         return resultI;
    }
}
