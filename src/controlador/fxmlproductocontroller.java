
package controlador;

import java.awt.event.MouseEvent;
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
import javafx.stage.Stage;
import modelo.Productos;


public class fxmlproductocontroller implements Initializable {
    
    
    @FXML
    private TextField txtDesc;
    @FXML
    private TableView tblProducto;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TableColumn colProducto;
    @FXML
    private TableColumn colDesc;
    @FXML
    private TableColumn colPrecio;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtProduc;
    @FXML
    private TableColumn colID;
    
    //creamos una lista 
    
     private ObservableList<Productos> listas;
    @FXML
    private Button CLista;
    @FXML
    private Button btnModProd;
    @FXML
    private Button btnElimProd;
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    

        listas = FXCollections.observableArrayList();

        this.colID.setCellValueFactory(new PropertyValueFactory("id"));
        this.colProducto.setCellValueFactory(new PropertyValueFactory("producto"));
        this.colDesc.setCellValueFactory(new PropertyValueFactory("descripcion"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));

    }
    
    
    
    /*---------------------------------*/
    
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
        
        
        Stage miStage = (Stage) this.btnElimProd.getScene().getWindow();
        miStage.close();
     
        
        
        }catch (IOException ex){
        
            Logger.getLogger(clientecontroller.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
    }
    
    
    /*-----------------------------------*/
    @FXML
    private void CrearLista(ActionEvent event) {
        
        try {
            int id = Integer.parseInt(this.txtID.getText());
            String producto = this.txtProduc.getText();
            String descripcion = this.txtDesc.getText();
            int precio = Integer.parseInt(this.txtPrecio.getText());
            
            
            Productos lista = new Productos(id, producto, descripcion, precio);

            if (!this.listas.contains(lista)) {
                this.listas.add(lista);

                this.tblProducto.setItems(listas);
                
                //alertas en pantalla
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setHeaderText(null);
                alert.setTitle("INFO");
                alert.setContentText("SE AGREGO PRODUCTO CORRECTAMENTE");
                alert.showAndWait();

            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("El producto ya Existe");
                alert.showAndWait();

            }

        } catch (NumberFormatException ex) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();

        }
    }

    private void selec(MouseEvent event) {

        // Obtengo la persona seleccionada
        Productos u = (Productos) this.tblProducto.getSelectionModel().getSelectedItem();

        // Sino es nula seteo los campos
        if (u != null) {
            this.txtID.setText(u.getId() + "");
            this.txtProduc.setText(u.getProducto());
            this.txtDesc.setText(u.getDescripcion());
            this.txtPrecio.setText(u.getPrecio()+ "");
        }

    }

    @FXML
    private void modificar(ActionEvent event) {
         // Obtengo la persona seleccionada
        Productos u = (Productos) this.tblProducto.getSelectionModel().getSelectedItem();

        // Si la persona es nula, lanzo error
        if (u == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar una registro");
            alert.showAndWait();
        } else {

            try {
                // Obtengo los datos del formulario
                String producto = this.txtProduc.getText();
                String descripcion = this.txtDesc.getText();
                int precio = Integer.parseInt(this.txtPrecio.getText());
               int id = Integer.parseInt(this.txtID.getText());
                // Creo una persona
               Productos aux = new Productos(id, producto, descripcion, precio);
                  //Productos aux = new Productos (,producto, descripcion, precio);
                // Compruebo si la persona esta en el lista
                if (!this.listas.contains(aux)) {

                    // Modifico el objeto
                    u.setProducto(aux.getProducto());
                    u.setDescripcion(aux.getDescripcion());
                    u.setPrecio(aux.getPrecio());

                    // Refresco la tabla
                    this.tblProducto.refresh();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("Se modifico el producto seleccionado");
                    alert.showAndWait();

                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("El producto ya existe");
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
         Productos u = (Productos) this.tblProducto.getSelectionModel().getSelectedItem();

        // Si la persona es nula, lanzo error
        if (u == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar un producto");
            alert.showAndWait();
        } else {

            // La elimino de la lista
            this.listas.remove(u);
            // Refresco la lista
            this.tblProducto.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Se elimino el producto correctamente");
            alert.showAndWait();

        }
}
}
