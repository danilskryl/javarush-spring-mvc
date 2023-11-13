package org.danilskryl.javarush.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "task", schema = "todo")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "description", length = 100, nullable = false)
    private String description;
    @Column(name = "status", nullable = false, columnDefinition = "tinyint")
    @Enumerated(EnumType.ORDINAL)
    private Status status;
}
