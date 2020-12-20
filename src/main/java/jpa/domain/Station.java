package jpa.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "station", indexes = {
        @Index(name = "UK_station_name", columnList = "name", unique = true)
})
@Audited
@AuditTable("station_audit")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 20)
    private String name;

    // TODO ManyToMany 사용법 확인 => 추후 변경 해야 함
    @NotAudited
    @ManyToMany
    @JoinTable(name = "station_line", joinColumns = @JoinColumn(name = "station_id"), inverseJoinColumns = @JoinColumn(name = "line_id"))
    private List<Line> lines = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    // TODO 문제 발생하는 것을 확인하기 위해 private 선언
    private Station() {
    }

    private Station(String name) {
        this.name = name;
    }

    public static Station of(String name) {
        return new Station(name);
    }

    public void addLine(Line line) {
        this.lines.add(line);
    }

    public List<Line> getLines() {
        return Collections.unmodifiableList(lines);
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lines=" + lines +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

}
