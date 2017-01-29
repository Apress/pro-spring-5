package com.apress.prospring4.ch5;

public class SecurityManager {
    private static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<UserInfo>();

    public void login(String userName, String password) {
        threadLocal.set(new UserInfo(userName, password));
    }

    public void logout() {
        threadLocal.set(null);
    }

    public UserInfo getLoggedOnUser() {
        return threadLocal.get();
    }
}
