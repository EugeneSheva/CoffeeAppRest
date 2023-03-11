package com.example.coffeapp.Coffee.Model.Users;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;


@Data
public class UserDTO {
    private  Long id;

    private String username;
    private String password;
    private String name;
    private String language;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @JsonCreator
    public UserDTO() {
    }
    @JsonCreator
    public UserDTO(@JsonProperty("id")Long id, @JsonProperty("username") String username,
                   @JsonProperty("password") String password, @JsonProperty("name") String name,
                   @JsonProperty("language") String language, @JsonProperty("dateOfBirth") Date dateOfBirth) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.language = language;
        this.dateOfBirth = dateOfBirth;
    }

}
