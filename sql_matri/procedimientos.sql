USE matricula;

drop procedure if exists spadicion;
DELIMITER //
create procedure spadicion(codc char(4),ape varchar(25),tipo char(1),pago numeric(8,1))
BEGIN
declare cuenta int ;
declare coda char(6);
select ifnull(max(nromat),0)+1 into cuenta from alumno;
set coda=lpad(cuenta,6,'0');
insert into alumno(nromat,codcur,apenom,tipo,pago) values(coda, codc, ape, tipo, pago);
update curso set nrovac=nrovac-1 where codcur=codc;
end //
delimiter ;

drop procedure if exists spanula;
DELIMITER //
create procedure spanula(coda char(6))
BEGIN
declare codc char(4);
select codcur into codc from alumno where nromat=coda;
update curso set nrovac=nrovac+1 where codcur=codc;
delete from alumno where nromat=coda;
end //
delimiter ;