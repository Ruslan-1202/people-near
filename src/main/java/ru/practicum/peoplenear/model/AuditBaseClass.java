package ru.practicum.peoplenear.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public class AuditBaseClass {
    @NotNull
    @Column(name = "creation_ts", nullable = false, updatable = false)
    private ZonedDateTime creationTs = ZonedDateTime.now();
    @Column(name = "edit_ts", insertable = false)
    private ZonedDateTime editTs;

    @PreUpdate
    public void preUpdate() {
        this.editTs = ZonedDateTime.now();
    }
}

