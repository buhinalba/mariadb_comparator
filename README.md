# Data Engineer Assignment
Collection of assignments for GE Healthcare's data engineering position.

The repo consists of 4 parts, each in a different package. (Description in hungarian):
  1. https://github.com/buhinalba/Data_engineer_assignment/tree/main/src/main/java/compare_databases
Egy olyan programot szeretnék Java-ban, ami összehasonlítja két MariaDB schema (náluk ezt database-nak is hívják) tábláit. Nevezzük az egyiket réginek, a másikat pedig újnak. Megmondja hogy melyik tábla az, ami törölve lett, melyik változott és hogyan (elég csak az oszlopokban bekövetkezett változást figyelni, de ha az oszlop adattípusa változott azt is szeretnénk látni nem csak az új vagy törölt oszlopokat) illetve melyik, ami újonnan lett létrehozva.
  2. https://github.com/buhinalba/Data_engineer_assignment/tree/main/src/main/java/count_column_alterations
Szeretnék egy olyan programot akár Java akár Python, ami végignézi egy adott könyvtár és alkönyvtárjai összes '.sql' kiterjesztésű file-ját. Ezek a file-ok mindenféle MariaDB SQL DDL parancsokat tárolnak. Van ami csak egyet, van ami viszont rengeteget. Minden parancs új sorban kezdődik és igaz minden ALTER prancsra, hogy egy parancs csak egy változást hajt végre. A program célja, hogy gyűjtse össze az összes megváltoztatott oszlop nevét tábla.oszlop formátumban. Azokra az oszlopokra viszont nem vagyunk kíváncsiak, amik olyan táblákban vannak, melyek nevében benne van a 'dwXX' sztring (az XX két számjegyet jelent)
  3. https://github.com/buhinalba/Data_engineer_assignment/blob/main/src/main/data/assignment_nr_3.sql
A mellékelt három MariaDB (az általam használt MariaDB verziója 10.4.12) tábla felhasználásával a következő kérdésekre szeretnék választ kapni:
  - Melyik a 4. legtöbb felvételt készítő gép?
  - Melyik volt a legzsúfoltabb nap, azaz melyik nap történt a legtöbb felvétel?
  - Mennyi volt az átlagos képkészítési idő (series_duration) képtípusonként (series_type)
  - Melyik adattípusnak (data_type) a legmagasabb a diagnosztikai eredményessége (legnagyobb arányú a diagnostic=Y értéke)
  A táblák közötti kapcsolat study.id = serie.study_key illetve a study.ae_key = device.id. A study tábla vizsgálati adatokat tartalmaz. A serie tábla az adott vizsgálat során elvégzett CT felvételek adatait, a device tábla pedig a felvételeket végző gépek adatait.  A választ megadó sql query-re lennék kíváncsi. Arra is kíváncsi lennék hogy mit változtatnál a táblákon a gyorsabb végrehajtás érdekében.
  4. Extra feladat tárolt eljárással MariaDB-ben.
 ``` sql

CREATE TABLE `scheduled_patient` (

`id` bigint(20) NOT null auto_increment,

`first_name` varchar(255) DEFAULT NULL,

`last_name` varchar(255) DEFAULT NULL,

`exam_datetime` datetime,

PRIMARY KEY (`id`)

);
```
  Ebbe a paciens nyilvántartó táblába szeretnék beszúrni véletlenszerű vezeték és keresztnév adatot. Az első vizsgálat minden nap reggel 7-kor van, az utolsó pedig és este 8-kor. Óránként véletlenszerűen 0-4 pácienst szeretnék beilleszteni egyenletesen elosztva, azaz ha egy ember jön akkor ő HH:00-ra, ha kettő akkor az első HH:00, a második HH:30, ha három akkor HH:00,HH:20,HH:40 és így tovább. A következő x napra szeretnék adatot, az x bemenő paramétere a tárolt eljárásnak.
