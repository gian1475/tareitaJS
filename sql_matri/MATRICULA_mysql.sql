
DROP DATABASE IF EXISTS matricula;
CREATE DATABASE matricula; 
USE matricula;

CREATE TABLE alumno (
	nromat char (6) NOT NULL primary key ,
        codcur char (4) NULL ,
	apenom VARCHAR (25) NULL, 
        tipo   CHAR(1) NULL ,
        pago NUMERIC(8,1) NULL
   ); 


CREATE TABLE tabesp (
	codesp char(2) NOT NULL PRIMARY KEY ,
	nomesp char (20) NULL
); 


CREATE TABLE curso (
	codcur char(4) NOT NULL PRIMARY KEY ,
	codesp char(2) NULL ,
	nomcur char (15) NULL ,
	costo numeric(8, 1) NULL,
        nrovac  int   
); 


INSERT INTO alumno VALUES ('000001','C001','CLAUDIA VASQUEZ','1',230 );
INSERT INTO alumno VALUES ('000002','C002','CARLOS PAREDES','2',100 );
INSERT INTO alumno VALUES ('000003','C003','JUAN SANCHEZ','3',230 );
INSERT INTO alumno VALUES ('000004','C001','ADELA MEZA','1',440 );
INSERT INTO alumno VALUES ('000005','C002','GLORIA DELGADO','1',320 );
INSERT INTO alumno VALUES ('000006','C003','VIRNA MEJIA','2',220 );
INSERT INTO alumno VALUES ('000007','C010','JOSE LINARES','2',220 );
INSERT INTO alumno VALUES ('000008','C008','JENIFER CRUZADO','3',110 );
INSERT INTO alumno VALUES ('000009','C009','RAMON CERCADO','2',110 );
INSERT INTO alumno VALUES ('000010','C012','TERESA EGUSQUIZA','1',320 );
INSERT INTO alumno VALUES ('000011','C013','CAROLINA RETAMOZO','1',230);

INSERT INTO alumno VALUES ('000012','C001','Reyes Francisco','2',230);
INSERT INTO alumno VALUES ('000013','C007','nza Patricia','2',220);
INSERT INTO alumno VALUES ('000014','C008','Quispe Maria','1',340);
INSERT INTO alumno VALUES ('000015','C009','Polo Sanchez','2',180);
INSERT INTO alumno VALUES ('000016','C010','Ramos Vanessa','3',250);
INSERT INTO alumno VALUES ('000017','C007','Mendoza Marisol','3',330);
INSERT INTO alumno VALUES ('000018','C011','Ardito Petronila','2',450);
INSERT INTO alumno VALUES ('000019','C011','Hernandez Anastacia','2',450);
INSERT INTO alumno VALUES ('000020','C012','Espinoza Juan','3',250);
INSERT INTO alumno VALUES ('000021','C014','Yucar Carlos','2',230);


INSERT INTO alumno VALUES ('000022','C001','Avellaneda saenz','2',230);
INSERT INTO alumno VALUES ('000023','C007','Bobadilla Patricia','2',220);
INSERT INTO alumno VALUES ('000024','C008','Bustamante Caballero','1',340);
INSERT INTO alumno VALUES ('000025','C009','Carbajo Diaz','2',180);
INSERT INTO alumno VALUES ('000026','C010','Cochachi Shulla','3',250);
INSERT INTO alumno VALUES ('000027','C007','Cotrina Cordova','3',330);
INSERT INTO alumno VALUES ('000028','C011','Escudero Valverde','2',450);
INSERT INTO alumno VALUES ('000029','C011','Vasquez Anastacia','2',450);
INSERT INTO alumno VALUES ('000030','C012','Espinoza Juan','3',250);
INSERT INTO alumno VALUES ('000031','C014','Yucar Carlos','2',230);

INSERT INTO alumno VALUES ('000032','C001','Avellaneda saenz','2',230);
INSERT INTO alumno VALUES ('000033','C007','Bobadilla Patricia','2',220);
INSERT INTO alumno VALUES ('000034','C008','Bustamante Caballero','1',340);
INSERT INTO alumno VALUES ('000035','C009','Carbajo Diaz','2',180);
INSERT INTO alumno VALUES ('000036','C010','Cochachi Shulla','3',250);
INSERT INTO alumno VALUES ('000037','C007','Cotrina Cordova','3',330);
INSERT INTO alumno VALUES ('000038','C011','Escudero Valverde','2',450);
INSERT INTO alumno VALUES ('000039','C011','Vasquez Anastacia','2',450);
INSERT INTO alumno VALUES ('000040','C012','Espinoza Juan','3',250);
INSERT INTO alumno VALUES ('000041','C014','Yucar Carlos','2',230);

INSERT INTO alumno VALUES ('000042','C001','Garcia Avellaneda','1',230 );
INSERT INTO alumno VALUES ('000043','C002','Mamani AlvaREZ','2',100 );
INSERT INTO alumno VALUES ('000044','C001','Mercado Vilca','1',440 );
INSERT INTO alumno VALUES ('000045','C002','Meza Rosas','1',320 );
INSERT INTO alumno VALUES ('000046','C003','Napan ramos','2',220 );
INSERT INTO alumno VALUES ('000047','C010','Quispe Huertas','2',220 );
INSERT INTO alumno VALUES ('000048','C008','Rivera Ramirez','3',110 );
INSERT INTO alumno VALUES ('000049','C009','Santos Hoyos','2',110 );
INSERT INTO alumno VALUES ('000050','C012','TERESA EGUSQUIZA','1',320 );



INSERT INTO tabesp VALUES ('01','Lenguajes Visuales');
INSERT INTO tabesp VALUES ('02','Programacion Web');
INSERT INTO tabesp VALUES ('03','Redes');
INSERT INTO tabesp VALUES ('04','Base de Dato');
INSERT INTO tabesp VALUES ('05','Office');
INSERT INTO tabesp VALUES ('06','Diseno Web');


INSERT INTO curso VALUES ('C001','01','Java',850 ,10);
INSERT INTO curso VALUES ('C002','01','Visual Basic',300,12 );
INSERT INTO curso VALUES ('C003','01','Power Builder',650 ,6);
INSERT INTO curso VALUES ('C004','01','Visual Rpg',350,13 );
INSERT INTO curso VALUES ('C005','01','Prolog',450,14 );
INSERT INTO curso VALUES ('C006','01','Visual Rpg',350,6 );
INSERT INTO curso VALUES ('C007','02','Java Web',450 ,9);
INSERT INTO curso VALUES ('C008','02','Dream Weber',650,5 );
INSERT INTO curso VALUES ('C009','02','HTML',450,11 );
INSERT INTO curso VALUES ('C010','02','ASP',350,14 );
INSERT INTO curso VALUES ('C011','03','Windows NT',550,11 );
INSERT INTO curso VALUES ('C012','03','Linux',650,12 );
INSERT INTO curso VALUES ('C013','03','Unix',470,7 );
INSERT INTO curso VALUES ('C014','03','Windos 2000',850,11 );

INSERT INTO curso VALUES ('C015','04','Sql Server 2008',500,10 );
INSERT INTO curso VALUES ('C016','04','Maysql ',600,11 );
INSERT INTO curso VALUES ('C017','04','Oracle',800,14 );


INSERT INTO curso VALUES ('C018','05','Word',200,10 );
INSERT INTO curso VALUES ('C019','05','Excel ',300,11 );
INSERT INTO curso VALUES ('C020','05','Power Point',240,14 );

select * from alumno;
select * from curso;










