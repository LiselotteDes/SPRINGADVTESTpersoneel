insert into jobtitels(naam,versie) values('test',0);
insert into jobtitels(naam,versie) values('baas',0);
insert into werknemers(familienaam,voornaam,email,chefid,jobtitelid,salaris,paswoord,geboorte,rijksregisternr,versie)
values ('testBaas','testBaas','email@baas.be',null,(select id from jobtitels where naam='baas'),1000,'paswoord','1950-12-31',52123100151,0);
insert into werknemers(familienaam,voornaam,email,chefid,jobtitelid,salaris,paswoord,geboorte,rijksregisternr,versie)
values ('testWerknemer','testWerknemer','email@werk.be',(select id from werknemers where familienaam='testBaas'),
(select id from jobtitels where naam='test'),1000,'paswoord','1950-12-31',74013100148,0);