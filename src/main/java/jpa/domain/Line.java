package jpa.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "line", indexes = {@Index(name = "UK_line_name", columnList = "name", unique = true)})
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "color", length = 20, nullable = false)
    private String color;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    private Line(String color,
                 String name) {
        this.color = color;
        this.name = name;
    }

    public static Line of(String color,
                          String name) {
        return new Line(color, name);
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

}
