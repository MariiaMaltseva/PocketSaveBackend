package mariia.maltseva.dao;

import mariia.maltseva.entities.Category;

public interface CategoryDao {
    Category findCategoryByName(String name);

    Category findCategoryByType(String type);
}
