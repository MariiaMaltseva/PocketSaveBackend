package mariia.maltseva.dao;

import mariia.maltseva.entities.Category;

public interface CategoryDao {
    Category findCategory(String name, String type);

    Category findCategoryByType(String type);
}
