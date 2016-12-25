package com.example.spring.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "bank", type = "account")
@Data
public class Account {
    @Id
    @JsonProperty("account_number")
    private Integer accountNumber;
    private Integer balance;
    private String firstname;
    private String lastname;
    private Integer age;
    private String gender;
    private String address;
    private String employer;
    private String email;
    private String city;
    private String state;
}
