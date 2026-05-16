/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        PreparedStatement st;
        conn = new conectaDAO().connectDB();
        try{
            st = conn.prepareStatement("insert into produtos(nome, valor, status) values (?,?,?)");
            st.setString(1, produto.getNome());
            st.setInt(2, produto.getValor());
            st.setString(3, produto.getStatus());
            st.executeUpdate();
            st.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Produto " + produto.getNome() +" salvo com sucesso!");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage());
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        ResultSet rs;
        PreparedStatement st;
        conn = new conectaDAO().connectDB();
        try{
           st = conn.prepareStatement("select * from produtos");
           rs = st.executeQuery();
           
           while(rs.next()){
               ProdutosDTO p = new ProdutosDTO();
               
               p.setId(rs.getInt("id"));
               p.setNome(rs.getString("nome"));
               p.setValor(rs.getInt("valor"));
               p.setStatus(rs.getString("status"));
               
               listagem.add(p);
           }
           rs.close();
           st.close();
           conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao buscar: " + e.getMessage());
        }
        return listagem;
    }
    
    
    
        
}

