package com.shortcut.todo.entity;

import com.shortcut.todo.entity.validator.annotation.ValidTaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "TODO_TASK", schema = "public")
@Data
@Schema(description = "Задача")
public class TodoTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "T_ID", nullable = false)
    private Long id;

    @Column(name = "T_NAME", nullable = false)
    @Schema(description = "Название задачи")
    private String name;

    @Column(name = "T_DESCRIPTION")
    @Schema(description = "Описание задачи")
    private String description;

    @Column(name = "T_CREATE_DATE", updatable = false)
    @Schema(description = "Дата создания задачи")
    private LocalDateTime createDate;

    @Column(name = "T_UPDATE_DATE")
    @Schema(description = "Дата обновления задачи")
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "CTS_ID")
    @ValidTaskStatus
    @Schema(description = "Текущий статус задачи")
    private ClsTaskStatus clsTaskStatus;

}
