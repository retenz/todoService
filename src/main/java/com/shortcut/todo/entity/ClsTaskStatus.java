package com.shortcut.todo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "CLS_TASK_STATUS", schema = "public")
@Data
@Schema(description = "Статус выполнения задачи")
public class ClsTaskStatus implements Serializable {

    @Id
    @Column(name = "CTS_ID")
    private Integer id;

    @Column(name = "CTS_NAME")
    @Schema(description = "Наименование статуса")
    private String name;

}
