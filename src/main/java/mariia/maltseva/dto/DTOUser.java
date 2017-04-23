package mariia.maltseva.dto;

public class DTOUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private double balance;
    private DTOBalanceHistory history;
    private DTOCategory category;

    public double getBalance() {
        return balance;
    }

    public DTOBalanceHistory getHistory() {
        return history;
    }

    public void setHistory(DTOBalanceHistory history) {
        this.history = history;
    }

    public DTOCategory getCategory() {
        return category;
    }

    public void setCategory(DTOCategory category) {
        this.category = category;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
