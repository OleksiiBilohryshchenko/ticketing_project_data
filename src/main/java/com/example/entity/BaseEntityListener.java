package com.example.entity;

import com.example.entity.common.UserPrincipal;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;


@Component
public class BaseEntityListener extends AuditingEntityListener {


    @PrePersist
    private void onPrePersist(BaseEntity baseEntity){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        baseEntity.setInsertDateTime(LocalDateTime.now());
        baseEntity.setLastUpdateDateTime(LocalDateTime.now());

        if (authentication != null && !authentication.getName().equals("anonymousUser")){ // valid user
            Object principal = authentication.getPrincipal();
            baseEntity.setInsertUserId( ((UserPrincipal) principal).getId()); // get id from logged user id (mapped user)
            baseEntity.setLastUpdateUserId( ((UserPrincipal) principal).getId());
        }
    }

    @PreUpdate
    private void onPreUpdate(BaseEntity baseEntity){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        baseEntity.setLastUpdateDateTime(LocalDateTime.now());

        if (authentication != null && !authentication.getName().equals("anonymousUser")){ // valid user
            Object principal = authentication.getPrincipal();
            baseEntity.setLastUpdateUserId( ((UserPrincipal) principal).getId());
        }

    }

}
