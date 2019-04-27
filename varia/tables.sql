create table connected_system(
	connected_sys_pk numeric(10,0) not null,
	connected_sys_id character varying(20) not null,
	created_by character varying(20),
	creation_date timestamp without time zone,
	updated_by character varying(20),
	updated_date timestamp without time zone,
	version_no numeric(20,0),
	primary key(connected_sys_pk)
);

CREATE TABLE incoming_msg(
	incoming_pk numeric(10,0) NOT NULL,
	reference_no character varying(6) not null,
	originating_sys_pk numeric(10,0) references connected_system(connected_sys_pk) not null,
	message_path character varying(256),
	created_by character varying(20),
	creation_date timestamp without time zone,
	updated_by character varying(20),
	updated_date timestamp without time zone,
	version_no numeric(20,0),
	PRIMARY KEY (incoming_pk)
);

create table splitted_msg(
	splitted_pk numeric(10,0) not null,
	incoming_pk numeric(10,0) references incoming_msg(incoming_pk) not null,
	message_path character varying(256),
	created_by character varying(20),
	creation_date timestamp without time zone,
	updated_by character varying(20),
	updated_date timestamp without time zone,
	version_no numeric(20,0),
	PRIMARY KEY (splitted_pk)
);

create table splitted_attribute(
	splitted_attr_pk numeric(10,0) not null,
	splitted_pk numeric(10,0) references splitted_msg(splitted_pk) not null,
	key character varying(20) not null,
	value character varying(50) not null,
	created_by character varying(20),
	creation_date timestamp without time zone,
	updated_by character varying(20),
	updated_date timestamp without time zone,
	version_no numeric(20,0),
	PRIMARY KEY (splitted_attr_pk)
);

create table out_msg(
	out_pk numeric(10,0) not null,
	destination_sys_pk numeric(10,0) references connected_system(connected_sys_pk) not null,
	message_path character varying(256),
	created_by character varying(20),
	creation_date timestamp without time zone,
	updated_by character varying(20),
	updated_date timestamp without time zone,
	version_no numeric(20,0),
	PRIMARY KEY (out_pk)
);

create table split_out_xref(
	xref_pk numeric(10,0) not null,
	splitted_pk numeric(10,0) references splitted_msg(splitted_pk) not null,
	out_pk numeric(10,0) references out_msg(out_pk) not null,
	processing_status character varying(20),
	created_by character varying(20),
	creation_date timestamp without time zone,
	updated_by character varying(20),
	updated_date timestamp without time zone,
	version_no numeric(20,0),
	PRIMARY KEY (xref_pk)
);





