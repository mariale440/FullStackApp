-- Table: tbl_tarea

-- DROP TABLE tbl_tarea;

CREATE TABLE tbl_tarea
(
  id integer NOT NULL,
  descripcion character varying(200) NOT NULL,
  fecha_creacion date NOT NULL,
  vigencia integer NOT NULL,
  CONSTRAINT tbl_tarea_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tbl_tarea
  OWNER TO test;
