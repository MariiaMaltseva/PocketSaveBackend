package mariia.maltseva.entities;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
public class Category {
    @SequenceGenerator(name = "category_seq", sequenceName = "category_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "category_seq")
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name")
    private String categoryName;

    @Column(name = "type")
    private String categoryType;

    public Category (){}

    public Category(String categoryName, String categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
}
