package co.uk.visualobjects.positnotes.application.model;

import java.util.List;

/**
 * An authenticated user.
 */
public final class User {
    // final class - think carefully before overriding in case overridden methods break user access filtering.

    private final String email;
    private final long userId;

    public User(
            final String email,
            final long userId) {
        this.email = email;
        this.userId = userId;
    }

    public User(
            final String email,
            final long companyId,
            final List<Long> permittedRetailerCompanyIds,
            final List<Long> permittedVendorCompanyIds) {

        this.email = email;
        this.userId = companyId;
    }

    public String getEmail() {
        return email;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        final User user = (User) o;

        if (userId != user.userId) {
            return false;
        }
        return email != null ? email.equals(user.email) : user.email == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "User{" + "email='" + email + '\'' + ", companyId=" + userId + '}';
    }

    public static final class Builder {
        private String email;
        private long userId;

        private Builder() {}

        public static Builder aUser() { return new Builder();}

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withUserId(long companyId) {
            this.userId = companyId;
            return this;
        }

        public User build() {
           return new User(email, userId);
        }
    }
}
