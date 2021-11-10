<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Seminario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    List<Seminario> lista=(List<Seminario>) request.getAttribute("lista");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            
            
            .container{
                display: block;                
                padding:15px;
                width: 500px;
                font-weight: bolder;
                margin:0px auto;
                border:1px solid black;
                text-align:center;
            }
            
            th, td{
                padding:10px;
                border:1px solid black;
            }
            table{
                border-collapse: collapse;
            }
            th{
                background: rgb(23,23,215);
                color:#fff;
            }
        </style>
    </head>
    <body>    
    <div class="container-fluid">
        <div class="container">
                    SEGUNDO PARCIAL TEM-742<BR>
                    NOMBRE :EDWIN VLADIMIR MAMANI PERCA<br>
                    CARNET :11548033
        </div>
        <h1 style="text-align: center">Registro  de Seminarios</h1>
        <p style="text-align: center"><a href="MainController?op=nuevo">Nuevo</a></p>
        <table  align="center">
            <tr>
                <th>ID</th>
                <th>Titulo</th>
                <th>Expositor</th>
                <th>Fecha</th>
                <th>Horas</th>
                <th>Cupos</th>
                <th colspan="2">Opciones</th>          
            </tr>
            
            <c:forEach var="item" items="${lista}">
                <tr>
                <td>${item.id}</td>
                <td>${item.titulo}</td>
                <td>${item.expositor}</td>
                <td>${item.fecha}</td>
                <td>${item.hora}</td>
                <td>${item.cupo}</td>
                <td><a href="MainController?op=editar&id=${item.id}">Editar</<a></td>
                <td><a href="MainController?op=eliminar&id=${item.id}"
                       onclick="return(confirm('Está seguro de eliminar el registro seleccionado ???'))">Eliminar</a></td>
            </tr>
                        
            </c:forEach>
            
        </table>
    </div>                                
    </body>
</html>
