package mariia.maltseva.entities;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "balance_history")
public class BalanceHistory {
    @SequenceGenerator(name = "history_seq", sequenceName = "history_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "history_seq")
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "description")
    private String description;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "category")
    private Category categories;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public BalanceHistory(){}

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = Double.parseDouble(amount);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return this.date;
    }

    public void setCreatedData(Date date) {
        this.date = new Date(date.getTime());
    }

    @Override
    public String toString() {
        return "BalanceHistory{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", categories=" + categories +
                '}';
    }
}
