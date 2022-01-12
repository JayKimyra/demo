package com.example.demo.hibernate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "records")
public class Record {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATETIME")
    private Date date;
    @Column
    private Long userId;
    @Column
    private Long streetId;
    @Column
    private String name;
    @Column
    private String path;

    public Record(){}
    public Record(Date date, Long userId, Long streetId, String name, String path){
        this.setDate(date);
        this.setUserId(userId);
        this.setStreetId(streetId);
        this.setName(name);
        this.setPath(path);
    }


    @Override
    public String toString() {
        return "FileEntity{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", userId=" + userId +
                ", streetId=" + streetId +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
