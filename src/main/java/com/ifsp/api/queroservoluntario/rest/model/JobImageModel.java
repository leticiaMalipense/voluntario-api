package com.ifsp.api.queroservoluntario.rest.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class JobImageModel  implements Serializable {

    @NotNull
    private Long jodId;

    @NotBlank
    private String title;

    @NotNull
    private byte[] image;

    public Long getJodId() {
        return jodId;
    }

    public void setJodId(Long jodId) {
        this.jodId = jodId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
