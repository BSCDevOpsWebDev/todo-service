package at.dergeorg.todoservice.controller;

import at.dergeorg.todoservice.data.TodoRepo;
import at.dergeorg.todoservice.model.StateEnum;
import at.dergeorg.todoservice.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "*")
public class restController {

    @Autowired
    private TodoRepo repo;

    @GetMapping("/todo/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable("id") int id){
        Optional<Todo> ret = repo.findById(id);
        return ret.map(ResponseEntity::ok).orElseGet(() -> (ResponseEntity<Todo>) ResponseEntity.notFound());
    }

    @PostMapping("/test")
    public void generateTestData(){
        ArrayList<Todo> newTodos = new ArrayList<>();
        Todo nTodo = new Todo("Test 1", "Das ist ein Test", StateEnum.DOING);
        nTodo.setDateSting("1644415439976");
        newTodos.add(nTodo);
        nTodo = new Todo("Test 2", "Das ist ein Test 2", StateEnum.DOING);
        nTodo.setDateSting("1644415439976");
        newTodos.add(nTodo);
        nTodo = new Todo("Test 3", "Das ist ein Test 3", StateEnum.DONE);
        nTodo.setDateSting("1644415439976");
        newTodos.add(nTodo);
        nTodo = new Todo("Test 4", "Das ist ein Test 4", StateEnum.DONE);
        nTodo.setDateSting("1644415439976");
        newTodos.add(nTodo);
        nTodo = new Todo("Test 5", "Das ist ein Test 5", StateEnum.TODO);
        nTodo.setDateSting("1644415439976");
        newTodos.add(nTodo);
        nTodo = new Todo("Test 6", "Das ist ein Test 6", StateEnum.TODO);
        nTodo.setDateSting("1644415439976");
        newTodos.add(nTodo);
        repo.saveAll(newTodos);
    }

    @GetMapping("/todos")
    public Iterable<Todo> getTodosFiltered(@RequestParam(required = false) Optional<String> state){
        if(state.isPresent()){
            StateEnum stateEnum = StateEnum.valueOf(state.get().toUpperCase());
            return repo.findByState(stateEnum);
        }else{
            return repo.findAll();
        }
    }

    @PostMapping("/todo")
    public void addTodo(@RequestParam(value="name") String name, @RequestParam(value="description") String description, @RequestParam(value = "date") String date, @RequestParam(value = "state") String state){
        Todo newTodo = new Todo(name, description, StateEnum.valueOf(state.toUpperCase()));
        newTodo.setDateSting(date);
        repo.save(newTodo);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteTodo(@PathVariable("id") int id){
        repo.deleteById(id);
    }

    @PutMapping("/todo/{id}")
    public void editTodo(@PathVariable("id") int id, @RequestParam(value="name") String name, @RequestParam(value="description") String description, @RequestParam(value = "date") String date, @RequestParam(value = "state") String state){
        Optional<Todo> optionalToEdit = repo.findById(id);
        Todo toEdit = optionalToEdit.get();
        if(!name.isEmpty()){
            toEdit.setName(name);
        }
        if(!description.isEmpty()){
            toEdit.setDescription(description);
        }
        if(!date.isEmpty()){
            toEdit.setDateSting(date);
        }
        if(!state.isEmpty()){
            toEdit.setState(StateEnum.valueOf(state));
        }
        repo.save(toEdit);
    }
}
