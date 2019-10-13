package br.edu.gazin.envers.revision;

import org.hibernate.envers.RevisionType;

public class AuditQueryResultUtils {

	private AuditQueryResultUtils() {
	}

	public static <TTargetType> AuditQueryResult<TTargetType> getAuditQueryResult(Object[] item,
			Class<TTargetType> type) {

		// Early exit, if no item given:
		if (item == null) {
			return null;
		}

		// Early exit, if there is not enough data:
		if (item.length < 3) {
			return null;
		}

		// Cast item[0] to the Entity:
		TTargetType entity = null;
		if (type.isInstance(item[0])) {
			entity = type.cast(item[0]);
		}

		// Then get the Revision Entity:
		RevisionInfo revision = null;
		if (item[1] instanceof RevisionInfo) {
			revision = (RevisionInfo) item[1];
		}

		// Then get the Revision Type:
		RevisionType revisionType = null;
		if (item[2] instanceof RevisionType) {
			revisionType = (RevisionType) item[2];
		}

		// Build the Query Result:
		return new AuditQueryResult<TTargetType>(entity, revision, revisionType);
	}

}