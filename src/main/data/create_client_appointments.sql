
-- tables used in stored procedure:
# CREATE TABLE dwh.`scheduled_patient` (
#
#     `id` bigint(20) NOT null auto_increment,
#
#     `first_name` varchar(255) DEFAULT NULL,
#
#     `last_name` varchar(255) DEFAULT NULL,
#
#     `exam_datetime` datetime,
#
#     PRIMARY KEY (`id`)
#
#     );

# CREATE TABLE random_names (
#     `id` bigint(20) PRIMARY KEY,
#     `first_name` varchar(255),
#     `last_name` varchar(255) DEFAULT NULL
# );


DROP PROCEDURE IF EXISTS createAppointments;
DELIMITER $$
CREATE PROCEDURE createAppointments(IN days integer)
BEGIN
    declare day_counter int unsigned default 0;
    declare appointment_hour int unsigned default 7;
    declare closing_hour int default 20;
    declare number_of_appointments int;
    declare minutes int default 0;
    -- iterate through days, then hours and finally minutes, based on how many clients will have appointment in that hour.
    WHILE day_counter < days do
        set appointment_hour = 7;
        WHILE appointment_hour < closing_hour do
            set number_of_appointments = FLOOR(RAND()*5);
            -- reset minutes when new hour starts
            set minutes=0;
            IF (number_of_appointments > 0) THEN
                WHILE minutes < 60 do
                    -- CREATE APPOINTMENT FOR RANDOM CLIENT FROM "random_names" table and calculate appointment time (starting from today 07:00)
                        INSERT INTO dwh.scheduled_patient(first_name, last_name, exam_datetime)
                            SELECT first_name, last_name, ADDTIME(CURRENT_DATE(), CONCAT(day_counter, ' ', appointment_hour, ':', minutes)) FROM random_names
                            ORDER BY RAND()
                            LIMIT 1;
                    set minutes=minutes+60/number_of_appointments;
                END WHILE;
            END IF;
            set appointment_hour=appointment_hour+1;
        END WHILE;
        set day_counter=day_counter+1;
    END WHILE;
END $$
DELIMITER ;


CALL createAppointments(3);