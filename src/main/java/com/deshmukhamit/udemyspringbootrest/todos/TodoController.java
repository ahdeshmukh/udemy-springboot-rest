package com.deshmukhamit.udemyspringbootrest.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    @Autowired
    private TodoService todoService;

//    @GetMapping("/users/{username}/todos")
//    public List<TodoBak> getAllTodos(@PathVariable String username) {
//        return todoService.findAll();
//    }

    @GetMapping("/users/{username}/todos/{id}")
    public TodoBak getTodoById(@PathVariable String username, @PathVariable long id) {
        return todoService.findById(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
        TodoBak todo = todoService.deleteById(id);
        if(todo != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<TodoBak> updateTodo(@PathVariable String username,
                                              @PathVariable long id,
                                              @RequestBody TodoBak todo) {

        todoService.save(todo);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PostMapping("/users/{username}/todos")
    public ResponseEntity<Void> addTodo(@PathVariable String username,
                                           @RequestBody TodoBak todo) {
        // Best REST practices - on POST, send the URL of the newly created resource
        // to get the URL of new resource - get current URL and append id of the newly created todo
        // eg: /users/{username}/todos/{id}

        TodoBak createdTodo = todoService.save(todo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    @GetMapping("/users/{uid}/todos")
    public List<Todo> getAllTodos(@PathVariable int uid) {
        return todoService.findByUserId(uid);
    }

}
