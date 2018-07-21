package com.qfedu;

import com.qfedu.pojo.Role;
import com.qfedu.pojo.User;
import com.qfedu.utils.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/6/7 15:34
 */
public class UserRole {

    private final static Logger LOG = LogManager.getLogger(UserRole.class);


    @Test
    public void save() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = HibernateUtil.getTransaction();

        User user = new User();
        user.setName("小花");
        user.setGender("女");

        Role r1 = new Role();
        r1.setName("学生");

        Role r2 = new Role();
        r2.setName("老师");

        user.getRoles().add(r1);
        user.getRoles().add(r2);

        //r1.getUsers().add(user);
        //r2.getUsers().add(user);

        session.merge(user);
        //session.save(r1);
        //session.save(r2);

        transaction.commit();
        HibernateUtil.close();
    }

    /*--------------------------many2many懒加载测试-----------------------------*/

    /**
     *
     * @Description: 默认懒加载
     * @auther: lichao
     * @date: 2018/6/7 16:11
     * @param: []
     * @return: void
     */
    @Test
    public void lazyTest() {
        Session session = HibernateUtil.openSession();

        User user = session.get(User.class, 1);

        LOG.info("============" + "执行了" + "============");
        LOG.info(user.getRoles());
    }

    /**
     *
     * @Description: 默认懒加载
     * @auther: lichao
     * @date: 2018/6/7 16:12
     * @param: []
     * @return: void
     */
    @Test
    public void lazyTest2() {
        Session session = HibernateUtil.openSession();

        Role role = session.get(Role.class, 1);

        LOG.info("============" + "执行了" + "============");
        LOG.info(role.getUsers());
    }

}
