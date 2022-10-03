package org.meglab.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Sequence {

    @JsonProperty
    private Integer id;
    @JsonProperty
    private String header;
    @JsonProperty
    private String fasta;
    @JsonProperty
    private String type;
    @JsonProperty("class")
    private String theclass;
    @JsonProperty
    private String mechanism;
    @JsonProperty
    private String group;

    public Sequence() {
    }

    public Integer getId() {
        return id;
    }

    public Sequence setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getHeader() {
        return header;
    }

    public Sequence setHeader(String header) {
        this.header = header;
        return this;
    }

    public String getType() {
        return type;
    }

    public Sequence setType(String type) {
        this.type = type;
        return this;
    }

    public String getFasta() {
        return fasta;
    }

    public Sequence setFasta(String fasta) {
        this.fasta = fasta;
        return this;
    }

    public String getTheclass() {
        return theclass;
    }

    public Sequence setTheclass(String theclass) {
        this.theclass = theclass;
        return this;
    }

    public String getMechanism() {
        return mechanism;
    }

    public Sequence setMechanism(String mechanism) {
        this.mechanism = mechanism;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public Sequence setGroup(String group) {
        this.group = group;
        return this;
    }
}
