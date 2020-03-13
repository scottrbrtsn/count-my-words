package com.scottrbrtsn.wordcount.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logs")
@Data
@AllArgsConstructor
public class Phrase {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column
    private long id;

    @Column
    private String phrase;

}
