create database healthcare_db;
use healthcare_db;
drop database healthcare_db; 
select * from flyway_schema_history;
select* from patient;
select* from medecin;
select * from rendez_vous;
select * from dossier_medical;
select * from patient p join dossier_medical d on d.patient_id = p.id where d.diagnostic like '%diabète%' order by p.nom asc;