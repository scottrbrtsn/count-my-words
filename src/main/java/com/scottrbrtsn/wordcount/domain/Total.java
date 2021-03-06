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
@Table(name = "totals")
@Data
public class Total {

    @Id
    @Column
    private String id;

    @Column
    private int total;


}
