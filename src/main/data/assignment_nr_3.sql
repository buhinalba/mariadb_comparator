-- Melyik a 4. legtöbb felvételt készítő gép?



-- Melyik volt a legzsúfoltabb nap, azaz melyik nap történt a legtöbb felvétel?



-- Mennyi volt az átlagos képkészítési idő (series_duration) képtípusonként (series_type)



-- Melyik adattípusnak (data_type) a legmagasabb a diagnosztikai eredményessége (legnagyobb arányú a diagnostic=Y értéke)
-- A táblák közötti kapcsolat study.id = serie.study_key illetve a study.ae_key = device.id.
-- A study tábla vizsgálati adatokat tartalmaz.
-- A serie tábla az adott vizsgálat során elvégzett CT felvételek adatait, a device tábla pedig a felvételeket végző gépek adatait.
-- A választ megadó sql query-re lennék kíváncsi. Arra is kíváncsi lennék hogy mit változtatnál a táblákon a gyorsabb végrehajtás érdekében.
