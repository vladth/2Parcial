<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Seminario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    Seminario sem=(Seminario)request.getAttribute("sem");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>        
            
    </head>
    <body>
        <h1 style="background:#2e2efe; width:250px; color:#fff;"><c:if test="${sem.id==0}">
            Nuevo Seminario
            </c:if>   
            <c:if test="${sem.id!=0}">
            Editar Seminario
            </c:if>
            </h1>
        
        <form action="MainController" method="post" style="border:1px solid black; display:inline-block; padding:10px;">
            <input type="hidden" name="id" value="${sem.id}">
            <table>                
                <tr>
                    <td>Titulo</td>
                     <td><input type="text" name="titulo" value="${sem.titulo}"></td>
                </tr>
                
                <tr>
                    <td>Expositor</td>
                     <td><input type="text" name="expositor" value="${sem.expositor}"></td>
                </tr>
                
                <tr>
                    <td>Fecha</td>
                     <td><input type="text" name="fecha" value="${sem.fecha}"></td>
                </tr>
                
                <tr>
                    <td>Hora</td>
                     <td><input type="text" name="hora" value="${sem.hora}"></td>
                </tr>
                
                <tr>
                    <td>Cupo</td>
                     <td><input type="text" name="cupo" value="${sem.cupo}"></td>
                </tr>
                
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar" style="background: green; padding:5px; width:100%;border:none;color:#fff;"></td>
                </tr>                
            </table>
        </form>
    </body>
</html>
