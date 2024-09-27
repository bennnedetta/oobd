package oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione;

import javafx.scene.control.DatePicker;
import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.DAO.ClienteDAO;
import oobd2324_27.uninadelivery.unina_delivery.DAO.OrdineDAO;
import oobd2324_27.uninadelivery.unina_delivery.DAO.SpedizioneDAO;
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
        String sql = "INSERT INTO ordine (data, costototale, pesototale, email_cliente,idspedizione) " +
                     "VALUES (?, ?, ?, ?,?)";
        try {
            Integer idspedizione = new SpedizioneDAOImp().getId(ordine.getSpedizione());
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, ordine.getData());
                preparedStatement.setDouble(2, ordine.getCostoTotale());
                preparedStatement.setDouble(3, ordine.getPesoTotale());
                preparedStatement.setString(4, ordine.getCliente().getEmail());

                preparedStatement.setInt(5, idspedizione);

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
        String sql = "UPDATE ordine SET idspedizione=? WHERE idordine=?";
        try {
            Integer spedizione = new SpedizioneDAOImp().getId(ordine.getSpedizione());
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,spedizione);
                preparedStatement.setInt(2,ordine.getNumero());

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
    public Ordine readOrdine(int idOrdine) {
        String sql = "SELECT * FROM ordine WHERE idordine = ?";
        Ordine ordine = null;
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, idOrdine);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    LocalDate data = resultSet.getObject("data", LocalDate.class);
                    double costoTotale = resultSet.getDouble("costototale");
                    double pesoTotale = resultSet.getDouble("pesototale");
                    String clienteEmail = resultSet.getString("email_cliente");

                    // Utilizza un DAO per ottenere l'oggetto Cliente se necessario
                    ClienteDAO clienteDAO = new ClienteDAOImp();
                    Cliente cliente = clienteDAO.getByEmail(clienteEmail);

                    List<Prodotto> prodotti = new ArrayList<>(); // Implementa la logica per recuperare i prodotti

                    ordine = new Ordine(idOrdine,data,costoTotale, pesoTotale, prodotti, cliente);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordine;
    }

    @Override
    public List<Ordine> getAllOrdiniDaSpedire() throws MyException {
        String sql = "SELECT * FROM ordine WHERE idspedizione IS NULL ";
        List<Ordine> ordini = new ArrayList<>();
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int numero = resultSet.getInt("idordine");
                    LocalDate dataOrdine = resultSet.getObject("data", LocalDate.class);
                    double costoTotale = resultSet.getDouble("costototale");
                    double pesoTotale = resultSet.getDouble("pesototale");
                    String clienteEmail = resultSet.getString("email_cliente");
                    int numeroSpedizione = resultSet.getInt("idspedizione");

                    SpedizioneDAO spedizioneDAO = new SpedizioneDAOImp();
                    Spedizione spedizione= spedizioneDAO.getById(numeroSpedizione);

                    // Utilizza un DAO per ottenere l'oggetto Cliente se necessario
                    ClienteDAO clienteDAO = new ClienteDAOImp();
                    Cliente cliente = clienteDAO.getByEmail(clienteEmail);

                   List<Prodotto> prodotti = new ArrayList<>(); // Implementa la logica per recuperare i prodotti

                    Ordine ordine = new Ordine(numero,dataOrdine, costoTotale, pesoTotale, prodotti, cliente,spedizione);
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

    @Override
    public List<Ordine> getAllOrdiniSpediti() {
        String sql = "SELECT * FROM ordine WHERE idspedizione IS NOT NULL ";
        List<Ordine> ordini = new ArrayList<>();
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int numero = resultSet.getInt("idordine");
                    LocalDate dataOrdine = resultSet.getObject("data", LocalDate.class);
                    double costoTotale = resultSet.getDouble("costototale");
                    double pesoTotale = resultSet.getDouble("pesototale");
                    String clienteEmail = resultSet.getString("email_cliente");
                    int numeroSpedizione = resultSet.getInt("idspedizione");

                    SpedizioneDAO spedizioneDAO = new SpedizioneDAOImp();
                    Spedizione spedizione= spedizioneDAO.getById(numeroSpedizione);

                    // Utilizza un DAO per ottenere l'oggetto Cliente se necessario
                    ClienteDAO clienteDAO = new ClienteDAOImp();
                    Cliente cliente = clienteDAO.getByEmail(clienteEmail);

                    List<Prodotto> prodotti = new ArrayList<>(); // Implementa la logica per recuperare i prodotti

                    Ordine ordine = new Ordine(numero,dataOrdine, costoTotale, pesoTotale, prodotti, cliente,spedizione);
                    ordini.add(ordine);
                }
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordini;
    }

    @Override
    public List<Ordine> getByData(LocalDate dataInizio, LocalDate dataFine) throws MyException {
        String sql = "SELECT * FROM ordine WHERE data BETWEEN ? AND ?";
        List<Ordine> ordini = new ArrayList<>();
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, dataInizio);
                preparedStatement.setObject(2, dataFine);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int numero = resultSet.getInt("idordine");
                    LocalDate dataOrdine = resultSet.getObject("data", LocalDate.class);
                    double costoTotale = resultSet.getDouble("costototale");
                    double pesoTotale = resultSet.getDouble("pesototale");
                    String clienteEmail = resultSet.getString("email_cliente");
                    int numeroSpedizione = resultSet.getInt("idspedizione");


                    SpedizioneDAO spedizioneDAO = new SpedizioneDAOImp();
                    Spedizione spedizione= spedizioneDAO.getById(numeroSpedizione);

                    ClienteDAO clienteDAO = new ClienteDAOImp();
                    Cliente cliente = clienteDAO.getByEmail(clienteEmail);

                    List<Prodotto> prodotti = new ArrayList<>();


                    Ordine ordine = new Ordine(numero,dataOrdine, costoTotale, pesoTotale,prodotti,cliente,spedizione);
                    ordini.add(ordine);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordini;
    }

    @Override
    public List<Ordine> getByCliente(LocalDate dataInizio, LocalDate dataFine,String clienteEmail) throws MyException {
        String sql = "SELECT * FROM ordine WHERE email_cliente=? AND data BETWEEN ? AND ?";
        List<Ordine> ordini = new ArrayList<>();
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, clienteEmail);
                preparedStatement.setObject(2, dataInizio);
                preparedStatement.setObject(3, dataFine);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int numero = resultSet.getInt("idordine");
                    LocalDate dataOrdine = resultSet.getObject("data", LocalDate.class);
                    double costoTotale = resultSet.getDouble("costototale");
                    double pesoTotale = resultSet.getDouble("pesototale");
                    int numeroSpedizione = resultSet.getInt("idspedizione");


                    SpedizioneDAO spedizioneDAO = new SpedizioneDAOImp();
                    Spedizione spedizione= spedizioneDAO.getById(numeroSpedizione);

                    List<Prodotto> prodotti = new ArrayList<>();

                    ClienteDAO clienteDAO = new ClienteDAOImp();
                    Cliente cliente = clienteDAO.getByEmail(clienteEmail);

                    Ordine ordine = new Ordine(numero,dataOrdine, costoTotale, pesoTotale,prodotti,cliente,spedizione);
                    ordini.add(ordine);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordini;
    }
}
