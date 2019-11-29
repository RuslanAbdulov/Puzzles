package com.company.linking_tree.example;

import java.util.ArrayList;
import java.util.List;

public class Arrangement {

    public String id;
    public String value;
    public List<String> partyIds = new ArrayList<>();

    public Arrangement(String id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Arrangement{");
        sb.append("id='").append(id).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append(", partyIds=").append(partyIds);
        sb.append('}');
        return sb.toString();
    }
}
