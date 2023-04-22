package net.javaguides.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dbo.hokhau", uniqueConstraints = @UniqueConstraint(columnNames = "cmnd"))
public class HoKhau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cmnd;
    private String relateID;

    public HoKhau() {

    }

    public HoKhau(Long id, String cmnd, String relateID) {
        this.id = id;
        this.cmnd = cmnd;
        this.relateID = relateID;
    }

    public Long getId() {
        return id;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getRelateID() {
        return relateID;
    }

    public void setRelateID(String relateID) {
        this.relateID = relateID;
    }

}
