package com.shortcut.todo.entity.classifier;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum TaskStatusClassifier {
    NEW(1, "Новая"),
    IN_PROGRESS(2, "В процессе"),
    FINISHED(3, "Закончена");

    @Id
    @Column(name = "CTS_ID")
    private Integer id;

    private String name;

    TaskStatusClassifier(Integer id) {
        this.id = id;
    }

    TaskStatusClassifier(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Optional<TaskStatusClassifier> fromId(int id) {
        return Arrays.asList(values())
                .stream()
                .filter(status -> status.getId().equals(id))
                .findFirst();

    }
}
