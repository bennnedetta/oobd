package oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.DAO.MezzoDAO;
import oobd2324_27.uninadelivery.unina_delivery.Database.Postgres;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Mezzo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MezzoDAOImp implements MezzoDAO {

    @Override
    public Mezzo getByTarga(String targa) {
        String sql="SELECT * FROM mezzo WHERE targa=?";
        try{
            Connection connection= Postgres.getConnection();
            try{
                PreparedStatement preparedStatement= connection.prepareStatement(sql);
                preparedStatement.setString(1,targa);

                ResultSet resultSet=preparedStatement.executeQuery();
                if (resultSet.next()){
                    Double pesoSupportato = resultSet.getDouble("peso_supportato");
                    Boolean disponibilita = resultSet.getBoolean("disponibilita");
                    return new Mezzo(targa,pesoSupportato,disponibilita);
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
    public List<Mezzo> getDisponibili() {
        String sql="SELECT * FROM mezzo WHERE disponibilita=true ORDER BY peso_supportato ASC";
        ArrayList<Mezzo> listamezzi = new ArrayList<>();
        try{
            Connection connection= Postgres.getConnection();
            try{
                PreparedStatement preparedStatement= connection.prepareStatement(sql);
                ResultSet resultSet=preparedStatement.executeQuery();
                while (resultSet.next()){
                    String targa = resultSet.getString("targa");
                    Double pesoSupportato = resultSet.getDouble("peso_supportato");

                    Mezzo mezzo = new Mezzo(targa, pesoSupportato, true);
                    listamezzi.add(mezzo);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listamezzi;
    }

    @Override
    public List<Mezzo> getDisponibili(Double pesoBase) {
        String sql="SELECT * FROM mezzo WHERE disponibilita=true AND peso_supportato>= ? ORDER BY peso_supportato ASC";
        ArrayList<Mezzo> listamezzi = new ArrayList<>();
        try{
            Connection connection= Postgres.getConnection();
            try{
                PreparedStatement preparedStatement= connection.prepareStatement(sql);
                preparedStatement.setDouble(1, pesoBase);
                ResultSet resultSet=preparedStatement.executeQuery();

                while (resultSet.next()){
                    String targa = resultSet.getString("targa");
                    Double pesoSupportato = resultSet.getDouble("peso_supportato");

                    Mezzo mezzo = new Mezzo(targa, pesoSupportato, true);
                    listamezzi.add(mezzo);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listamezzi;
    }
}


