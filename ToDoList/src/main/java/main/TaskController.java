package main;

import main.model.Task;
import main.model.TaskRepository;
import main.model.TaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping("/")
    public String index() {
        return (new Date().toString());
    }

    @GetMapping("/tasks/")
    public List<Task> list() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> taskList = new ArrayList<>();
        taskIterable.forEach(taskList::add);
        return taskList;
    }

    @PostMapping(value = "/tasks/", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity add(@RequestBody TaskRequest taskRequest) {
        if (taskRequest.title() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//            return EntityModel.of(HttpStatus.BAD_REQUEST);
        }
        Task task = new Task(taskRequest.title(), taskRequest.description());
        Task newTask = taskRepository.save(task);
//        ToDo: посмотреть как правильно подставлять URI
        return ResponseEntity.created(URI.create("/tasks/" + Integer.toString( newTask.getId())))
                .body(null);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable @NonNull int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity patch(@PathVariable int id, @RequestBody TaskRequest taskRequest) {
        if (taskRequest.title() == null && taskRequest.isDone() == null && taskRequest.description() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Task task = optionalTask.get();
        if (taskRequest.title() != null) {
            task.setTitle(taskRequest.title());
        }
        if (taskRequest.description() != null) {
            task.setDescription(taskRequest.description());
        }
        if (taskRequest.isDone() != null) {
            task.setDone(taskRequest.isDone());
        }
        taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("tasks/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Task task = optionalTask.get();
        taskRepository.delete(task);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
