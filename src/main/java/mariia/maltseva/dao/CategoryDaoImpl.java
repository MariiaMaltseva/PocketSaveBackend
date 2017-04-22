package mariia.maltseva.dao;

import mariia.maltseva.entities.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Category findCategoryByName(String name) {
        String query = "SELECT c FROM Category c WHERE c.categoryName = :name";
        List<Category> foundCategories = em.createQuery(query, Category.class)
                .setParameter("name", name)
                .getResultList();
        return (foundCategories.size() > 0) ? foundCategories.get(0) : null;
    }

    @Override
    public Category findCategoryByType(String type) {
        String query = "SELECT c FROM Category c WHERE c.categoryType = :type";
        List<Category> foundCategories = em.createQuery(query, Category.class)
                .setParameter("type", type)
                .getResultList();
        return (foundCategories.size() > 0) ? foundCategories.get(0) : null;
    }
}
