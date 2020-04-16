package api.test.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import java.util.Date;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import api.test.model.UserInput;
import api.test.repository.UserInputInterface;


@Singleton
public class UserInputRepository implements UserInputInterface{

    @PersistenceContext
    private EntityManager entityManager;

    public UserInputRepository(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserInput> findAll() {
        TypedQuery<UserInput> query = entityManager
            .createQuery("from UserInput", UserInput.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Long save(@NotNull UserInput userInput) {
        try {
            entityManager.persist(userInput);
            return userInput.getId();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Long size() {
        return entityManager.createQuery("select count(*) from UserInput", Long.class).getSingleResult();
    }

    @Transactional(readOnly = true)
    @Override
    public UserInput findById(@NotNull Long id) {
        return entityManager.find(UserInput.class, id);
    }

    @Transactional
    @Override
    public boolean update(@NotNull Long id, String user_name, String user_password) {
        try {
            UserInput userInput = entityManager.find(UserInput.class, id);
            if(user_name != null) userInput.setUserName(user_name);
            if(user_password !=null) userInput.setUserPassword(user_password);
            userInput.setUpdatedAt(new Date());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    @Override
    public boolean destroy(@NotNull Long id) {
        try {
            UserInput userInput = entityManager.find(UserInput.class, id);
            entityManager.remove(userInput);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    
}

