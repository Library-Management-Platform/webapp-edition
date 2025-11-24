package com.platform.libraryManager.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("CLIENT")
@Table(name = "clients")
public class Client extends User { }


