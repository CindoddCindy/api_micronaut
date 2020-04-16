package api.test.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;




@Entity
@Table(name = "user_input")
public class UserInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "input name")
    @Column(name = "user_name")
    private String user_name;

    @NotNull(message = " input password ")
    @Column(name = "user_password")
    private String user_passowrd;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "deleted_at", nullable = true)
    private Date deleted_at;


    public void setId(Long id){
        this.id=id;
    }

    public Long getId(){
        return id;
    }

    public void setUserName(String user_name){
        this.user_name=user_name;
    }

    public String getUserName(){
        return user_name;
    }

    public void setUserPassword(String user_password){
        this.user_passowrd=user_passowrd;
    }

    public String getUserPassword(){
        return user_passowrd;
    }

    public void setCreatedAt(Date created_at){
        this.created_at=created_at;
    }

    public Date getCreatedAt(){
        return created_at=created_at;
    }

    public void setUpdatedAt(Date updated_at){
        this.updated_at=updated_at;
    }

    public Date getUpdatedAt(){
        return updated_at;
    }

    public void setDeletedAt(Date deleted_at){
        this.deleted_at=deleted_at;
    }

    public Date getDeletedAt(){
        return deleted_at;
    }




}