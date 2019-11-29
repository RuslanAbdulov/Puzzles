package com.company.linking_tree.example;

public class Party {

    public String id;
    public String value;

    public Party(String id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Party{");
        sb.append("id='").append(id).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
