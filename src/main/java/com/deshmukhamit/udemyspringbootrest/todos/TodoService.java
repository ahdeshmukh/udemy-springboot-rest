package com.deshmukhamit.udemyspringbootrest.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoService {

    @Autowired private TodoRepository todoRepository;

    private static List<TodoBak> todos = new ArrayList<>();
    private static int idCounter = 0;

    static {
        todos.add(new TodoBak(++idCounter, "john.doe", "Wake Up", new Date(), false));
        todos.add(new TodoBak(++idCounter, "john.doe", "Exercise", new Date(), false));
        todos.add(new TodoBak(++idCounter, "john.doe", "Eat Breakfast", new Date(), false));
        todos.add(new TodoBak(++idCounter, "john.doe", "Shower", new Date(), false));
    }

    public List<TodoBak> findAll() {
        /*try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            return todos;
        }*/

        return todos;
    }

    public TodoBak deleteById(long id) {
        /*try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            return null;
        }*/

        TodoBak todo = findById(id);
        if(todo == null) return null;
        if(todos.remove(todo)) {
            return todo;
        }
        return null;
    }

    public TodoBak findById(long id) {
        for(TodoBak todo: todos) {
            if(todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }

    public TodoBak save(TodoBak todo) {
        if(todo.getId() == -1 || todo.getId() == 0) {
            todo.setId(++idCounter);
            todos.add(todo);
        } else {
            deleteById(todo.getId());
            todos.add(todo);
        }

        return todo;
    }

    public List<Todo> findByUserId(int uid) {
        return todoRepository.findByUserId(uid);
    }
}
