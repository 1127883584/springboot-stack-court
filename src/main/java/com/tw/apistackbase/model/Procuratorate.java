package com.tw.apistackbase.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Procuratorate {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Prosecutor> prosecutors;

    public Procuratorate(String name, List<Prosecutor> prosecutors) {
        this.name = name;
        this.prosecutors = prosecutors;
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

    public List<Prosecutor> getProsecutors() {
        return prosecutors;
    }

    public void setProsecutors(List<Prosecutor> prosecutors) {
        this.prosecutors = prosecutors;
    }
}
