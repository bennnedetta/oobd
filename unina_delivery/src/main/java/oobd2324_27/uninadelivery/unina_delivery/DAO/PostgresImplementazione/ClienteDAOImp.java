package oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.DAO.ClienteDAO;
import oobd2324_27.uninadelivery.unina_delivery.Database.Postgres;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class ClienteDAOImp implements ClienteDAO {
    @Override
    public void create(Cliente cliente) {
        String sql="INSERT INTO cliente VALUES(?,?,?,?,?)";
        try{
            Connection connection= Postgres.getConnection();
            try{
                PreparedStatement preparedStatement= connection.prepareStatement(sql);
                preparedStatement.setString(1, cliente.getEmail());
                preparedStatement.setString(2, cliente.getNome());
                preparedStatement.setString(3, cliente.getCognome());
                preparedStatement.setString(4, cliente.getVia());
                preparedStatement.setString(5, cliente.getRecapitoTelefonico());

                preparedStatement.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            }
            finally {
                connection.close();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Cliente cliente) {
        String sql="DELETE FROM cliente where email=?";
        try{
            Connection connection=Postgres.getConnection();
            try {
                PreparedStatement preparedStatement= connection.prepareStatement(sql);
                preparedStatement.setString(1, cliente.getEmail());

                preparedStatement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                connection.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(Cliente nuovoCliente, String email) {
        String sql="UPDATE cliente SET nome=?,cognome=? where email=?";
        try{
            Connection connection=Postgres.getConnection();
            try {
                PreparedStatement preparedStatement= connection.prepareStatement(sql);
                preparedStatement.setString(1, nuovoCliente.getNome());
                preparedStatement.setString(2, nuovoCliente.getCognome());
                preparedStatement.setString(3, nuovoCliente.getEmail());

                preparedStatement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public Cliente getByEmail(String email) {
        String sql="SELECT * FROM cliente WHERE email=?";
        try{
            Connection connection=Postgres.getConnection();
            try{
                PreparedStatement preparedStatement= connection.prepareStatement(sql);
                preparedStatement.setString(1,email);

                ResultSet resultSet= preparedStatement.executeQuery();
                if (resultSet.next()){

                    String nome=resultSet.getString("nome");
                    String cognome=resultSet.getString("cognome");
                    String via=resultSet.getString("via");
                    String recapitoTelefonico=resultSet.getString("numero_telefono");
                    return new Cliente(nome,cognome,via,email,recapitoTelefonico);
                }
            }catch(SQLException | MyException e){
                e.printStackTrace();
            }
            finally {
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
