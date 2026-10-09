package vallegrande.edu.pe.gestionpedidos.view;

import vallegrande.edu.pe.gestionpedidos.model.Pedido;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class MainView extends BorderPane {

    public TextField txtCliente = new TextField();
    public TextField txtProducto = new TextField();
    public TextField txtCantidad = new TextField();
    public ComboBox<String> cbEstado = new ComboBox<>();

    public Button btnRegistrar = new Button("Registrar");
    public Button btnEditar = new Button("Editar");
    public Button btnEliminar = new Button("Eliminar");
    public Button btnLimpiar = new Button("Limpiar");

    public TableView<Pedido> tablaPedidos = new TableView<>();
    public TableColumn<Pedido, Integer> colId = new TableColumn<>("ID");
    public TableColumn<Pedido, String> colCliente = new TableColumn<>("Cliente");
    public TableColumn<Pedido, String> colProducto = new TableColumn<>("Producto");
    public TableColumn<Pedido, Integer> colCantidad = new TableColumn<>("Cantidad");
    public TableColumn<Pedido, String> colEstado = new TableColumn<>("Estado");

    public MainView() {
        setPadding(new Insets(15));

        // Formulario
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        cbEstado.getItems().addAll("Pendiente", "En Proceso", "Completado", "Cancelado");

        grid.add(new Label("Cliente:"), 0, 0);
        grid.add(txtCliente, 1, 0);
        grid.add(new Label("Producto:"), 0, 1);
        grid.add(txtProducto, 1, 1);
        grid.add(new Label("Cantidad:"), 0, 2);
        grid.add(txtCantidad, 1, 2);
        grid.add(new Label("Estado:"), 0, 3);
        grid.add(cbEstado, 1, 3);

        HBox botones = new HBox(10, btnRegistrar, btnEditar, btnEliminar, btnLimpiar);
        grid.add(botones, 1, 4);

        // Configuración de Tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tablaPedidos.getColumns().addAll(colId, colCliente, colProducto, colCantidad, colEstado);
        tablaPedidos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        setLeft(grid);
        setCenter(tablaPedidos);
    }
}