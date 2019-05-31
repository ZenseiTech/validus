package com.zensei.validus.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Created")
    private Date created;

    @Column(name = "LastModified")
    private Date lastModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseModel baseModel = (BaseModel) o;
        return Objects.equals(id, baseModel.id) &&
                Objects.equals(created, baseModel.created) &&
                Objects.equals(lastModified, baseModel.lastModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, lastModified);
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "id=" + id +
                ", created=" + created +
                ", lastModified=" + lastModified +
                '}';
    }
}
