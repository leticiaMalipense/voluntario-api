package com.ifsp.api.queroservoluntario.rest.model;

import java.io.Serializable;
import java.util.List;

public class PaginationModel implements Serializable {

    private Long total;

    List<?> items;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }
}
