/*
 * Copyright (c) 2023. All right reserved.
 */


package git.noteswiper.ent;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

/**
 * User object
 * @author user
 * @version 0.0.1
 * @since 0.0.1+2023.1_git.0003
 */
public class User {

    /**
     * A user name for {@link git.noteswiper.ent.User User} per object
     * @since v0.0.1+2023.1_git.0003
     * @see #getUserName()
     * @see org.apache.commons.lang3.RandomStringUtils#randomAlphanumeric(int)
     */
    public String userName = "user-" + RandomStringUtils.randomAlphanumeric(8).toUpperCase();
    /**
     * A user ID for {@link git.noteswiper.ent.User User} per object
     * @since v0.0.1+2023.1_git.0003
     * @see #getUserId()
     * @see org.apache.commons.lang3.RandomStringUtils#randomAlphanumeric(int)
     */
    public String userId = "userId-" + RandomStringUtils.randomAlphanumeric(16);
    /**
     * A user UUID for {@link git.noteswiper.ent.User User} per object
     * @since v0.0.1+2023.1_git.0003
     * @see #getUserUuid()
     * @see java.util.UUID#fromString(String)
     */
    public String userUuid = UUID.fromString(userId).toString();

    public User() {
    }

    /**
     * getUserName Method
     * Returns userName
     * @since v0.0.1+2023.1_git.0003
     * @return String
     */
    public String getUserName() {
        return this.userName;
    }
    /**
     * getUserId Method
     * Returns userId
     * @since v0.0.1+2023.1_git.0003
     * @return String
     */
    public String getUserId() {
        return this.userId;
    }
    /**
     * getUserUuid Method
     * Returns userUuid
     * @since v0.0.1+2023.1_git.0003
     * @return String
     */
    public String getUserUuid() {
        return this.userUuid;
    }
}
