package api.test.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import java.util.List;
import java.util.Date;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import api.test.model.UserInput;
import api.test.repository.UserInputInterface;


@Singleton
public class UserInputRepository implements UserInputInterface{

   
    @PersistenceContext
    private EntityManager manager;

    public UserInputRepository(@CurrentSession EntityManager manager) {
        this.manager = manager;
    }

    @Override
    @Transactional(readOnly = true)
    public Long size() {
        Long count = manager.createQuery("select count(*) from UserInput where deleted_at IS NULL", Long.class).getSingleResult();
        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserInput> findAll(int page, int limit) {
        TypedQuery<UserInput> query = manager
                .createQuery("from UserInput where deleted_at IS NULL", UserInput.class)
                .setFirstResult(page > 1 ? page * limit - limit : 0)
                .setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserInput findById(@NotNull Long id) {
        UserInput query = manager.find(UserInput.class, id);
        return query;
    }


    @Override
    @Transactional
    public boolean save(@NotNull UserInput userInput){
        try {
            manager.persist(userInput);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }

    @Override
    @Transactional 
    public boolean update(@NotNull Long id, String user_name, String user_password){
        try {
            UserInput c = manager.find(UserInput.class, id);
            if (user_name.equals("-")==false) c.setUserName(user_name);
            if (user_password.equals("-")==false) c.setUserPassword(user_password);
            c.setUpdatedAt(new Date());
            return true;
        } catch (Exception e){
            return false;
        }
            
    }

    @Override
    @Transactional
    public boolean destroy(@NotNull Long id){
        try {
            UserInput c = manager.find(UserInput.class, id);
            c.setDeletedAt(new Date());
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
   
}

