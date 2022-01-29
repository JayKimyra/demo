package com.example.demo.hibernate.entities;

import com.example.demo.Password;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="Users")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,length = 250)
    private String login;
    @Column
    private String role;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String password;

    public User(){}
    public User(String login, String firstname, String lastname, String password,String role) throws Exception {
        this.setLogin(login);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        String pass = Password.getSaltedHash(password);
        this.setPassword(pass);
        this.role = role;
    }

    public void setFirstname(String firstname) {
        if (firstname.length() > 1){
            this.firstname = firstname.substring(0,1).toUpperCase() + firstname.substring(1);
        }
    }

    public void setLastname(String lastname) {
        if (lastname.length() > 1){
            this.lastname = lastname.substring(0,1).toUpperCase() + lastname.substring(1);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
