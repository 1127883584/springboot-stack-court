package com.tw.apistackbase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CaseInfo {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String subjective;

    @Column(nullable = false)
    private String objective;

    public CaseInfo(String subjective, String objective) {
        this.subjective = subjective;
        this.objective = objective;
    }

    public CaseInfo() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjective() {
        return subjective;
    }

    public void setSubjective(String subjective) {
        this.subjective = subjective;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }
}
