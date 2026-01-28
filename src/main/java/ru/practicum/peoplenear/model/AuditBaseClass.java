package ru.practicum.peoplenear.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditBaseClass {
    @NotNull
    @Column(name = "creation_ts", nullable = false)
    private ZonedDateTime creationTs = ZonedDateTime.now();
    @NotNull
    @Column(name = "edit_ts", nullable = false)
    private ZonedDateTime editTs = ZonedDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.editTs = ZonedDateTime.now();
    }
}

