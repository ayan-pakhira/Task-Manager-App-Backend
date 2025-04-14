package com.example.taskManagerAppBackend.Task.Manager.App.Entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Data
@Document(collection = "tasks")
public class Task {

    @Id
    private ObjectId id;

    @NonNull
    private String taskTitle;

    @NonNull
    private String taskContent;
}
