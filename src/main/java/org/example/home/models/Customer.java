package org.example.home.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.home.views.Views;
import org.hibernate.annotations.View;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Admin.class})
    private int id;

    @NotEmpty
    @Length(min = 5, max = 12, message = "The field must be at least 5 characters")
    @JsonView({Views.Admin.class, Views.Client.class})
    private String name;

    public Customer(String name) {
        this.name = name;
    }
}
