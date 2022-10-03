package org.meglab.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Description {

    @JsonProperty
    private Integer id;
    @JsonProperty
    private String term;
    @JsonProperty
    private String description;
    @JsonProperty
    private String links;

    public Integer getId() {
        return id;
    }

    public Description setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTerm() {
        return term;
    }

    public Description setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Description setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getLinks() {
        return links;
    }

    public Description setLinks(String links) {
        this.links = links;
        return this;
    }
}
