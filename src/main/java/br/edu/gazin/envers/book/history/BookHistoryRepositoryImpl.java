package br.edu.gazin.envers.book.history;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.edu.gazin.envers.book.Book;
import br.edu.gazin.envers.revision.AuditQueryResult;
import br.edu.gazin.envers.revision.AuditQueryUtils;

@Component
public class BookHistoryRepositoryImpl<T> implements BookHistoryRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	@Override
	public List<BookHistory> listBookRevisions(UUID bookId) {

        // Create the Audit Reader. It uses the EntityManager, which will be opened when
        // starting the new Transation and closed when the Transaction finishes.
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        int pageNumber = 2;
        int pageSize = 2;
        // Create the Query:
        AuditQuery auditQuery = auditReader.createQuery()
                .forRevisionsOfEntity(Book.class, false, true)
                .add(AuditEntity.id().eq(bookId))
                .addOrder(AuditEntity.revisionNumber().asc())
                .setFirstResult(pageNumber * pageSize)
                .setMaxResults(pageSize)
                ;

        // We don't operate on the untyped Results, but cast them into a List of AuditQueryResult:
        return AuditQueryUtils.getAuditQueryResults(auditQuery, Book.class).stream()
                // Turn into the BookHistory Domain Object:
                .map(x -> getBookHistory(x))
                // And collect the Results:
                .collect(Collectors.toList());
    }

    private static BookHistory getBookHistory(AuditQueryResult<Book> auditQueryResult) {
        return new BookHistory(
                auditQueryResult.getEntity(),
                auditQueryResult.getRevision().getRevisionNumber(),
                auditQueryResult.getType(),
                auditQueryResult.getRevision().getUsername()
        );
    }
}
