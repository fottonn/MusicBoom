package ru.bugmakers.entity;

import javax.persistence.*;

/**
 * Created by Ayrat on 20.11.2017.
 */
@Entity
@Table(name = "vk_contacts")
public class Vk {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "vk")
    private String vk;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVk() {
        return vk;
    }

    public void setVk(String vk) {
        this.vk = vk;
    }
}
