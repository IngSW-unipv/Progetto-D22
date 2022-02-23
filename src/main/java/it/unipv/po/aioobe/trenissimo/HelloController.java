package it.unipv.po.aioobe.trenissimo;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.RoutesEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.RoutesService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class HelloController {

    @FXML
    private ListView listView;

    @FXML
    protected void onHelloButtonClick()
    {
        var test = new RoutesService().findAll();
        ObservableList<RoutesEntity> items = FXCollections.observableArrayList (test);
        listView.setItems(items);

    }
}