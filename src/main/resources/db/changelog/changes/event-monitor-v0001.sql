CREATE TABLE app_events
(
    BUS_NAME character varying(50),
	LOCN_NBR integer not null default 0,
	COMPANY  character varying(15), -- is gift
	DIVISION  character varying(10), -- is gift
	BUS_UNIT  character varying(3),
	EVENT_NAME  character varying(50) NOT NULL,
	REF_FIELD_1  character varying(50),
	REF_FIELD_2  character varying(50),
	HOST_NAME  character varying(50),
    CREATED_DTTM  timestamp not null default NOW(),
    UPDATED_DTTM  timestamp not null default NOW(),
    CREATED_BY character varying(25),
    UPDATED_BY character varying(25),
    VERSION INTEGER
);

SELECT create_hypertable('app_events', 'created_dttm');

