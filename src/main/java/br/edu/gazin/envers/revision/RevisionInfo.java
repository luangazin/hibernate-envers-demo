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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import org.springframework.data.annotation.LastModifiedBy;

@Entity
@Table(name = "\"RevisionInfo\"")
@RevisionEntity(RevisionInfoListener.class)
public class RevisionInfo implements Serializable {
	private static final long serialVersionUID = 210798494749422L;

    @Id
    @GeneratedValue(generator = "RevisionNumberSequenceGenerator")
    @GenericGenerator(name = "RevisionNumberSequenceGenerator", strategy = "org.hibernate.envers.enhanced.OrderedSequenceGenerator", parameters = {
            @Parameter(name = "table_name", value = "revinfo"),
            @Parameter(name = "sequence_name", value = "revision_generator_seq"),
            @Parameter(name = "initial_value", value = "1"), 
            @Parameter(name = "increment_size", value = "1") })
    @RevisionNumber
    @Column(name = "revision_number")
    private long revisionNumber;

    @RevisionTimestamp
    @Column(name = "revision_timestamp")
    private long revtstmp;

    @LastModifiedBy
    @Column(name = "user_name")
    private String username;

    public long getRevisionNumber() {
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
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (revisionNumber ^ (revisionNumber >>> 32));
        result = prime * result + (int) (revtstmp ^ (revtstmp >>> 32));
        return result;
    }
	
}