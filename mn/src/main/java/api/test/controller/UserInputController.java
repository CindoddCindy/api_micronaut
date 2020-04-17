package api.test.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.validation.Validated;
import io.micronaut.http.annotation.*;
import java.util.HashMap;
import java.util.List;

import com.google.gson.*;
import api.test.model.UserInput;

import api.test.repository.UserInputRepository;

import api.test.repository.UserInputInterface;


@Validated
@Controller("/user_input")
public class UserInputController{

    private UserInputInterface repository;

    UserInputController(UserInputInterface repository) {
        this.repository = repository;
    }
    
    @Get(produces = MediaType.APPLICATION_JSON)
    public String index(@QueryValue int page, @QueryValue int limit) {
        final HashMap<String, Object> data = new HashMap<>();
        try {
            final List<UserInput> userInput = repository.findAll(page, limit);
            data.put("page", Math.ceil(repository.size() / limit));
            data.put("status", "ok");
            data.put("message", "Data Classes");
            data.put("data", userInput);
            return (new Gson().toJson(data));
        } catch (Exception e) {
            data.put("status", "error");
            data.put("message", e.getMessage());
            return (new Gson()).toJson(data);
        }
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String show(@PathVariable Long id) {
        return (new Gson()).toJson(repository.findById(id));
    }

    @Post(consumes=MediaType.APPLICATION_JSON)
    public String save(@Body UserInput t) {
        HashMap<String, Object> data = new HashMap<>();
        if (repository.save(t)) {
            data.put("status", "ok");
        } else {
            data.put("status", "fail");
        }
        return (new Gson()).toJson(data);
    }

    @Put(consumes=MediaType.APPLICATION_JSON)
    public String update(@Body UserInput c) {
        HashMap<String, Object> data = new HashMap<>();
        if (repository.update(c.getId(), c.getUserName(), c.getUserPassword())) {
            data.put("status", "ok");
        } else {
            data.put("status", "fail");
        }
        return (new Gson()).toJson(data);
    }

    @Delete("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String destroy(@PathVariable Long id) {
        HashMap<String, Object> data = new HashMap<>();
        if (repository.destroy(id)) {
            data.put("status", "ok");
        } else {
            data.put("status", "fail");
        }
        return (new Gson()).toJson(data);
    }

}