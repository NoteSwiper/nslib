/*
 * Copyright (c) 2023. All right reserved.
 */

package git.noteswiper.ent;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class User {
    private String userName = "user-" + RandomStringUtils.randomAlphanumeric(8).toUpperCase();
    private String userId = "userId-" + RandomStringUtils.randomAlphanumeric(16);
    private String userUuid = UUID.fromString(userId).toString();

    public User() {

    }
}
