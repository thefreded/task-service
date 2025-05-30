package com.freded.task.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskFileEntity {

    @Id
    private String id;


    private LocalDateTime createdAt;
    private String fileName;
    private String fileType;

    private String uploadedBy;

    @ManyToOne
    @JoinColumn(name = "task_id",   nullable = false)
    @JsonIgnore
    private TaskEntity task;
}
