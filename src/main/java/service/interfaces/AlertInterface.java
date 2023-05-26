package service.interfaces;

import java.sql.SQLException;

public interface AlertInterface {
    void errorAlert() throws SQLException;
    void informationAlert() throws SQLException;
    void updateAlert() throws SQLException;
    void deleteAlert() throws SQLException;
}
