package oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.DAO.OperatoreDAO;
import oobd2324_27.uninadelivery.unina_delivery.Database.Postgres;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Operatore;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Spedizione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperatoreDAOImp implements OperatoreDAO {

    @Override
    public Operatore getByEmail(String email) {
        String sql="SELECT * FROM operatore WHERE email=?";
        Operatore operatore=null;
        try{
            Connection connection=Postgres.getConnection();
            try{
                PreparedStatement preparedStatement= connection.prepareStatement(sql);
                preparedStatement.setString(1,email);

                ResultSet resultSet=preparedStatement.executeQuery();
                while(resultSet.next()){
                    String mail=resultSet.getString("email");
                    String nome=resultSet.getString("nome");
                    String cognome=resultSet.getString("cognome");
                    String password=resultSet.getString("password");
                    operatore= new Operatore(nome,cognome,mail,password);
                }
            }catch(SQLException | MyException e ){
                e.printStackTrace();
            }
            finally {
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return operatore;
    }

    @Override
    public boolean existCredenziali(String email, String password) {
        String sql="SELECT * FROM operatore WHERE email=? AND password=?";
        try{
            Connection connection=Postgres.getConnection();
            try{
                PreparedStatement preparedStatement= connection.prepareStatement(sql);
                preparedStatement.setString(1,email);
                preparedStatement.setString(2,password);

                ResultSet resultSet=preparedStatement.executeQuery();
                if (resultSet.next()){
                    return true;
                }else{
                    return false;
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
        return false;
    }
}
