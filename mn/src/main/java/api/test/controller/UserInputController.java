package api.test.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.google.gson.Gson;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.validation.Validated;

import com.google.gson.GsonBuilder;

//import steamdom.master.adapter.HibernateTypeProxyAdapter;



import api.test.model.UserInput;

import api.test.repository.UserInputRepository;

import api.test.repository.UserInputInterface;


@Validated
@Controller("/user_input")
public class UserInputController{

    private UserInputRepository repository;
    final GsonBuilder gsonBuilder = new GsonBuilder();
    private final Gson gson;

    public UserInputController(UserInputRepository repository) {
        this.repository = repository;
      //  gsonBuilder.registerTypeAdapterFactory(HibernateTypeProxyAdapter.FACTORY);
        gson = gsonBuilder.create();
    }

    @Get(processes = MediaType.APPLICATION_JSON)
    public String index(){
        final HashMap<String, Object> data = new HashMap<>();
        final List<UserInput> userInput = repository.findAll();
        if(userInput.size() > 0) {
            data.put("status", "ok");
            data.put("message", "data Standard user input berhasil ditampilkan");
            data.put("data", userInput);
            return gson.toJson(data);  
        } else {
            data.put("status", "error");
            data.put("message", "data tidak ada");
            return gson.toJson(data);
        }
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String save(@Body final UserInput userInput){
        final HashMap<String, Object> data = new HashMap<>();
        Long result = repository.save(userInput);
        if (result != null) {
            data.put("status", "ok");
            data.put("id", result);
        } else {
            data.put("status", "fail");
        }
        return gson.toJson(data);
    }

    @Get("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String show(@PathVariable @Nullable final Long id){
        return gson.toJson(repository.findById(id));
    }

    @Put("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String update(@PathVariable @Nullable final Long id, @Body final UserInput userInput){
        final HashMap<String, Object> data = new HashMap<>();
        if (repository.update(id, userInput.getUserName(),userInput.getUserPassword())) {
            data.put("status", "ok");
        }else{
            data.put("status", "fail");
        }
        return gson.toJson(data);
    }

    @Delete("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String destroy(@PathVariable @Nullable final Long id){
        final HashMap<String, Object> data = new HashMap<>();
        if (repository.destroy(id)) {
            data.put("status", "ok");
        }else{
            data.put("status", "fail");
        }
        return gson.toJson(data);
    }

}