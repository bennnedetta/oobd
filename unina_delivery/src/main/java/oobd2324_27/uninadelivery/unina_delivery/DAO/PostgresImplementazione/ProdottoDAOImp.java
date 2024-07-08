package oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.DAO.ProdottoDAO;
import oobd2324_27.uninadelivery.unina_delivery.Database.Postgres;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAOImp implements ProdottoDAO {

    @Override
    public void createProdotto(Prodotto prodotto) throws MyException {
        // Query SQL per inserire un nuovo prodotto nel database
        String sql = "INSERT INTO prodotto (peso, marca, prezzo) VALUES (?, ?, ?)";
        try {
            // Ottiene una connessione al database
            Connection connection = Postgres.getConnection();
            try {
                // Prepara la query SQL con i parametri
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1, prodotto.getPeso());
                preparedStatement.setString(2, prodotto.getMarca());
                preparedStatement.setDouble(3, prodotto.getPrezzo());

                // Esegue l'inserimento nel database
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante la creazione del prodotto");
            } finally {
                // Chiude la connessione al database
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
    }

    @Override
    public void deleteProdotto(String marca) throws MyException {
        // Query SQL per eliminare un prodotto dal database utilizzando la marca
        String sql = "DELETE FROM prodotto WHERE marca = ?";
        try {
            // Ottiene una connessione al database
            Connection connection = Postgres.getConnection();
            try {
                // Prepara la query SQL con i parametri
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, marca);

                // Esegue l'eliminazione dal database
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante l'eliminazione del prodotto");
            } finally {
                // Chiude la connessione al database
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
    }

    @Override
    public void updateProdotto(Prodotto prodotto) throws MyException {
        // Query SQL per aggiornare un prodotto nel database
        String sql = "UPDATE prodotto SET peso = ?, prezzo = ? WHERE marca = ?";
        try {
            // Ottiene una connessione al database
            Connection connection = Postgres.getConnection();
            try {
                // Prepara la query SQL con i parametri
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1, prodotto.getPeso());
                preparedStatement.setDouble(2, prodotto.getPrezzo());
                preparedStatement.setString(3, prodotto.getMarca());

                // Esegue l'aggiornamento nel database
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante l'aggiornamento del prodotto");
            } finally {
                // Chiude la connessione al database
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
    }

    @Override
    public Prodotto readProdotto(String marca) throws MyException {
        // Query SQL per recuperare un prodotto dal database utilizzando la marca
        String sql = "SELECT * FROM prodotto WHERE marca = ?";
        Prodotto prodotto = null;
        try {
            // Ottiene una connessione al database
            Connection connection = Postgres.getConnection();
            try {
                // Prepara la query SQL con i parametri
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, marca);

                // Esegue la query e recupera i dati dal database
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    double peso = resultSet.getDouble("peso");
                    double prezzo = resultSet.getDouble("prezzo");
                    String marcaResult = resultSet.getString("marca");

                    // Crea un nuovo oggetto Prodotto con i dati recuperati
                    prodotto = new Prodotto(peso, marcaResult, prezzo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante la lettura del prodotto");
            } finally {
                // Chiude la connessione al database
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
        if (prodotto == null) {
            throw new MyException("Prodotto non trovato");
        }
        return prodotto;
    }

    @Override
    public List<Prodotto> getAllProdotti() throws MyException {
        // Query SQL per recuperare tutti i prodotti dal database
        String sql = "SELECT * FROM prodotto";
        List<Prodotto> prodotti = new ArrayList<>();
        try {
            // Ottiene una connessione al database
            Connection connection = Postgres.getConnection();
            try {
                // Prepara la query SQL
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Esegue la query e recupera i dati dal database
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    double peso = resultSet.getDouble("peso");
                    double prezzo = resultSet.getDouble("prezzo");
                    String marca = resultSet.getString("marca");

                    // Crea un nuovo oggetto Prodotto con i dati recuperati e lo aggiunge alla lista
                    Prodotto prodotto = new Prodotto(peso, marca, prezzo);
                    prodotti.add(prodotto);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante la lettura dei prodotti");
            } finally {
                // Chiude la connessione al database
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
        return prodotti;
    }
}
