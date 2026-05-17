package com.diamondogs.trucksapp.session;

import com.diamondogs.trucksapp.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class SessionManager {
    private static final SessionManager INSTANCE = new SessionManager();

    private User currentUser;
    private final List<Consumer<User>> listeners = new ArrayList<>();

    public static SessionManager getInstance() {
        return INSTANCE;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;

        notifyListeners();
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void clear() {
        this.currentUser = null;
        notifyListeners();
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public String getRole() {
        return currentUser != null ? currentUser.getRole() : null;
    }
    public String getUsername() {
        return currentUser != null ? currentUser.getUsername() : null;
    }

    public void addListener(Consumer<User> listener) {
        listeners.add(listener);
    }

    public void removeListener(Consumer<User> listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (Consumer<User> listener : new ArrayList<>(listeners)) {
            try {
                listener.accept(currentUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
