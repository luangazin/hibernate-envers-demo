package br.edu.gazin.envers.revision.repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AuditHistoryRepositoryImpl<T> implements AuditHistoryRepository<T> {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	@Override
	public List<AuditHistory<T>> listEntityRevisions(UUID entityId, Pageable pageable, Class<T> clazz) {
        // Create the Audit Reader. It uses the EntityManager, which will be opened when
        // starting the new Transation and closed when the Transaction finishes.
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        // Create the Query:
        AuditQuery auditQuery = auditReader.createQuery()
                .forRevisionsOfEntity(clazz, false, true)
                .add(AuditEntity.id().eq(entityId))
                .addOrder(AuditEntity.revisionNumber().asc())
//                .setFirstResult(pageNumber * pageSize)
//                .setMaxResults(pageSize)
                ;
//        final Class<T> clazz = getClass();
        // We don't operate on the untyped Results, but cast them into a List of AuditQueryResult:
        return AuditQueryUtils.getAuditQueryResults(auditQuery, clazz).stream()
                // Turn into the BookHistory Domain Object:
                .map(auditQueryResult -> new AuditHistory<T>(
                        auditQueryResult.getEntity(),
                        auditQueryResult.getRevision().getRevisionNumber(),
                        auditQueryResult.getType(),
                        auditQueryResult.getRevision().getUsername()
                ))
                // And collect the Results:
                .collect(Collectors.toList());
    }
}
