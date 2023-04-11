package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dbo.basemoney", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class BaseMoney {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "float default 1432000")
    private Float baseMoney;

    public BaseMoney() {

    }

    public BaseMoney(Float money) {
        this.baseMoney = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getBaseMoney() {
        return baseMoney;
    }

    public void setId(Float baseMoney) {
        this.baseMoney = baseMoney;
    }
}
