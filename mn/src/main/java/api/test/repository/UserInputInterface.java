package api.test.repository;


import java.util.List;
import javax.validation.constraints.NotNull;
import api.test.model.UserInput;


public interface UserInputInterface{

    Long size();
    List<UserInput> findAll(int page, int limit);
    UserInput findById(@NotNull Long id);    
    boolean save(@NotNull UserInput userInput);
    boolean update(@NotNull Long id, String  user_name, String user_password);
    boolean destroy(@NotNull Long id);




}