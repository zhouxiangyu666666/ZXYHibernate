package src;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jeremy on 2017/8/9.
 */
public class HibernateHelper {
     private static SessionFactory sessionFactory;
     static {
         Configuration cfg = new Configuration().configure();
         /**
          * 只是用xml文件方式加载hibernate配置
          */
         sessionFactory = cfg.buildSessionFactory();
         /**
          * 用xml文件方式加载hibernate配置或者用注解加载
          */
         //sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
     }
    /**
     * 获取sessionFactory
     */
     public static SessionFactory getSessionFactory(){
         return sessionFactory;
     }
    /**
     * 获取一个新的session
     */
     public static Session getNewSession(){
        return sessionFactory.openSession();
    }
    /**
     * 如果想使用sessionFactory.getCurrentSession()来获得Session时，需要在配置文件中添加一句：
     * <!-- 本地事务 防止使用sessionFactory.getCurrentSession()时报错："org.hibernate.HibernateException: No CurrentSessionContext configured!"-->
     * <property name="hibernate.current_session_context_class">thread</property>
     * @return
     */
    /**
     * 获取当前session
     */
    public static Session getCurrentSession(){
         return sessionFactory.getCurrentSession();
    }
    /**
     * add
     */
     public static void add(Object object){
         Session s=null;
         Transaction tx = null;
         try {
            s= HibernateHelper.getNewSession();
            tx = s.beginTransaction();
            s.save(object);
            tx.commit();
         }
         catch (HibernateException e){
             e.printStackTrace();
         }
         finally {
             if (s!=null){
                 s.close();
             }
         }
     }
    /**
     * get
     */
    public static Object get(Class clazz, Serializable id){
        Session s = null;
        Object obj = null;
        try {
            s = HibernateHelper.getNewSession();
            obj = s.get(clazz,id);

        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            if (s!=null){
                s.close();
            }
        }
        return obj;
    }
    /**
     * update
     */
     public static void update(Object object){
         Session s = null;
         Transaction tx = null;
         try {
             s=HibernateHelper.getNewSession();
             tx = s.beginTransaction();
             s.update(object);
             tx.commit();
         }
         catch (HibernateException e){
             e.printStackTrace();
         }
         finally {
             if (s!=null){
                 s.close();
             }
         }
     }
    /**
     * delete
     */
     public static void delete(Object object){
         Session s = null;
         Transaction tx = null;
         try{
             s=HibernateHelper.getNewSession();
             tx = s.beginTransaction();
             s.delete(object);
             tx.commit();
         }
         catch (HibernateException e){
             e.printStackTrace();
         }
         finally {
             if (s!=null){
                 s.close();
             }
         }
     }
    /**
     * get
     */
     public static List<User> select(Class clazz,String username) throws HibernateException{
         Session s =null;
         List<User> userList = null;
         try {
             s=HibernateHelper.getNewSession();
             Query qi = s.createQuery("from src.User u where u.name=:customername");
             qi.setString("customername",username);
             userList = qi.list();

         }
         catch (HibernateException e){
             e.printStackTrace();
         }
         finally {
             if (s!=null){
                 s.close();
             }
         }
         return userList;
     }
}
