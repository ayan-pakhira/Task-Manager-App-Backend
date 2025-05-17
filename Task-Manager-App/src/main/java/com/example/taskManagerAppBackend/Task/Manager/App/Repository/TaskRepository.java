package com.example.taskManagerAppBackend.Task.Manager.App.Repository;

import com.example.taskManagerAppBackend.Task.Manager.App.Entity.Task;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task, ObjectId> {

    //Task findByTitle(String taskTitle);
}
