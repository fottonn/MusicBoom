package ru.bugmakers.entity.auth;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Ivan
 */
@Entity
@Table(name = "fb_auth")
public class FbAuth extends SocialAuth{
}
