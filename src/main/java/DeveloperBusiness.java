import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperBusiness {
    public List<DeveloperModel> getAllDevelopers() {
        List<DeveloperModel> developerList = new ArrayList<>();
        String sql = "select * from developer";
        DBHelper dbHelper = new DBHelper(sql);
        ResultSet resultSet = null;
        try {
            resultSet = dbHelper.preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String site = resultSet.getString(3);
                String avatar = resultSet.getString(4);
                DeveloperModel developerModel = new DeveloperModel();
                developerModel.setId(id);
                developerModel.setName(name);
                developerModel.setSite(site);
                developerModel.setAvatar(avatar);
                developerList.add(developerModel);
            }
            resultSet.close();
            dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developerList;
    }

    public DeveloperModel getDeveloper(String developerId) {
        String sql = "select * from developer where id=" + developerId;
        DBHelper dbHelper = new DBHelper(sql);
        DeveloperModel developerModel = null;
        try {
            ResultSet resultSet = dbHelper.preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String site = resultSet.getString(3);
                String avatar = resultSet.getString(4);
                System.out.println("avatar=" + avatar);
                developerModel = new DeveloperModel();
                developerModel.setId(id);
                developerModel.setName(name);
                developerModel.setSite(site);
                developerModel.setAvatar(avatar);
            }
            resultSet.close();
            dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developerModel;
    }

    public boolean addDeveloper(DeveloperModel developerModel) {
        String sql = "INSERT INTO developer(name,site,avatar) VALUES(" +
                "'" + developerModel.getName() + "'," +
                "'" + developerModel.getSite() + "'," +
                "'" + developerModel.getAvatar() + "'" + ");";
        System.out.println("sql=" + sql);
        DBHelper dbHelper = new DBHelper(sql);
        return execute(dbHelper);
    }


    public boolean updateDeveloper(String id, String name) {
        String sql = "UPDATE developer SET name='" + name + "' WHERE id=" + id;
        System.out.println("sql=" + sql);
        DBHelper dbHelper = new DBHelper(sql);
        try {
            dbHelper.preparedStatement.executeUpdate();
            dbHelper.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteDeveloper(String id) {
        String sql = "DELETE FROM developer WHERE id=" + id;
        System.out.println("sql=" + sql);
        DBHelper dbHelper = new DBHelper(sql);
        return execute(dbHelper);
    }

    private boolean execute(DBHelper dbHelper) {
        try {
            dbHelper.preparedStatement.execute();
            dbHelper.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
