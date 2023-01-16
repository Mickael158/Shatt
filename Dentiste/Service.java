package compta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Service {
    private long idservice;
    private double credit;
    private double debit;
    private double reste;

    public static double getSalaireByService(Connection conn, int idService ) throws SQLException, ClassNotFoundException{
        boolean witness = false;

        if (conn == null) {
            conn = new DBConnect().getConnection();
            witness = true;
        }
        double SalaireByService = 0;
        PreparedStatement ProfilStmt = null;
        try{
            String ProfilQuery = "SELECT Acte.duree , Acte.nbreemp , Pofil.salairehoraire FROM Acte" + 
                                                 " join Profil on Profil.idprofil=Acte.idprofil" +
                                                 " join Service on Service.idservice=Acte.idservice" +
                                                 " where idservice = ?";
            ProfilStmt = conn.prepareStatement(ProfilQuery);

            ProfilStmt.setInt(1, idService);
            ResultSet resultat = ProfilStmt.executeQuery();

            while(resultat.next()){
                double durer = resultat.getDouble("Acte.duree");
                double salairehoraire = resultat.getDouble("Pofil.salairehoraire");
                int nbrProfil = resultat.getInt("Acte.nbreemp");

                SalaireByService = durer * salairehoraire * nbrProfil;
                return SalaireByService;
            }
        }
        finally{
            if(ProfilStmt != null) ProfilStmt.close();
            if(witness) conn.close();
        }
        
        return SalaireByService;
    }

     public static double getPriceService(Connection conn , int idService ) throws SQLException, ClassNotFoundException {
        double PrixService = 0;
        String ServiceQuery = "SELECT montant FROM Service where idservice = ?";
        PreparedStatement ServiceStmt = conn.prepareStatement(ServiceQuery);

        ServiceStmt.setInt(1, idService);
        ResultSet Prix = ServiceStmt.executeQuery();

        if(Prix!=null){
            PrixService = Prix.getDouble("montant");
        }

        return PrixService;
    }

     public static double getDepense(Connection conn , int idService) throws SQLException , ClassNotFoundException {
        double PrixDepense = 0;
        String DepenseQuery = "SELECT SUM(Acte.duree*Acte.nbreemp*Pofil.salairehoraire) as PrixDepense FROM Acte" +
                                       " join Profil on Profil.idprofil=Acte.idprofil" +
                                       " join Service on Service.idservice=Acte.idservice" +
                                       "where idservice = ?";
        PreparedStatement DepenseStmt = conn.prepareStatement(DepenseQuery);

        DepenseStmt.setInt(1, idService);
        ResultSet Prix = DepenseStmt.executeQuery();
                               
        if(Prix!=null){
           PrixDepense = Prix.getDouble("PrixDepense");
        }
        return PrixDepense;
     }
     public static double getReste(Connection conn , int idService) throws SQLException , ClassNotFoundException{
        double reste=0;
        reste = getPriceService(conn, idService) - getDepense(conn, idService);
        return reste;
     }
}
