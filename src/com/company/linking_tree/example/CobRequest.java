package com.company.linking_tree.example;

import java.util.ArrayList;
import java.util.List;

public class CobRequest {

    public String id;
    public String value;
    public List<Arrangement> arrangements = new ArrayList<>();
    public List<Party> parties = new ArrayList<>();

    public CobRequest(String id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CobRequest{");
        sb.append("id='").append(id).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append(", arrangements=").append(arrangements);
        sb.append(", parties=").append(parties);
        sb.append('}');
        return sb.toString();
    }
}
