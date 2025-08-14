package com.Newnop.Book_Rental_Management_System.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class PersistedObject implements Serializable {

    @Id
    @SequenceGenerator(name = "id_generator", sequenceName = "id_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    private Long id;

    @Version
    @JsonIgnore
    private long version;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date lastUpdate;

    public PersistedObject(long version, Date createdAt, Date lastUpdate) {
        this.version = version;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = lastUpdate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdate = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersistedObject)) return false;
        PersistedObject that = (PersistedObject) o;
        return id.equals(that.id) &&
                version == that.version &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, createdAt);
    }
}
