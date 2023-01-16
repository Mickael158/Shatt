package compta;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RevenuService {
    List<Service> Service;
    public static double getDebitTotal(Connection connection) throws SQLException, ClassNotFoundException{
        try {
            Statement statement = connection.createStatement();
            ResultSet debitTotal = statement.executeQuery("SELECT SUM(Acte.duree*Acte.nbreemp*Pofil.salairehoraire) as total_debits from Acte join Profil on Profil.idprofil=Acte.idprofil join Service on Service.idservice=Acte.idservice");
            return debitTotal.getDouble("total_debits");
        } catch (Exception e) {
            // TODO: handle exception
            return 0;
        }
    }
    public static double getDebitCredits(Connection connection) throws SQLException, ClassNotFoundException{
        try {
            Statement statement = connection.createStatement();
            ResultSet creditTotal = statement.executeQuery("SELECT SUM(montant) as total_credits from service");
            return creditTotal.getDouble("total_credits");
        } catch (Exception e) {
            // TODO: handle exception
            return 0;
        }
    }
    public static double getBenefice(Connection connection) throws SQLException, ClassNotFoundException{
        double benefice = 0;
        benefice = getDebitCredits(connection) - getDebitCredits(connection);
        return benefice;
    }

}
