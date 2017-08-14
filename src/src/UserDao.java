package src;

import java.sql.SQLException;

/**
 * Created by Jeremy on 2017/8/3.
 */

public interface UserDao {

    public void save (User user) throws SQLException;

    public int queryUserName(String username) throws  SQLException;

    public int queryPassword(String username,String password) throws SQLException;

}
