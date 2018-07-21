package com.qfedu;

import com.qfedu.pojo.Clbum;
import com.qfedu.pojo.Student;
import com.qfedu.utils.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/6/7 11:11
 */
public class Jpa_test {

    private final static Logger LOG = LogManager.getLogger(Jpa_test.class);

    /**
     *
     * @Description: 在一方设置mappedBy和cascade
     * 通过一方进行保存，因为设置了mappedBy所以关联关系需要手动添加
     * @auther: lichao
     * @date: 2018/6/7 14:17
     * @param: []
     * @return: void
     */
    @Test
    public void save() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = HibernateUtil.getTransaction();

        Clbum clbum = new Clbum();
        clbum.setName("一班");

        Student s1 = new Student();
        s1.setName("小草");
        s1.setGerder("女");

        Student s2 = new Student();
        s2.setName("大树");
        s2.setGerder("男");

        Student s3 = new Student();
        s3.setGerder("女");
        s3.setName("小云");

        Student student = session.get(Student.class, 3);
        //student.setName("小天");

        //clbum.getStudents().add(s1);
        //clbum.getStudents().add(s2);
        //s2.setClbum(clbum);
        //Clbum c = session.get(Clbum.class, 1);
        //s3.setClbum(c);
        //s1.setClbum(c);

        //session.persist(clbum);
        //session.persist(s1);
        //session.persist(s2);
        //session.persist(s3);
        session.persist(student);
        transaction.commit();

        HibernateUtil.close();
    }

    /*---------------------------测试惰性加载-----------------------------*/

    /**
     *
     * @Description: 通过一方查询多方下，默认是懒加载
     * 设置fetch = FetchType.EAGER,改为迫切加载
     * @auther: lichao
     * @date: 2018/6/7 14:52
     * @param: []
     * @return: void
     */
    @Test
    public void lazyTest() {
        Session session = HibernateUtil.openSession();

        Clbum clbum = session.get(Clbum.class, 1);

        LOG.info("============" + "执行了" + "============");
        LOG.info(clbum.getStudents());

    }

    /**
     *
     * @Description: 通过多方查询一方时，默认是迫切加载
     * 因为多方中需要维护外键，所以在查询多方时会同时查询关联对象
     * 设置fetch = FetchType.LAZY,改为懒加载
     * @auther: lichao
     * @date: 2018/6/7 14:55
     * @param: []
     * @return: void
     */
    @Test
    public void lazyTest2() {
        Session session = HibernateUtil.openSession();

        Student student = session.get(Student.class, 1);

        LOG.info("============" + "执行了" + "============");
        LOG.info(student.getClbum());
    }


    /*-----------------------QBC测试--------------------------*/

    @Test
    public void QBCTest() {
        Session session = HibernateUtil.openSession();

        Criteria criteria = session.createCriteria(Clbum.class);

        Object o = criteria.uniqueResult();

        LOG.info(o);
    }

    @Test
    public void QBCTest2() {
        Session session = HibernateUtil.openSession();

        Criteria criteria = session.createCriteria(Student.class);

        List<Student> students = criteria.list();

        for (Student student : students) {
            LOG.info(student);
        }
    }

}
