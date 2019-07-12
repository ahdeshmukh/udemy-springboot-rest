package com.deshmukhamit.udemyspringbootrest.todos;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();
    private static int idCounter = 0;

    static {
        todos.add(new Todo(++idCounter, "john.doe", "Wake Up", new Date(), false));
        todos.add(new Todo(++idCounter, "john.doe", "Exercise", new Date(), false));
        todos.add(new Todo(++idCounter, "john.doe", "Eat Breakfast", new Date(), false));
        todos.add(new Todo(++idCounter, "john.doe", "Shower", new Date(), false));
    }

    public List<Todo> findAll() {
        /*try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            return todos;
        }*/

        return todos;
    }

    public Todo deleteById(long id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            return null;
        }

        Todo todo = findById(id);
        if(todo == null) return null;
        if(todos.remove(todo)) {
            return todo;
        }
        return null;
    }

    public Todo findById(long id) {
        for(Todo todo: todos) {
            if(todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }

    public Todo save(Todo todo) {
        if(todo.getId() == -1 || todo.getId() == 0) {
            todo.setId(++idCounter);
            todos.add(todo);
        } else {
            deleteById(todo.getId());
            todos.add(todo);
        }

        return todo;
    }
}
