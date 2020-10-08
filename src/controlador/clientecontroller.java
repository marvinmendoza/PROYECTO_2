package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Cliente;

public class clientecontroller implements Initializable {

   
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEdad;
    
    //TABLA
    @FXML
    private TableView<Cliente> tblClientes;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApellidos;
    @FXML
    private TableColumn colEdad;
    
    //ARRAYLIST

    private ObservableList<Cliente> personas;
    
    //BOTONES PRINCIPALES
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    
    @FXML
    private Button btnAgregar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        personas = FXCollections.observableArrayList();

        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory("apellidos"));
        this.colEdad.setCellValueFactory(new PropertyValueFactory("edad"));
        
        
        
        
    }
    
    
    public void cerrarVentana(){
    
      try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuVista.fxml"));
        
        Parent root = loader.load();
        
        menucontroller controlador = loader.getController();
        
        
        Scene scene = new Scene (root);
        Stage stage = new Stage ();
        
        stage.setScene(scene);
        stage.show();
        
        stage.setTitle("Sistema Menu");
        
        
        Stage miStage = (Stage) this.btnAgregar.getScene().getWindow();
        miStage.close();
     
        
        
        }catch (IOException ex){
        
            Logger.getLogger(clientecontroller.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
    }
    

    @FXML
    private void agregarPersona(ActionEvent event) {

        try {

            // Obtengo los datos del formulario
            String nombre = this.txtNombre.getText();
            String apellidos = this.txtApellidos.getText();
            int edad = Integer.parseInt(this.txtEdad.getText());

            // Creo una persona
            Cliente p = new Cliente(nombre, apellidos, edad);

            // Compruebo si la persona esta en el lista
            if (!this.personas.contains(p)) {
                // Lo añado a la lista
                this.personas.add(p);
                // Seteo los items
                this.tblClientes.setItems(personas);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Info");
                alert.setContentText("Se agrego CLIENTE correctamente");
                alert.showAndWait();
                txtNombre.setText("");
                txtApellidos.setText("");
                txtEdad.setText("");
               
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("El cliente ya existe");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }

    }

    @FXML
    private void seleccionar(MouseEvent event) {

        // Obtengo la persona seleccionada
        Cliente p = this.tblClientes.getSelectionModel().getSelectedItem();

        // Sino es nula seteo los campos
        if (p != null) {
            this.txtNombre.setText(p.getNombre());
            this.txtApellidos.setText(p.getApellidos());
            this.txtEdad.setText(p.getEdad() + "");
        }

    }

    @FXML
    private void modificar(ActionEvent event) {

        // Obtengo la persona seleccionada
        Cliente p = this.tblClientes.getSelectionModel().getSelectedItem();

        // Si la persona es nula, lanzo error
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar una registro");
            alert.showAndWait();
        } else {

            try {
                // Obtengo los datos del formulario
                String nombre = this.txtNombre.getText();
                String apellidos = this.txtApellidos.getText();
                int edad = Integer.parseInt(this.txtEdad.getText());

                // Creo una persona
                Cliente aux = new Cliente(nombre, apellidos, edad);

                // Compruebo si la persona esta en el lista
                if (!this.personas.contains(aux)) {

                    // Modifico el objeto
                    p.setNombre(aux.getNombre());
                    p.setApellidos(aux.getApellidos());
                    p.setEdad(aux.getEdad());

                    // Refresco la tabla
                    this.tblClientes.refresh();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("Se modifico cliente");
                    alert.showAndWait();

                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("El cliente ya existe");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Formato incorrecto");
                alert.showAndWait();
            }

        }

    }

    @FXML
    private void eliminar(ActionEvent event) {

        // Obtengo la persona seleccionada
        Cliente p = this.tblClientes.getSelectionModel().getSelectedItem();

        // Si la persona es nula, lanzo error
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar una persona");
            alert.showAndWait();
        } else {

            // La elimino de la lista
            this.personas.remove(p);
            // Refresco la lista
            this.tblClientes.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Se elimino CLIENTE correctamente");
            alert.showAndWait();

        }

    }

}


