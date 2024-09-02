package oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.DAO.MezzoDAO;
import oobd2324_27.uninadelivery.unina_delivery.Database.Postgres;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Mezzo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MezzoDAOImp implements MezzoDAO {
    @Override
    public Mezzo getByDisponibilita(boolean disponibile) {
        String sql = "SELECT * FROM mezzo WHERE disponibile=?";
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setBoolean(1, disponibile);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String targa = resultSet.getString("targa");
                    Double pesoSupportato = resultSet.getDouble("peso_supportato");
                    Boolean disponibilita = resultSet.getBoolean("disponibilita");
                    Mezzo mezzo = new Mezzo(targa, pesoSupportato, disponibile);
                }
            } catch (SQLException | MyException e) {
                e.printStackTrace();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Mezzo getByTarga(String targa) {
        String sql="SELECT * FROM mezzo WHERE targa=?";
        try{
            Connection connection= Postgres.getConnection();
            try{
                PreparedStatement preparedStatement= connection.prepareStatement(sql);
                preparedStatement.setString(1,targa);

                ResultSet resultSet=preparedStatement.executeQuery();
                while (resultSet.next()){
                    String targaMezzo = resultSet.getString("targa");
                    Double pesoSupportato = resultSet.getDouble("peso_supportato");
                    Boolean disponibilita = resultSet.getBoolean("disponibilita");
                    Mezzo mezzo = new Mezzo(targa,pesoSupportato,disponibilita);
                }
            }catch (SQLException | MyException e){
                e.printStackTrace();
            }
            finally{
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Mezzo nuovoMezzo, String targa) {
        String sql = "UPDATE mezzo SET disponibile=? WHERE targa=?";
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setBoolean(1, nuovoMezzo.isDisponibile());
                preparedStatement.setString(2, nuovoMezzo.getTarga());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

