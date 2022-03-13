# Progetto-D22
![Java](https://img.shields.io/badge/Backend-java-red)
![JavaFX](https://img.shields.io/badge/Frontend-javafx-blueviolet)
![MySQL](https://img.shields.io/badge/Database-mysql-blue)

Progetto studenti (gruppo ArrayIndexOutOfBoundsException)

## Setup iniziale database
  1. Installare MySQL Server e MySQL Workbench sulla propria macchina
  2. Accedere all'istanza database locale
  3. Utilizzando MySQL Workbench, importare il file dump `./resources/trenissimo_db_dump.sql` (Server -> Data Import -> Import from Self-Contained File)
  4. Procedere con Start Import
  5. Modificare adeguatamente gli elementi `property` nel file `./src/hibernate.cfg.xml`
  6. Prima di eseguire l'entry point Trenissimo aggiungere le VM options:

     (IntelliJ: Edit Configurations -> Trenissimo -> Modify options -> Add VM options)
     (Eclipse: Run -> Run Configurations -> Arguments -> VM arguments)

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

  7. In Eclipse sussiste un problema probabilmente relativo all'Encoding UTF-8 che visualizza "€" con "â‚¬".
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