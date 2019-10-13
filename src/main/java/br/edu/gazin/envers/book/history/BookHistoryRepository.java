package br.edu.gazin.envers.book.history;

import java.util.List;
import java.util.UUID;

public interface BookHistoryRepository {

	List<BookHistory> listBookRevisions(UUID bookId);

}
