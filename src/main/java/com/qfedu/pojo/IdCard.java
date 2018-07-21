package com.qfedu.pojo;

import javax.persistence.*;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/6/7 15:50
 */
@Entity
@Table(name = "idcard")
public class IdCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cardNum;

    @OneToOne
    @JoinColumn(name = "people_id")
    private People people;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "IdCard{" +
                "id=" + id +
                ", cardNum='" + cardNum + '\'' +
                '}';
    }
}
