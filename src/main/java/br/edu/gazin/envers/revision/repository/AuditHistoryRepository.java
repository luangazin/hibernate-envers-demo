package br.edu.gazin.envers.revision.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

public interface AuditHistoryRepository<T> {

	List<AuditHistory<T>> listEntityRevisions(UUID entityId, Pageable pageable, Class<T> clazz);

}
