
package com.emergentes.controlador;

import com.emergentes.modelo.Seminario;
import com.emergentes.utiles.ConexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id;
            String op;
            op=(request.getParameter("op")!=null)? request.getParameter("op"):"list";
            ArrayList<Seminario> lista= new ArrayList<Seminario>();
            ConexionBD canal = new ConexionBD();
            Connection conn =canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            
            if(op.equals("list")){
                //Para listar los datos
                String sql="select * from seminarios";
                //Consulta de seleccion y almacenarlo en una coleccion
                ps=conn.prepareStatement(sql);
                rs=ps.executeQuery();
                while(rs.next()){
                    Seminario sem=new Seminario();
                    sem.setId(rs.getInt("id"));
                    sem.setTitulo(rs.getString("titulo"));
                    sem.setExpositor(rs.getString("expositor"));
                    sem.setFecha(rs.getString("fecha"));
                    sem.setHora(rs.getString("hora"));
                    sem.setCupo(rs.getInt("cupo"));
                    lista.add(sem);
                }
                
                request.setAttribute("lista", lista);
                //Enviar al index.jsp para mostrar la informacion
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
            if(op.equals("nuevo")){
                Seminario se =new Seminario();
                
                System.out.println(se.toString());
                //El objeto se pone como atributo de request
                request.setAttribute("sem", se);
                //Redireccionar a editar.jsp
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                
            }
            
             if(op.equals("editar")){
                
                id=Integer.parseInt(request.getParameter("id"));
                Seminario sem1=new Seminario();
                ps=conn.prepareStatement("select * from seminarios where id=?");
                ps.setInt(1,id);
                rs=ps.executeQuery();
                
                if(rs.next()){
                    sem1.setId(rs.getInt("id"));
                    sem1.setTitulo(rs.getString("titulo"));
                    sem1.setExpositor(rs.getString("expositor"));
                    sem1.setFecha(rs.getString("fecha"));
                    sem1.setHora(rs.getString("hora"));
                    sem1.setCupo(rs.getInt("cupo"));
                }   
                request.setAttribute("sem", sem1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                
            }
             
             if(op.equals("eliminar")){
                //Obtener el id
                id=Integer.parseInt(request.getParameter("id"));
                //Realizar la eliminacion en la base de datos
                String sql="delete from seminarios where id= ?";
                ps=conn.prepareStatement(sql);
                ps.setInt(1,id);
                ps.executeUpdate();
                //Redireccionar a MainController
                response.sendRedirect("MainController");
            }
             
             
            
        } catch (Exception ex) {
            System.out.println("ERROR AL CONECTAR"+ex.getMessage());
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int id=Integer.parseInt(request.getParameter("id"));
            System.out.println("Valor de ID "+ id);
            String titulo=request.getParameter("titulo");
            String expositor=request.getParameter("expositor");
            String fecha =request.getParameter("fecha");
            String hora =request.getParameter("hora");
            int cupo =Integer.parseInt(request.getParameter("cupo"));
            
            Seminario sem= new Seminario();
            sem.setId(id);
            sem.setTitulo(titulo);
            sem.setExpositor(expositor);
            sem.setFecha(fecha);
            sem.setHora(hora);
            sem.setCupo(cupo);
            
            ConexionBD canal= new ConexionBD();
            Connection conn=canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            
            if(id==0){
                // NUevo Registro
                String sql="insert into seminarios (titulo,expositor,fecha,hora,cupo) values(?,?,?,?,?)";
                ps=conn.prepareStatement(sql);
                ps.setString(1, sem.getTitulo());
                ps.setString(2, sem.getExpositor());
                ps.setString(3, sem.getFecha());
                ps.setString(4, sem.getHora());
                ps.setInt(5, sem.getCupo());
                ps.executeUpdate();
            }
             else
            {   //Editar Registro
                String sql="update seminarios set titulo=?, expositor=?, fecha=?, hora=?, cupo=? where id=? ";
                
                ps=conn.prepareStatement(sql);
                ps.setString(1, sem.getTitulo());
                ps.setString(2, sem.getExpositor());
                ps.setString(3, sem.getFecha());
                ps.setString(4, sem.getHora());
                ps.setInt(5,sem.getCupo());
                ps.setInt(6,sem.getId());
                ps.executeUpdate();
                
                
                
            }
            response.sendRedirect("MainController");
            
        } catch (Exception ex) {
            System.out.println("Error en SQL"+ex.getMessage());
        }
        
    }


}
