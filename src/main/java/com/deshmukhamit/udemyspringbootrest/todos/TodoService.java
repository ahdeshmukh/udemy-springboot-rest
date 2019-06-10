package com.deshmukhamit.udemyspringbootrest.todos;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.util.concurrent.TimeUnit;

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
}
