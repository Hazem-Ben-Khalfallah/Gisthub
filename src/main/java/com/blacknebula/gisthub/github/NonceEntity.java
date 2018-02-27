package com.blacknebula.gisthub.github;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author hazem
 */

@Document(collection = "nonce")
public class NonceEntity {
    @Id
    private String id;

    private Date creationDate;

    public NonceEntity() {
    }

    private NonceEntity(Builder builder) {
        setId(builder.id);
        setCreationDate(builder.creationDate);
    }

    static Builder newBuilder() {
        return new Builder();
    }

    String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    Date getCreationDate() {
        return creationDate;
    }

    void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    static final class Builder {
        private String id;
        private Date creationDate;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder creationDate(Date creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public NonceEntity build() {
            return new NonceEntity(this);
        }
    }
}
