package oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.DAO.CorriereDAO;
import oobd2324_27.uninadelivery.unina_delivery.Database.Postgres;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Corriere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CorriereDAOImp implements CorriereDAO {

    @Override
    public List<Corriere> getAvailableCorrieri()  {
        String sql = "SELECT * FROM corriere WHERE disponibilita = TRUE";
        List<Corriere> corrieri = new ArrayList<>();
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String cognome = resultSet.getString("cognome");
                    String recapitoTelefonico = resultSet.getString("numero_telefono");
                    int matricola = resultSet.getInt("idcorriere");

                    Corriere corriere = new Corriere(nome, cognome, recapitoTelefonico,true, matricola);
                    corrieri.add(corriere);
                }
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return corrieri;
    }

    @Override
    public Corriere getById(int idcorriere) {
        String sql="SELECT * FROM corriere WHERE idcorriere=?";
        try{
            Connection connection=Postgres.getConnection();
            try{
                PreparedStatement preparedStatement= connection.prepareStatement(sql);
                preparedStatement.setInt(1,idcorriere);

                ResultSet resultSet=preparedStatement.executeQuery();
                if (resultSet.next()){

                    String nome=resultSet.getString("nome");
                    String cognome=resultSet.getString("cognome");
                    String recapitoTelefonico=resultSet.getString("numero_telefono");
                    Boolean disponibile=resultSet.getBoolean("disponibilita");
                    return new Corriere(nome,cognome,recapitoTelefonico,disponibile,idcorriere);
                }
            }catch (SQLException | MyException e){
                e.printStackTrace();
            }finally{
                connection.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
