package bin;

import java.util.List;

public class Account {

    private int id;
    private String username;
    private String password;
    private Role role;
    private List<Role> roles;

    public Account() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (getId() != account.getId()) return false;
        if (!getUsername().equals(account.getUsername())) return false;
        if (!getPassword().equals(account.getPassword())) return false;
        if (!getRole().equals(account.getRole())) return false;
        return getRoles().equals(account.getRoles());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getRole().hashCode();
        result = 31 * result + getRoles().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", roles=" + roles +
                '}';
    }
}
