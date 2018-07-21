package com.qfedu.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: lichao
 * @Description:
 * @Date: 2018/6/7 11:02
 */
@Entity
@Table(name = "clbum")
public class Clbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "clbum")
    private Set<Student> students = new HashSet<Student>();

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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Clbum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
