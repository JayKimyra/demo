package com.example.demo.hibernate.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


@Entity
@Getter
@Setter
@Table(name = "records")
public class Record {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Timestamp date;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Street street;
    @Column
    private String home;
    @Column
    private String flat;
    @Column
    private String name;
    @Column
    private String path;

    public Record(){}
    public Record(Timestamp date, User user, Street street, String home, String flat, String name, String path){
        this.setDate(date);
        this.setUser(user);
        this.setStreet(street);
        this.setHome(home);
        this.setFlat(flat);
        this.setName(name);
        this.setPath(path);
    }


    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat(
                "MM-dd-yyyy HH:mm:ss ");
        return "Record{" +
                "id=" + id +
                ", date=" + sdf.format(date) +
                ", user=" + user +
                ", street=" + street +
                ", home='" + home + '\'' +
                ", flat='" + flat + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
