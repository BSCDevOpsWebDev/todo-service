package at.dergeorg.todoservice.data;

import at.dergeorg.todoservice.model.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TodoRepo extends CrudRepository<Todo, Integer> {

    @Override
    Optional<Todo> findById(Integer integer);
}
