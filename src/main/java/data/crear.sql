drop table reserva;
drop table Valoracion;
drop table Sala;
drop table Usuario;
drop table Empresario;

create table Usuario
(
    nombre VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(20) NOT NULL,
    fechaNacimiento DATE,
    imagen BLOB,
    primary key(nombre)
);

create table Empresario
(
    nombre VARCHAR(50) NOT NULL,
    nombreEmp VARCHAR(20) UNIQUE,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(20) NOT NULL,
    NIF VARCHAR(9) NOT NULL,
    direccion VARCHAR(100),
    telefono INTEGER UNIQUE,
    imagen BLOB,
    primary key(NIF)
);

create table Sala
(
    ID INTEGER GENERATED ALWAYS AS IDENTITY
    (START WITH 1, INCREMENT BY 1),
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(1000),
    ubicacion VARCHAR(100) NOT NULL UNIQUE,
    precio FLOAT NOT NULL,
    horario DATE,
    infantil BOOLEAN,
    capacidad INTEGER,
    categorias VARCHAR(100),
    imagen BLOB,
    empresa VARCHAR(20),
    primary key(ID),
    FOREIGN KEY(empresa) REFERENCES Empresario(nombreEmp)
);

create table Valoracion
(
    ID INTEGER GENERATED ALWAYS AS IDENTITY
    (START WITH 1, INCREMENT BY 1),
    nombre VARCHAR(50) NOT NULL,
    sala INTEGER NOT NULL,
    opinion VARCHAR(200),
    valoracion FLOAT,
    primary key(ID),
    FOREIGN KEY(nombre) REFERENCES Usuario(nombre),
    FOREIGN KEY(sala) REFERENCES Sala(ID)
);

create table reserva
(
    nombre  VARCHAR(50) NOT NULL,
    sala INTEGER NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    precio FLOAT NOT NULL,
    PRIMARY KEY(hora),
    FOREIGN KEY(nombre) REFERENCES Usuario(nombre),
    FOREIGN KEY(sala) REFERENCES Sala(id)
);

insert into Usuario(Nombre, Email, Password, FechaNacimiento) values('pepe',
'pepe@gmail.com','ppee','04/13/2001');

insert into Empresario(Nombre,NombreEmp, NIF,email, telefono, direccion, password) 
values('Juan','J.S.L','27957364u','contacto@juan.es',742083743,'C/Ochoa 32','adfg');
insert into Empresario(Nombre,NombreEmp, NIF,email, telefono, direccion, password) 
values('Antoio','A.S.L','79375634v','contacto@ant.es',836591756,'C/Malaza 20',
'adfh');

insert into Sala(Nombre, Descripcion, Ubicacion, Precio, Infantil, Capacidad,
Categorias, Empresa) values('Sala Cine Privada "Delux"','Sala de cine para 8 personas<br>
Pantalla 80" con proyector<br>
Sonido Dolby Atmos<br>
Sillones reclinables<br>
Nevera', 'Valladolid C/Ochoa 32', 40, false, 8, 'Cine', 'J.S.L');
insert into Sala(Nombre, Descripcion, Ubicacion, Precio, Infantil, Capacidad,
Categorias, Empresa) values('Sala Cine Privada "Home, Sweet Home"', 'Cine para 15 personas<br>
Pantalla 80" led<br>
Nevera<br>
Dolby Atmos', 'Madrid C/Ochoa 33', 85, false, 15, 'Cine', 'J.S.L');
insert into Sala(Nombre, Descripcion, Ubicacion, Precio, Infantil, Capacidad,
Categorias, Empresa) values('Padel 10z', 'Paredes de cristal<br> 
iluminación artificial y natural', 'Valladolid C/Industrias 7',27, true, 4, 
'Deporte','A.S.L');

insert into Sala(Nombre, Descripcion, Ubicacion, Precio, Infantil, Capacidad,
Categorias, Empresa) values('Sala Infantil', 'Equipada para el ocio de los mas jovenes', 
'Valencia C/ Magdalena 26', 250, true, 20, 'Infantil', 'A.S.L');

insert into Valoracion(Nombre, Sala, Opinion, Valoracion) values('pepe', 4 ,
    'Sala infantil bonita pero mal equipada y muy cara relación calidad-precio.', 2.0);
insert into Valoracion(Nombre, Sala, Opinion, Valoracion) values('pepe', 3, 
    'Pista deportiva amplia y bien cuidada, ideal para actividad en equipo.', 5.0);

insert into Reserva(Nombre, Sala, Fecha, Hora, Precio) values('pepe', 1, '2021-04-03', '20:00:00', 40);