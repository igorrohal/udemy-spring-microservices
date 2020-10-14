package com.irohal.springmicroservices.restful.helloworld;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// @JsonIgnoreProperties("field1") // static filtering, not so good, as fields are hardcoded :/
@JsonFilter("SomeBeanFilter")
public class SomeBeanForFiltering {

    private String field1;
    private String field2;
    // @JsonIgnore // static filtering, in addition to what's already ignore by @JsonIgnoreProperties, better, as fields not hardcoded
    private String field3;

    public SomeBeanForFiltering(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }
}
