package br.edu.gazin.envers.revision;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import org.springframework.data.annotation.LastModifiedBy;

@Entity
@Table(name = "revinfo", schema = "envers_schema")
@RevisionEntity(RevisionInfoListener.class)
//@EntityListeners(AuditingEntityListener.class)
public class RevisionInfo implements Serializable {
	private static final long serialVersionUID = 210798494749422L;

	@Id
	@GeneratedValue
	@RevisionNumber
	@Column(name = "revision_number")
	private int revisionNumber;

	@RevisionTimestamp
	@Column(name = "revision_timestamp")
	private long revtstmp;

	@LastModifiedBy
	@Column(name = "user_name")
	private String username;

	public int getRevisionNumber() {
		return revisionNumber;
	}

	public long getRevtstmp() {
		return revtstmp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Transient
	public LocalDateTime getRevisionDate() {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(revtstmp), TimeZone.getDefault().toZoneId());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof DefaultRevisionEntity)) {
			return false;
		}

		final RevisionInfo that = (RevisionInfo) o;
		return revisionNumber == that.revisionNumber && revtstmp == that.revtstmp;
	}

	@Override
	public int hashCode() {
		int result;
		result = revisionNumber;
		result = 31 * result + (int) (revtstmp ^ (revtstmp >>> 32));
		return result;
	}

}