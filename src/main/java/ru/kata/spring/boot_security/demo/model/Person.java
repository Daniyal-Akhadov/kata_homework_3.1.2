package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "person_role", // Имя таблицы для связи
            joinColumns = @JoinColumn(name = "person_id"), // Колонка, которая ссылается на текущую сущность (Student)
            inverseJoinColumns = @JoinColumn(name = "role_id") // Колонка, которая ссылается на связанную сущность (Course)
    )
    private Set<Role> roles;

    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }

    public Set<Role> getRoles() {
        if (roles == null) {
            roles = new HashSet<>();
        }
        
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
