package at.dergeorg.todoservice.model;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@NoArgsConstructor
public class Todo {
    @NonNull
    @Column(name = "name")
    private String name;
    @NonNull
    @Column(name = "description")
    private String description;
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "date")
    private Date date;
    @NonNull
    @Column(name = "state")
    private StateEnum state;

    public void setDateSting(String date){
        Date dDate = new Date();
        dDate.setTime(Long.parseLong(date));
        this.date = dDate;
    }
}
