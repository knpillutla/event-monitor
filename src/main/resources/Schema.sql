CREATE TABLE RES_STATE
(
    ID serial primary key,
    BUS_NAME character varying(50),
	LOCN_NBR integer not null default 0,
	COMPANY  character varying(100), -- is gift
	DIVISION  character varying(100), -- is gift
	BUS_UNIT  character varying(3) NOT NULL,
	BUS_KEY character varying(50),
	BUS_VAL character varying(50),
    WORKFLOW character varying(50),
    LAST_EVENT character varying(50),
    STAT_CODE  integer DEFAULT 0,
	HAD_EXCEPTION character varying(1),
	IS_IN_EXCEPTION_STATE character varying(1),
	TRANSACTION_NAME character varying(50),
	REF_FIELD_1  character varying(50),
	REF_FIELD_2  character varying(50),
	SOURCE character varying(50),
	HOST_NAME  character varying(50),
    CREATED_DTTM  timestamp not null default NOW(),
    UPDATED_DTTM  timestamp not null default NOW(),
    CREATED_BY character varying(25),
    UPDATED_BY character varying(25)
);

CREATE TABLE RES_EVENTS
(
    ID serial primary key,
	RES_ID integer not null  references RES_STATE(ID),
    EVENT_NAME character varying(25) NULL,
    EVENT_PAYLOAD text NULL,
	SOURCE character varying(50),
	TRANSACTION_NAME character varying(50),
	REF_FIELD_1  character varying(50),
	REF_FIELD_2  character varying(50),
	HOST_NAME  character varying(50),
    CREATED_DTTM  timestamp not null default NOW(),
    UPDATED_DTTM  timestamp not null default NOW(),
    CREATED_BY character varying(25),
    UPDATED_BY character varying(25)
);

CREATE TABLE WORKFLOW
(
    ID serial primary key,
    NAME character varying(25) NULL,
    START_STAT_CODE  integer not null,
    COMPLETION_STAT_CODE  integer not null,
    EXCEPTION_STAT_CODE  integer not null,
	DESCRIPTION character varying(100),
	REF_FIELD_1  character varying(50),
	REF_FIELD_2  character varying(50),
	HOST_NAME  character varying(50),
    CREATED_DTTM  timestamp not null default NOW(),
    UPDATED_DTTM  timestamp not null default NOW(),
    CREATED_BY character varying(25),
    UPDATED_BY character varying(25)
);

CREATE TABLE WORKFLOW_DTL
(
    ID serial primary key,
    WORKFLOW_ID  integer not NULL references WORKFLOW(ID),
    PARALLEL_EVENT_GROUP character varying(50),
    SEQ_NBR integer not NULL references EVENTS(ID),
	EVENT_ID integer not NULL references EVENTS(ID),
	STAT_CODE  character varying(50),
	IS_EXCEPTION character varying(1),
	REF_FIELD_1  character varying(50),
	REF_FIELD_2  character varying(50),
	HOST_NAME  character varying(50),
    CREATED_DTTM  timestamp not null default NOW(),
    UPDATED_DTTM  timestamp not null default NOW(),
    CREATED_BY character varying(25),
    UPDATED_BY character varying(25)
);

CREATE TABLE EVENTS
(
    ID serial primary key,
	EVENT_NAME character varying(50),
	DESCRIPTION character varying(100),
	BUS_KEY    character varying(50),
	IS_EXCEPTION character varying(1),
	REF_FIELD_1  character varying(50),
	REF_FIELD_2  character varying(50),
	HOST_NAME  character varying(50),
    CREATED_DTTM  timestamp not null default NOW(),
    UPDATED_DTTM  timestamp not null default NOW(),
    CREATED_BY character varying(25),
    UPDATED_BY character varying(25)
);



