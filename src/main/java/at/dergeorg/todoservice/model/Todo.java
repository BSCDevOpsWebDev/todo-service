package at.dergeorg.todoservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("name")
    private String name;
    @NonNull
    @Column(name = "description")
    @JsonProperty("description")
    private String description;
    @Id
    @GeneratedValue
    @JsonProperty("id")
    private int id;
    @Column(name = "date")
    @JsonProperty("date")
    private Date date;
    @NonNull
    @Column(name = "state")
    @JsonProperty("state")
    private StateEnum state;

    public void setDateSting(String date){
        Date dDate = new Date();
        dDate.setTime(Long.parseLong(date));
        this.date = dDate;
    }
}
