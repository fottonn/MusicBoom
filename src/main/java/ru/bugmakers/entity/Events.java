package ru.bugmakers.entity;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by Ayrat on 14.11.2017.
 */
@Entity
@Table(name="EVENTS")
public class Events {
    @Id
    private Long events_id;
}
