package com.example.baitap.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(schema = "baitap")
public class Certificate {
    @Id
    private String id;
    private String certificationName;
    private double cost;
    private String category;


}
