package oobd2324_27.uninadelivery.unina_delivery.DAO.PostgresImplementazione;

import oobd2324_27.uninadelivery.unina_delivery.CustomException.MyException;
import oobd2324_27.uninadelivery.unina_delivery.DAO.OperatoreDAO;
import oobd2324_27.uninadelivery.unina_delivery.Database.Postgres;
import oobd2324_27.uninadelivery.unina_delivery.Entity.Operatore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperatoreDAOImp implements OperatoreDAO {
    @Override
    public void create(Operatore operatore) {
        String sql="INSERT INTO operatore VALUES(?,?,?,?)";
        try {
            Connection connection = Postgres.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, operatore.getEmail());
                preparedStatement.setString(2, operatore.getNome());
                preparedStatement.setString(3, operatore.getCognome());
                preparedStatement.setString(4, operatore.getPassword());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Operatore operatore) {
        String sql="DELETE FROM operatore WHERE email=?";
        try{
            Connection connection=Postgres.getConnection();
            try{
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,operatore.getEmail());

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
    public void update(Operatore nuovoOperatore, String email) {
        String sql="UPDATE FROM operatore WHERE email=?";
        try{
            Connection connection=Postgres.getConnection();
            try{
                PreparedStatement preparedStatement= connection.prepareStatement(sql);
                preparedStatement.setString(1, nuovoOperatore.getEmail());

                preparedStatement.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            }
            finally{
                connection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

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
}
