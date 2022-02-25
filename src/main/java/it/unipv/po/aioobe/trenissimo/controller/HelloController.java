package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.RoutesEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedRoutesService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.controlsfx.control.SearchableComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ListView<StopsEntity> listView;

    @FXML
    private Button btnCache;

    @FXML
    private SearchableComboBox scbSearch;

    @FXML
    private Label lblText;

    private ObjectProperty<StopsEntity> routesSelezionato = new SimpleObjectProperty<>();

    @FXML
    protected void onLoadEntities(){
        var result = CachedStopsService.getInstance().findAll();
        ObservableList<StopsEntity> items = FXCollections.observableArrayList(result);
        listView.setItems(items);
    }

    @FXML
    protected void onLoadCache(){
        var result = CachedStopsService.getInstance().findAll();
        btnCache.setText("Cache loaded!");

        scbSearch.setItems(FXCollections.observableArrayList(result));
//        scbSearch.setCellFactory(param -> new ListCell<RoutesEntity>() {
//            @Override
//            protected void updateItem(RoutesEntity item, boolean empty) {
//                super.updateItem(item, empty);
//
//                if (empty || item == null || item.getRouteId() == null) {
//                    setText(null);
//                } else {
//                    setText(item.getRouteId());
//                }
//            }
//        });

        scbSearch.setConverter(new StringConverter<StopsEntity>() {
            @Override
            public String toString(StopsEntity user) {
                if (user== null){
                    return null;
                } else {
                    return user.getStopName();
                }
            }

            @Override
            public StopsEntity fromString(String id) {
                return null;
            }
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Message Here...");
//        alert.setHeaderText("Look, an Information Dialog");
//        alert.setContentText("I have a great message for you!");
//        alert.showAndWait().ifPresent(rs -> {
//            if (rs == ButtonType.OK) {
//                System.out.println("Pressed OK.");
//            }
//        });

        scbSearch.valueProperty().bindBidirectional(routesSelezionato);
    }
}