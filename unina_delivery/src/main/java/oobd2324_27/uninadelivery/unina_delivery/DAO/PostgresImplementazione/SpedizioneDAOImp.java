package oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.DAO.SpedizioneDAO;
import oobd2324_27.uninadelivery.unina_delivery.Database.Postgres;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Corriere;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Mezzo;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Operatore;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Ordine;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Spedizione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SpedizioneDAOImp implements SpedizioneDAO {

    @Override
    public void createSpedizione(Spedizione spedizione) throws MyException {
        String sql = "INSERT INTO spedizione (data_spedizione, peso_totale, data_consegna, stato_spedizione, operatore_email, mezzo_targa, corriere_matricola) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, spedizione.getDataSpedizione());
                preparedStatement.setDouble(2, spedizione.getPesoTotale());
                preparedStatement.setObject(3, spedizione.getDataConsegna());
                preparedStatement.setString(4, spedizione.getStatoSpedizione());
                preparedStatement.setString(5, spedizione.getOperatore().getEmail());
                preparedStatement.setString(6, spedizione.getMezzo().getTarga());
                preparedStatement.setString(7, spedizione.getCorriere().getMatricola());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante la creazione della spedizione");
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
    }

    @Override
    public void deleteSpedizione(LocalDate dataSpedizione) throws MyException {
        String sql = "DELETE FROM spedizione WHERE data_spedizione = ?";
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, dataSpedizione);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante l'eliminazione della spedizione");
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
    }

    @Override
    public void updateSpedizione(Spedizione spedizione) throws MyException {
        String sql = "UPDATE spedizione SET peso_totale = ?, data_consegna = ?, stato_spedizione = ?, operatore_email = ?, mezzo_targa = ?, corriere_matricola = ? " +
                     "WHERE data_spedizione = ?";
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1, spedizione.getPesoTotale());
                preparedStatement.setObject(2, spedizione.getDataConsegna());
                preparedStatement.setString(3, spedizione.getStatoSpedizione());
                preparedStatement.setString(4, spedizione.getOperatore().getEmail());
                preparedStatement.setString(5, spedizione.getMezzo().getTarga());
                preparedStatement.setString(6, spedizione.getCorriere().getMatricola());
                preparedStatement.setObject(7, spedizione.getDataSpedizione());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante l'aggiornamento della spedizione");
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
    }

    @Override
    public Spedizione readSpedizione(LocalDate dataSpedizione) throws MyException {
        String sql = "SELECT * FROM spedizione WHERE data_spedizione = ?";
        Spedizione spedizione = null;
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, dataSpedizione);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    double pesoTotale = resultSet.getDouble("peso_totale");
                    LocalDate dataConsegna = resultSet.getObject("data_consegna", LocalDate.class);
                    String statoSpedizione = resultSet.getString("stato_spedizione");
                    String operatoreEmail = resultSet.getString("operatore_email");
                    String mezzoTarga = resultSet.getString("mezzo_targa");
                    String corriereMatricola = resultSet.getString("corriere_matricola");

                    // Utilizza altri DAO per ottenere gli oggetti Operatore, Mezzo, Corriere, Ordini se necessario
                    OperatoreDAO operatoreDAO = new OperatoreDAOImp();
                    Operatore operatore = operatoreDAO.getByEmail(operatoreEmail);

                    MezzoDAO mezzoDAO = new MezzoDAOImp();
                    Mezzo mezzo = mezzoDAO.getByTarga(mezzoTarga);

                    CorriereDAO corriereDAO = new CorriereDAOImp();
                    Corriere corriere = corriereDAO.getByMatricola(corriereMatricola);

                    List<Ordine> ordini = new ArrayList<>(); // Implementa la logica per recuperare gli ordini

                    spedizione = new Spedizione(dataSpedizione, pesoTotale, dataConsegna, statoSpedizione, operatore, mezzo, corriere, ordini);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante la lettura della spedizione");
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
        if (spedizione == null) {
            throw new MyException("Spedizione non trovata");
        }
        return spedizione;
    }

    @Override
    public List<Spedizione> getAllSpedizioni() throws MyException {
        String sql = "SELECT * FROM spedizione";
        List<Spedizione> spedizioni = new ArrayList<>();
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    LocalDate dataSpedizione = resultSet.getObject("data_spedizione", LocalDate.class);
                    double pesoTotale = resultSet.getDouble("peso_totale");
                    LocalDate dataConsegna = resultSet.getObject("data_consegna", LocalDate.class);
                    String statoSpedizione = resultSet.getString("stato_spedizione");
                    String operatoreEmail = resultSet.getString("operatore_email");
                    String mezzoTarga = resultSet.getString("mezzo_targa");
                    String corriereMatricola = resultSet.getString("corriere_matricola");

                    // Utilizza altri DAO per ottenere gli oggetti Operatore, Mezzo, Corriere, Ordini se necessario
                    OperatoreDAO operatoreDAO = new OperatoreDAOImp();
                    Operatore operatore = operatoreDAO.getByEmail(operatoreEmail);

                    MezzoDAO mezzoDAO = new MezzoDAOImp();
                    Mezzo mezzo = mezzoDAO.getByTarga(mezzoTarga);

                    CorriereDAO corriereDAO = new CorriereDAOImp();
                    Corriere corriere = corriereDAO.getByMatricola(corriereMatricola);

                    List<Ordine> ordini = new ArrayList<>(); // Implementa la logica per recuperare gli ordini

                    Spedizione spedizione = new Spedizione(dataSpedizione, pesoTotale, dataConsegna, statoSpedizione, operatore, mezzo, corriere, ordini);
                    spedizioni.add(spedizione);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante la lettura delle spedizioni");
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
        return spedizioni;
    }
}

