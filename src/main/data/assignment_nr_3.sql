-- Melyik a 4. legtöbb felvételt készítő gép?
SELECT dwh.device.*, count(*) "series_count"
    FROM dwh.device
             JOIN dwh.study ON device.device_key = study.ae_key
             JOIN dwh.serie ON study.id = serie.study_key
    GROUP BY device.id
    ORDER BY count(*) DESC
    LIMIT 1 OFFSET 4;

-- Melyik volt a legzsúfoltabb nap, azaz melyik nap történt a legtöbb felvétel?
SELECT study.study_datetime, count(*) "series_count"
    FROM dwh.device
             JOIN dwh.study ON device.device_key = study.ae_key
             JOIN dwh.serie ON study.id = serie.study_key
    GROUP BY study.study_datetime
    ORDER BY count(*) DESC
    LIMIT 1 OFFSET 1;


-- Mennyi volt az átlagos képkészítési idő (series_duration) képtípusonként (series_type)
SELECT serie.series_type, AVG(series_duration) "average_series_duration"
    FROM serie
    GROUP BY serie.series_type
    ORDER BY serie.series_type ASC;


-- Melyik adattípusnak (data_type) a legmagasabb a diagnosztikai eredményessége (legnagyobb arányú a diagnostic=Y értéke)
SELECT device.data_type, count(*)
FROM dwh.device
         JOIN dwh.study ON device.device_key = study.ae_key
         JOIN dwh.serie ON study.id = serie.study_key
WHERE diagnostic = 'Y'
GROUP BY device.data_type
ORDER BY  COUNT(*) DESC
LIMIT 1;

-- Mit változtatnál a táblákon a gyorsabb végrehajtás érdekében?
-- 1. Minden tábla id-ját megjelölném PRIMARY KEY-nek, ezzel automatikusan kreálódna egy index az id alapján, így például a joinok is sokkal gyorsabbak lennének
