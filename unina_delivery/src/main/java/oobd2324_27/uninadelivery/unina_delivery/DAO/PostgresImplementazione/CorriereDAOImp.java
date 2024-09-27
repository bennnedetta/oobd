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
    public void createCorriere(Corriere corriere) throws MyException {
        String sql = "INSERT INTO corriere (nome, cognome, recapitoTelefonico, disponibile, matricola) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, corriere.getNome());
                preparedStatement.setString(2, corriere.getCognome());
                preparedStatement.setString(3, corriere.getRecapitoTelefonico());
                preparedStatement.setBoolean(4, corriere.isDisponibile());
                preparedStatement.setInt(5, corriere.getIdcorriere());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante la creazione del corriere");
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
    }

    @Override
    public void deleteCorriere(String matricola) throws MyException {
        String sql = "DELETE FROM corriere WHERE matricola = ?";
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, matricola);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante l'eliminazione del corriere");
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
    }

    @Override
    public void updateCorriere(Corriere corriere) throws MyException {
        String sql = "UPDATE corriere SET nome = ?, cognome = ?, recapitoTelefonico = ?, disponibile = ? WHERE matricola = ?";
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, corriere.getNome());
                preparedStatement.setString(2, corriere.getCognome());
                preparedStatement.setString(3, corriere.getRecapitoTelefonico());
                preparedStatement.setBoolean(4, corriere.isDisponibile());
                preparedStatement.setInt(5, corriere.getIdcorriere());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante l'aggiornamento del corriere");
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
    }

    @Override
    public Corriere readCorriere(String matricola) throws MyException {
        String sql = "SELECT * FROM corriere WHERE matricola = ?";
        Corriere corriere = null;
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, matricola);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String cognome = resultSet.getString("cognome");
                    String recapitoTelefonico = resultSet.getString("recapitoTelefonico");
                    boolean disponibile = resultSet.getBoolean("disponibile");
                    int matricolaResult = resultSet.getInt("idcorriere");

                    corriere = new Corriere(nome, cognome, recapitoTelefonico, disponibile, matricolaResult);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante la lettura del corriere");
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
        if (corriere == null) {
            throw new MyException("Corriere non trovato");
        }
        return corriere;
    }

    @Override
    public List<Corriere> getAllCorrieri() throws MyException {
        String sql = "SELECT * FROM corriere";
        List<Corriere> corrieri = new ArrayList<>();
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String cognome = resultSet.getString("cognome");
                    String recapitoTelefonico = resultSet.getString("recapitoTelefonico");
                    boolean disponibile = resultSet.getBoolean("disponibile");
                    int matricola = resultSet.getInt("idcorriere");

                    Corriere corriere = new Corriere(nome, cognome, recapitoTelefonico, disponibile, matricola);
                    corrieri.add(corriere);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante la lettura dei corrieri");
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
        return corrieri;
    }

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
