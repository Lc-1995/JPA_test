package com.qfedu;

import com.qfedu.pojo.IdCard;
import com.qfedu.pojo.People;
import com.qfedu.utils.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/6/7 15:55
 */
public class PeopleIdCard {

    private final static Logger LOG = LogManager.getLogger(PeopleIdCard.class);


    @Test
    public void save() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = HibernateUtil.getTransaction();

        People people = new People();
        people.setName("小花");

        IdCard card = new IdCard();
        card.setCardNum("12345153");

        card.setPeople(people);
        people.setIdCard(card);

        session.save(people);
        session.save(card);

        transaction.commit();
        HibernateUtil.close();
    }

    /*------------------------one2one懒加载测试------------------------------*/

    /**
     *
     * @Description: 没有外键的一方默认迫切加载
     * @auther: lichao
     * @date: 2018/6/7 16:04
     * @param: []
     * @return: void
     */
    @Test
    public void lazyTest() {
        Session session = HibernateUtil.openSession();

        People people = session.get(People.class, 1);

        LOG.info("============" + "执行了" + "============");
        LOG.info(people.getIdCard().getCardNum());
    }

    /**
     *
     * @Description: 有外键的一方也是默认迫切加载
     * @auther: lichao
     * @date: 2018/6/7 16:06
     * @param: []
     * @return: void
     */
    @Test
    public void lazyTest2() {
        Session session = HibernateUtil.openSession();

        IdCard card = session.get(IdCard.class, 1);

        LOG.info("============" + "执行了" + "============");
        LOG.info(card.getPeople().getName());
    }
}
