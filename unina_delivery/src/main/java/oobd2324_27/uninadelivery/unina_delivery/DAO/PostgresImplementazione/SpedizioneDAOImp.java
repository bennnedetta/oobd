package oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.DAO.CorriereDAO;
import oobd2324_27.uninadelivery.unina_delivery.DAO.MezzoDAO;
import oobd2324_27.uninadelivery.unina_delivery.DAO.OperatoreDAO;
import oobd2324_27.uninadelivery.unina_delivery.DAO.SpedizioneDAO;
import oobd2324_27.uninadelivery.unina_delivery.Database.Postgres;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Corriere;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Mezzo;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Operatore;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Ordine;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Spedizione;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpedizioneDAOImp implements SpedizioneDAO {

    @Override
    public void createSpedizione(Spedizione spedizione) {
        String sql = "INSERT INTO spedizione (data_spedizione, peso_totale_spedizione, data_consegna, stato_spedizione,targa_mezzo, idcorriere) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, spedizione.getDataSpedizione());
                preparedStatement.setDouble(2, spedizione.getPesoTotale());
                preparedStatement.setObject(3, spedizione.getDataConsegna());
                preparedStatement.setObject(4, spedizione.getStatoSpedizione(), Types.OTHER);
                preparedStatement.setString(5, spedizione.getMezzo().getTarga());
                preparedStatement.setInt(6, spedizione.getCorriere().getIdcorriere());

                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Spedizione> getAllSpedizioni() {
        String sql = "SELECT * FROM spedizione";
        List<Spedizione> spedizioni = new ArrayList<>();
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int numeroSpedizione=resultSet.getInt("idspedizione");
                    LocalDate dataSpedizione = resultSet.getObject("data_spedizione", LocalDate.class);
                    double pesoTotale = resultSet.getDouble("peso_totale_spedizione");
                    LocalDate dataConsegna = resultSet.getObject("data_consegna", LocalDate.class);
                    String statoSpedizione = resultSet.getString("stato_spedizione");
                    String mezzoTarga = resultSet.getString("targa_mezzo");
                    int corriereMatricola = resultSet.getInt("idcorriere");

                    MezzoDAO mezzoDAO = new MezzoDAOImp();
                    Mezzo mezzo = mezzoDAO.getByTarga(mezzoTarga);

                    CorriereDAO corriereDAO = new CorriereDAOImp();
                    Corriere corriere = corriereDAO.getById(corriereMatricola);

                    List<Ordine> ordini = new ArrayList<>(); // Implementa la logica per recuperare gli ordini

                    Spedizione spedizione = new Spedizione(numeroSpedizione,dataSpedizione, pesoTotale, dataConsegna, statoSpedizione, mezzo, corriere, ordini);
                    spedizioni.add(spedizione);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spedizioni;
    }

    @Override
    public Spedizione getById(int numeroSpedizione) {
        String sql = "SELECT * FROM spedizione WHERE idspedizione = ?";
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, numeroSpedizione);

                ResultSet resultSet = preparedStatement.executeQuery();
                if  (resultSet.next()) {
                    LocalDate dataSpedizione = resultSet.getObject("data_spedizione", LocalDate.class);
                    double pesoTotale = resultSet.getDouble("peso_totale_spedizione");
                    LocalDate dataConsegna = resultSet.getObject("data_consegna", LocalDate.class);
                    String statoSpedizione = resultSet.getString("stato_spedizione");
                    String mezzoTarga = resultSet.getString("targa_mezzo");
                    int corriereMatricola = resultSet.getInt("idcorriere");

                    MezzoDAO mezzoDAO = new MezzoDAOImp();
                    Mezzo mezzo = mezzoDAO.getByTarga(mezzoTarga);

                    CorriereDAO corriereDAO = new CorriereDAOImp();
                    Corriere corriere = corriereDAO.getById(corriereMatricola);

                    List<Ordine> ordini = new ArrayList<>(); // Implementa la logica per recuperare gli ordini

                    return new Spedizione(numeroSpedizione,dataSpedizione, pesoTotale, dataConsegna, statoSpedizione, mezzo, corriere, ordini);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getId(Spedizione spedizione) {
        Integer id=null;
        String sql="SELECT idspedizione FROM spedizione WHERE data_spedizione=? AND peso_totale_spedizione=? AND stato_spedizione=? AND data_consegna=? AND targa_mezzo=? AND idcorriere=?";
        try{
            Connection connection = Postgres.getConnection();
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, spedizione.getDataSpedizione());
                preparedStatement.setDouble(2, spedizione.getPesoTotale());
                preparedStatement.setObject(3, spedizione.getStatoSpedizione(),java.sql.Types.OTHER);
                preparedStatement.setObject(4, spedizione.getDataConsegna());
                preparedStatement.setString(5,spedizione.getMezzo().getTarga());
                preparedStatement.setInt(6, spedizione.getCorriere().getIdcorriere());

                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    id = resultSet.getInt("idspedizione");
                    return id;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public int contaSpedizioni(int anno,int mese){
        int numeroSpedizioni=0;
        String sql="SELECT COUNT(*) FROM spedizione WHERE data_spedizione>=? AND data_spedizione<?";
        String inizio = anno + "-" + mese + "-01";
        String fine = anno + "-" + (mese+1) + "-01";
        try{
            Connection connection = Postgres.getConnection();
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, inizio, Types.DATE);
                preparedStatement.setObject(2, fine, Types.DATE);

                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    numeroSpedizioni=resultSet.getInt(1);
                   // System.out.println(numeroSpedizioni);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numeroSpedizioni;
    }

    @Override
    public HashMap<String, Integer> getNumeroSpedizioniPerData(LocalDate inizio, LocalDate fine) {
        String sql="SELECT to_char(data_spedizione, 'YYYY-MM') as key, COUNT(*) as value FROM spedizione\n" +
                "WHERE data_spedizione >=? AND data_spedizione<? GROUP BY key";
        HashMap<String,Integer> result=new HashMap<>();
        try{
            Connection connection = Postgres.getConnection();
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, inizio.toString(), Types.DATE);
                preparedStatement.setObject(2, fine.toString(), Types.DATE);

                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    String key = resultSet.getString("key");
                    Integer value = resultSet.getInt("value");
                    result.put(key,value);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                connection.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public double getAvg(LocalDate dataInizio, LocalDate dataFine) {
        String sql="SELECT AVG(numero_prodotti) AS in_media\n" +
                "FROM(SELECT COUNT(*) AS numero_prodotti\n" +
                "FROM ordine_prodotto op JOIN ordine o on op.idordine = o.idordine\n" +
                "JOIN spedizione s on s.idspedizione = o.idspedizione\n" +
                "WHERE data_spedizione >=? AND data_spedizione<?\n" +
                "GROUP BY o.idordine)";
        double avg=0;
        try{
            Connection connection = Postgres.getConnection();
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, dataInizio.toString(), Types.DATE);
                preparedStatement.setObject(2, dataFine.toString(), Types.DATE);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    avg=resultSet.getDouble("in_media");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avg;
    }

    @Override
    public int getMin(LocalDate dataInizio, LocalDate dataFine) {
        String sql="SELECT o.idordine\n" +
                "FROM ordine_prodotto op JOIN ordine o on op.idordine = o.idordine\n" +
                "JOIN spedizione s on s.idspedizione = o.idspedizione\n" +
                "WHERE data_spedizione >=? AND data_spedizione<?\n" +
                "GROUP BY o.idordine\n" +
                "ORDER BY COUNT(*) ASC\n" +
                "LIMIT 1";
        int min=0;
        try{
            Connection connection = Postgres.getConnection();
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, dataInizio.toString(), Types.DATE);
                preparedStatement.setObject(2, dataFine.toString(), Types.DATE);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    min=resultSet.getInt(1);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return min;
    }

    @Override
    public int getMax(LocalDate dataInizio, LocalDate dataFine) {
        String sql="SELECT o.idordine\n" +
                "FROM ordine_prodotto op JOIN ordine o on op.idordine = o.idordine\n" +
                "         JOIN spedizione s on s.idspedizione = o.idspedizione\n" +
                "WHERE data_spedizione >=? AND data_spedizione<?\n" +
                "GROUP BY o.idordine\n" +
                "ORDER BY COUNT(*) DESC\n" +
                "LIMIT 1\n";
        int max=0;
        try{
            Connection connection = Postgres.getConnection();
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, dataInizio.toString(), Types.DATE);
                preparedStatement.setObject(2, dataFine.toString(), Types.DATE);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    max=resultSet.getInt(1);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return max;
    }
}

