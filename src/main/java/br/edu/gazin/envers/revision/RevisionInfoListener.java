package br.edu.gazin.envers.revision;

import java.util.Optional;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class RevisionInfoListener implements RevisionListener {
	public final static String DEFAULT_USERNAME = "no-user";

	@Override
	public void newRevision(Object revisionEntity) {
		RevisionInfo rev = (RevisionInfo) revisionEntity;
		String username = Optional.of(SecurityContextHolder.getContext().getAuthentication().getName())
				.orElse(DEFAULT_USERNAME);
		rev.setUsername(username);
	}

}
