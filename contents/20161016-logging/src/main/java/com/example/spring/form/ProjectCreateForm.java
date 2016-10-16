package com.example.spring.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class ProjectCreateForm {
    @NotEmpty
    private String name;
}
