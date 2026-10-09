package vallegrande.edu.pe.gestionpedidos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import vallegrande.edu.pe.gestionpedidos.model.Pedido;
import vallegrande.edu.pe.gestionpedidos.model.PedidoDAO;

public class MainController {

    @FXML private TableView<Pedido> tablaPedidos;
    @FXML private TableColumn<Pedido, Integer> colId;
    @FXML private TableColumn<Pedido, String> colCliente;
    @FXML private TableColumn<Pedido, String> colProducto;
    @FXML private TableColumn<Pedido, Integer> colCantidad;
    @FXML private TableColumn<Pedido, String> colEstado;

    @FXML private TextField txtCliente;
    @FXML private TextField txtProducto;
    @FXML private TextField txtCantidad;
    @FXML private ComboBox<String> cbEstado;

    private final PedidoDAO dao = new PedidoDAO();
    private Pedido pedidoSeleccionado;

    @FXML
    public void initialize() {
        // Mapeo de columnas de la tabla con los getters de Pedido.java
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        // Opciones del ComboBox
        cbEstado.setItems(FXCollections.observableArrayList("Pendiente", "En Proceso", "Completado", "Cancelado"));

        // Evento de selección en la tabla
        tablaPedidos.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                pedidoSeleccionado = newVal;
                txtCliente.setText(newVal.getCliente());
                txtProducto.setText(newVal.getProducto());
                txtCantidad.setText(String.valueOf(newVal.getCantidad()));
                cbEstado.setValue(newVal.getEstado());
            }
        });

        cargarTabla();
    }

    private void cargarTabla() {
        ObservableList<Pedido> lista = FXCollections.observableArrayList(dao.listar());
        tablaPedidos.setItems(lista);
    }

    @FXML
    private void registrar() {
        if (validarCampos()) {
            Pedido p = new Pedido(
                    txtCliente.getText(),
                    txtProducto.getText(),
                    Integer.parseInt(txtCantidad.getText()),
                    cbEstado.getValue()
            );
            if (dao.insertar(p)) {
                cargarTabla();
                limpiar();
            }
        }
    }

    @FXML
    private void editar() {
        if (pedidoSeleccionado != null && validarCampos()) {
            pedidoSeleccionado.setCliente(txtCliente.getText());
            pedidoSeleccionado.setProducto(txtProducto.getText());
            pedidoSeleccionado.setCantidad(Integer.parseInt(txtCantidad.getText()));
            pedidoSeleccionado.setEstado(cbEstado.getValue());

            if (dao.actualizar(pedidoSeleccionado)) {
                cargarTabla();
                limpiar();
            }
        }
    }

    @FXML
    private void eliminar() {
        if (pedidoSeleccionado != null) {
            if (dao.eliminar(pedidoSeleccionado.getId())) {
                cargarTabla();
                limpiar();
            }
        }
    }

    @FXML
    private void limpiar() {
        txtCliente.clear();
        txtProducto.clear();
        txtCantidad.clear();
        cbEstado.setValue(null);
        pedidoSeleccionado = null;
        tablaPedidos.getSelectionModel().clearSelection();
    }

    private boolean validarCampos() {
        return !txtCliente.getText().trim().isEmpty() &&
                !txtProducto.getText().trim().isEmpty() &&
                !txtCantidad.getText().trim().isEmpty() &&
                cbEstado.getValue() != null;
    }
}