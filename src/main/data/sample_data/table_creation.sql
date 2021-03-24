CREATE TABLE dwh.`serie` (
  `id` bigint(20) NOT NULL,
  `study_key` bigint(20) DEFAULT NULL,
  `series_type` varchar(255) DEFAULT NULL,
  `series_duration` double DEFAULT NULL,
  `diagnostic` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE dwh.`study` (
  `id` bigint(20) NOT NULL,
  `modality` varchar(3) DEFAULT NULL,
  `study_datetime` datetime(6) DEFAULT NULL,
  `ae_key` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE dwh.`device` (
  `device_key` bigint(20) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `aet` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `data_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

