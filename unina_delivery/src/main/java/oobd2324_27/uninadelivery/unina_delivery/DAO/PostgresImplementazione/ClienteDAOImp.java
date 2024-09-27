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
