/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.logging.Loggable;

/**
 * FXML Controller class
 *
 * @author Eder Rodríguez
 */
public class menucontroller implements Initializable {

    @FXML 
    private Button btnCliente;
    
    @FXML 
    private Button btnProducto;
    
     @FXML 
    private Button btnOrden;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
        
    @FXML
    private void abrirCliente(ActionEvent event) throws IOException{
    
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ClienteVista.fxml"));
        
        Parent root = loader.load();
        
        clientecontroller controlador = loader.getController();
        
        
    //    Scene scene = new Scene (root);
        Stage stage = new Stage ();
         stage.setTitle("Sistema Clientes");
      //  stage.setScene(scene);
        
        
          stage.setScene(new Scene(root, 600, 500));
        stage.show();
        
      
                
        stage.setOnCloseRequest(e -> controlador.cerrarVentana());
        
        Stage miStage = (Stage) this.btnCliente.getScene().getWindow();
        miStage.close();
     
        
        
        }catch (IOException ex){
        
            Logger.getLogger(clientecontroller.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
        
    
    }
    
    @FXML
    private void abrirProducto(ActionEvent event){
    
    
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/FXMLProducto.fxml"));
        
        Parent root = loader.load();
        
        fxmldocumentcontroller controlador = loader.getController();
        
        
    //    Scene scene = new Scene (root);
        Stage stage = new Stage ();
         stage.setTitle("Sistema Clientes");
      //  stage.setScene(scene);
        
        
          stage.setScene(new Scene(root, 600, 500));
        stage.show();
        
      
                
        stage.setOnCloseRequest(e -> controlador.cerrarVentana());
        
        Stage miStage = (Stage) this.btnProducto.getScene().getWindow();
        miStage.close();
     
        
        
        }catch (IOException ex){
        
            Logger.getLogger(fxmldocumentcontroller.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
        
    
    }
        
    
    
    
    @FXML
    private void abrirOrden (ActionEvent event){
        
        
         try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/FXMLDocument.fxml"));
        
        Parent root = loader.load();
        
        fxmldocumentcontroller controlador = loader.getController();
        
        
    //    Scene scene = new Scene (root);
        Stage stage = new Stage ();
         stage.setTitle("Sistema Clientes");
      //  stage.setScene(scene);
        
        
          stage.setScene(new Scene(root, 600, 500));
        stage.show();
        
      
                
        stage.setOnCloseRequest(e -> controlador.cerrarVentana());
        
        Stage miStage = (Stage) this.btnProducto.getScene().getWindow();
        miStage.close();
     
        
        
        }catch (IOException ex){
        
            Logger.getLogger(fxmldocumentcontroller.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
        
        
        
        
    }
    
     
    
}
