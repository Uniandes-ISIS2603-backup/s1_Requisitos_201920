delete from RequisitosEntity;
delete from CasoDeUsoEntity;
delete from ModificacionesEntity;



insert into CasoDeUsoEntity (id,documentacion,pruebas,servicios) values (44,'pruebaJava', 0, 'servicio de prueba');
insert into CasoDeUsoEntity (id, documentacion,pruebas,servicios) values (5, 'prueba2', 1, 'servicio de prueba');
insert into CasoDeUsoEntity (id, documentacion,pruebas,servicios) values (6, 'prueba3', 0, 'servicio de prueba');


insert into ModificacionesEntity (id, descripcion, fechaModificacion) values (1,'desc','2/02/1998');