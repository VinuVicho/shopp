package me.vinuvicho.shopp.security;

public enum ApplicationUserPermission {
    POST_CREATE("post:create"),
    POST_COMMENT("post:comment"),
    POST_EDIT("post:edit"),
    USER_EDIT("user:edit"),
    USER_ADD("user:add"),
    ADMIN_EDIT("admin:edit");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
