package com.qfedu.pojo;

import javax.persistence.*;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/6/7 11:00
 */
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String gerder;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "clbum_id")
    private Clbum clbum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGerder() {
        return gerder;
    }

    public void setGerder(String gerder) {
        this.gerder = gerder;
    }

    public Clbum getClbum() {
        return clbum;
    }

    public void setClbum(Clbum clbum) {
        this.clbum = clbum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gerder='" + gerder + '\'' +
                '}';
    }
}
