package br.edu.gazin.envers.revision;

import java.util.Optional;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserRevisionListener implements RevisionListener {
    public final static String DEFAULT_USERNAME = "Suay";
    
    
    @Override
    public void newRevision(Object revisionEntity) {
        UserRevEntity rev = (UserRevEntity) revisionEntity;
        String username = Optional.of(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(DEFAULT_USERNAME);
        rev.setUsername(username);
    }


}
