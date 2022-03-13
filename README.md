# Progetto-D22
![Java](https://img.shields.io/badge/Backend-java_17-red)
![JavaFX](https://img.shields.io/badge/Frontend-javafx_17-blueviolet)
![MySQL](https://img.shields.io/badge/Database-mysql_8-blue)

Progetto studenti (gruppo ArrayIndexOutOfBoundsException)

# TRENISSIMO

<p align="center">
  <img width="300" style= alt="Trenissimo logo grande (1)" src="https://user-images.githubusercontent.com/69969487/158078673-7bf6c617-ea79-4c27-984a-bf3a2145dbba.png">
</p>

## Setup iniziale database
  1. Installare MySQL Server e MySQL Workbench sulla propria macchina
  2. Accedere all'istanza database locale
  3. Utilizzando MySQL Workbench, importare il file dump `./resources/trenissimo_db_dump.sql` (Server -> Data Import -> Import from Self-Contained File)
  4. Procedere con Start Import
  5. Modificare adeguatamente gli elementi `property` nel file `./src/hibernate.cfg.xml`
## Setup iniziale progetto
  1. Prima di eseguire l'entrypoint Trenissimo aggiungere le VM options:

     (IntelliJ: Edit Configurations -> Trenissimo -> Modify options -> Add VM options)
     (Eclipse: Run -> Run Configurations -> Arguments -> VM arguments)

     ```
     --add-exports
     javafx.graphics/com.sun.javafx.scene=com.jfoenix
     --add-exports
     javafx.controls/com.sun.javafx.scene.control.behavior=com.jfoenix
     --add-exports
     javafx.controls/com.sun.javafx.scene.control=com.jfoenix
     --add-exports
     javafx.base/com.sun.javafx.binding=com.jfoenix
     --add-exports
     javafx.base/com.sun.javafx.event=com.jfoenix
     --add-exports
     javafx.graphics/com.sun.javafx.stage=com.jfoenix
     --add-opens=java.base/java.lang.reflect=com.jfoenix
     --add-exports
     javafx.base/com.sun.javafx.runtime=org.controlsfx.controls
     --add-exports
     javafx.controls/com.sun.javafx.scene.control.behavior=org.controlsfx.controls
     --add-exports
     javafx.controls/com.sun.javafx.scene.control.inputmap=org.controlsfx.controls
     --add-exports
     javafx.graphics/com.sun.javafx.scene.traversal=org.controlsfx.controls
     ```
     

  2. Selezionare come "main class" la classe `it/unipv/po/aioobe/trenissimo/Trenissimo.java`
  3. Premere il tasto Run
  4. In Eclipse sussiste un problema probabilmente relativo all'Encoding UTF-8 che visualizza "€" con "â‚¬".
     Per risolvere la cosa:
       1) Go to Window->Preferences->General->Content Types
       2) Select "Text" at the top tree-list.
       3) Specify "UTF8" in the bottom text box labeled "Default encoding".
       4) Click on the "Update" button.
       5) Select "Java Class File" at the top tree-list.
       6) Repeat (3) and (4)
       7) Click OK to save preferences.
  
     Recarsi nel file `src/main/java/it/unipv/po/aioobe/trenissimo/model/titolodiviaggio/enumeration/ValoreVoucher.java`
     e sostuire "â‚¬" con "€"

### ATTENZIONE!
In caso si abbia un dispositivo con processore Apple Silicon è necessario utilizzare
la versione `19-ea+3` di javafx.
In particolare è necessario modificare in questo modo il file `pom.xml`:
```xml
<dependencies>
     ...
     <dependency>
          <groupId>org.openjfx</groupId>
          <artifactId>javafx-controls</artifactId>
          <version>19-ea+3</version>
     </dependency>
     <dependency>
          <groupId>org.openjfx</groupId>
          <artifactId>javafx-fxml</artifactId>
          <version>19-ea+3</version>
     </dependency>
     ...
</dependencies>
```
e successivamente fare il load dei cambiamenti (Load Maven Changes).
