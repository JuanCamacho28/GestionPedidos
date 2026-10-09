package vallegrande.edu.pe.gestionpedidos.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public List<Pedido> listar() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pedido p = new Pedido(
                        rs.getInt("id"),
                        rs.getString("cliente"),
                        rs.getString("producto"),
                        rs.getInt("cantidad"),
                        rs.getString("estado")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar pedidos: " + e.getMessage());
        }
        return lista;
    }

    public boolean insertar(Pedido p) {
        String sql = "INSERT INTO pedido (cliente, producto, cantidad, estado) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getCliente());
            ps.setString(2, p.getProducto());
            ps.setInt(3, p.getCantidad());
            ps.setString(4, p.getEstado());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar pedido: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Pedido p) {
        String sql = "UPDATE pedido SET cliente=?, producto=?, cantidad=?, estado=? WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getCliente());
            ps.setString(2, p.getProducto());
            ps.setInt(3, p.getCantidad());
            ps.setString(4, p.getEstado());
            ps.setInt(5, p.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar pedido: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM pedido WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar pedido: " + e.getMessage());
            return false;
        }
    }
}