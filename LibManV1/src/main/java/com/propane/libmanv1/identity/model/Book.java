package com.propane.libmanv1.identity.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String authors;

    private String isbn;

    private int numberOfCopies;

    private int availableCopies;
}