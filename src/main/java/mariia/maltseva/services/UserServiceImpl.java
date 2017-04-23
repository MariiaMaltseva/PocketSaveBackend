package mariia.maltseva.services;

import mariia.maltseva.dao.CategoryDao;
import mariia.maltseva.dao.UserDao;
import mariia.maltseva.dto.Credentials;
import mariia.maltseva.dto.DTOUser;
import mariia.maltseva.entities.BalanceHistory;
import mariia.maltseva.entities.Category;
import mariia.maltseva.entities.User;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryDao categoryDao;
    private DozerBeanMapper mapper = new DozerBeanMapper();

    public User register(DTOUser newUser) {
        User foundUserByEmail = userDao.getUserByEmail(newUser.getEmail());
        if (foundUserByEmail == null) {
            User userToSave = mapper.map(newUser, User.class);
            Category defaultCategory = new Category("other", "outcome");
            userToSave.setCategories(Arrays.asList(defaultCategory));
            userDao.save(userToSave);
            return userToSave;
        }
        return null;
    }

    @Override
    public User login(Credentials credentials) {
        boolean isExist = false;
        User foundUser = userDao.getUserByEmail(credentials.getEmail());
        if (foundUser != null && credentials.getPassword().equals(foundUser.getPassword())) {
            return foundUser;
        }
        return null;
    }

    @Override
    public User saveBalanceHistory(DTOUser user) {
        User userToUpdate = userDao.getUserByEmail(user.getEmail());
        if (userToUpdate != null) {
            BalanceHistory balanceHistoryToSave = new BalanceHistory();
            balanceHistoryToSave.setAmount(user.getHistory().getAmount());
            balanceHistoryToSave.setDescription(user.getHistory().getDescription());
            balanceHistoryToSave.setCreatedData(user.getHistory().getDate());

            Category category = categoryDao.findCategory(user.getCategory().getCategoryName(), user.getCategory().getCategoryType());
            balanceHistoryToSave.setCategories(category);

            List<BalanceHistory> balanceHistories = userToUpdate.getBalanceHistories();
            balanceHistories.add(balanceHistoryToSave);
            balanceHistories = balanceHistories.stream()
                    .sorted(Comparator.comparing(BalanceHistory::getDate).reversed())
                    .collect(Collectors.toList());
            userToUpdate.setBalance(userToUpdate.getBalance() + user.getHistory().getAmount());
            userToUpdate.setBalanceHistories(balanceHistories);

            userDao.update(userToUpdate);
        }
        return userToUpdate;
    }

    @Override
    public User addNewCategory(DTOUser user) {
        User userToUpdate = userDao.getUserByEmail(user.getEmail());
        if (userToUpdate != null) {
            Category categoryToAdd = new Category(user.getCategory().getCategoryName(), user.getCategory().getCategoryType());
            List<Category> existedCategory = userToUpdate.getCategories();
            existedCategory.add(categoryToAdd);
            userToUpdate.setCategories(existedCategory);
        }
        return userToUpdate;
    }

    @Override
    public User deleteCategory(DTOUser user) {
        User userToUpdate = userDao.getUserByEmail(user.getEmail());
        if (userToUpdate != null) {
            Category categoryToDelete = categoryDao.findCategory(user.getCategory().getCategoryName(), user.getCategory().getCategoryType());
            List<Category> existedCategory = userToUpdate.getCategories();
            if(existedCategory.contains(categoryToDelete)){
                existedCategory.remove(categoryToDelete);
                userToUpdate.setCategories(existedCategory);
            }
        }
        return userToUpdate;
    }

    public User updateBalance(DTOUser user) {
        User userToUpdate = userDao.getUserByEmail(user.getEmail());
        if (userToUpdate != null) {
            Category category = new Category("other", "income");
            List<Category> userCategory = userToUpdate.getCategories();
            userCategory.add(category);
            userToUpdate.setCategories(userCategory);
            userToUpdate.setBalance(userToUpdate.getBalance() + user.getBalance());

            userDao.update(userToUpdate);
        }
        return userToUpdate;
    }
}
