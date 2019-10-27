package com.elogstation.api.elogstationapi.db;

import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UsersId  implements Serializable {

    private String sub;
    private String deviceId;

}
