package com.platform.libraryManager.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("ADMIN")
@Table(name = "admins")
public class Admin extends User { }
