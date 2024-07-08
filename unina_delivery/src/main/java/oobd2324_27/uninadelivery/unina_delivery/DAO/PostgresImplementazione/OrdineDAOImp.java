package oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.DAO.OrdineDAO;
import oobd2324_27.uninadelivery.unina_delivery.Database.Postgres;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Cliente;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Prodotto;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Spedizione;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Ordine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAOImp implements OrdineDAO {

    @Override
    public void createOrdine(Ordine ordine) throws MyException {
        String sql = "INSERT INTO ordine (data_ordine, costo_totale, peso_totale, cliente_email) " +
                     "VALUES (?, ?, ?, ?)";
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, ordine.getData());
                preparedStatement.setDouble(2, ordine.getCostoTotale());
                preparedStatement.setDouble(3, ordine.getPesoTotale());
                preparedStatement.setString(4, ordine.getCliente().getEmail());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante la creazione dell'ordine");
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
    }

    @Override
    public void deleteOrdine(LocalDate dataOrdine) throws MyException {
        String sql = "DELETE FROM ordine WHERE data_ordine = ?";
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, dataOrdine);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante l'eliminazione dell'ordine");
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
    }

    @Override
    public void updateOrdine(Ordine ordine) throws MyException {
        String sql = "UPDATE ordine SET costo_totale = ?, peso_totale = ?, cliente_email = ? " +
                     "WHERE data_ordine = ?";
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1, ordine.getCostoTotale());
                preparedStatement.setDouble(2, ordine.getPesoTotale());
                preparedStatement.setString(3, ordine.getCliente().getEmail());
                preparedStatement.setObject(4, ordine.getData());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante l'aggiornamento dell'ordine");
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
    }

    @Override
    public Ordine readOrdine(LocalDate dataOrdine) throws MyException {
        String sql = "SELECT * FROM ordine WHERE data_ordine = ?";
        Ordine ordine = null;
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, dataOrdine);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    double costoTotale = resultSet.getDouble("costo_totale");
                    double pesoTotale = resultSet.getDouble("peso_totale");
                    String clienteEmail = resultSet.getString("cliente_email");

                    // Utilizza un DAO per ottenere l'oggetto Cliente se necessario
                    ClienteDAO clienteDAO = new ClienteDAOImp();
                    Cliente cliente = clienteDAO.getByEmail(clienteEmail);

                    List<Prodotto> prodotti = new ArrayList<>(); // Implementa la logica per recuperare i prodotti

                    ordine = new Ordine(dataOrdine, costoTotale, pesoTotale, prodotti, cliente);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante la lettura dell'ordine");
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
        if (ordine == null) {
            throw new MyException("Ordine non trovato");
        }
        return ordine;
    }

    @Override
    public List<Ordine> getAllOrdini() throws MyException {
        String sql = "SELECT * FROM ordine";
        List<Ordine> ordini = new ArrayList<>();
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    LocalDate dataOrdine = resultSet.getObject("data_ordine", LocalDate.class);
                    double costoTotale = resultSet.getDouble("costo_totale");
                    double pesoTotale = resultSet.getDouble("peso_totale");
                    String clienteEmail = resultSet.getString("cliente_email");

                    // Utilizza un DAO per ottenere l'oggetto Cliente se necessario
                    ClienteDAO clienteDAO = new ClienteDAOImp();
                    Cliente cliente = clienteDAO.getByEmail(clienteEmail);

                    List<Prodotto> prodotti = new ArrayList<>(); // Implementa la logica per recuperare i prodotti

                    Ordine ordine = new Ordine(dataOrdine, costoTotale, pesoTotale, prodotti, cliente);
                    ordini.add(ordine);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("Errore durante la lettura degli ordini");
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Errore durante la connessione al database");
        }
        return ordini;
    }
}
