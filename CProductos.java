
package com.mycompany.proveedores;

import java.sql.CallableStatement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author valer
 */
public class CProductos {
    int id,cantidad;
    String descripcion;
    float costo_unitario,precio;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getCosto_unitario() {
        return costo_unitario;
    }

    public void setCosto_unitario(float costo_unitario) {
        this.costo_unitario = costo_unitario;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public void InsertarProducto(JTextField paramDescripcion,JTextField paramCosto,JTextField paramPrecio,JTextField paramCantidad){
        setDescripcion(paramDescripcion.getText());
        setCosto_unitario(Float.parseFloat(paramCosto.getText()));
        setPrecio(Float.parseFloat(paramPrecio.getText()));
        setCantidad(Integer.parseInt(paramCantidad.getText()));
        DBConnection objetoConexion= new DBConnection();
        String insert="INSERT INTO productos (descripcion,costo_unitario,precio,cantidad) VALUES(?,?,?,?);";
        try{
            CallableStatement cs=objetoConexion.establecerConexion().prepareCall(insert);
            cs.setString(1,getDescripcion());
            cs.setFloat(2, getCosto_unitario());
            cs.setFloat(3,getPrecio());
            cs.setInt(4,getCantidad());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se han insertado los datos correctamente");
            
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    public void eliminarProducto(JTextField paramCodigo){
        setId(Integer.parseInt(paramCodigo.getText()));
        DBConnection objetoConexion= new DBConnection();
        String delete="DELETE FROM productos WHERE productos.id=?;";
        try{
            CallableStatement cs=objetoConexion.establecerConexion().prepareCall(delete);
            cs.setInt(1,getId());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se eliminaron los datos correctamente");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    public void actualizarProducto(JTextField paramCodigo,JTextField paramCantidad,JTextField paramDescripcion, JTextField paramCostoUnitario,JTextField paramPrecio){
        setId(Integer.parseInt(paramCodigo.getText()));
        setCantidad(Integer.parseInt(paramCantidad.getText()));
        setDescripcion(paramCantidad.getText());
        setCosto_unitario(Float.parseFloat(paramCostoUnitario.getText()));
        setPrecio(Float.parseFloat(paramPrecio.getText()));
        DBConnection objetoConexion= new DBConnection();
        String update="UPDATE productos set cantidad=?,descripcion=?,costo_unitario=?, precio=? WHERE id=?;";
        try{
            CallableStatement cs=objetoConexion.establecerConexion().prepareCall(update);
            cs.setInt(1,getCantidad());
            cs.setString(2,getDescripcion());
            cs.setFloat(3,getCosto_unitario());
            cs.setFloat(4, getPrecio());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se modificaron los datos correctamente");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
