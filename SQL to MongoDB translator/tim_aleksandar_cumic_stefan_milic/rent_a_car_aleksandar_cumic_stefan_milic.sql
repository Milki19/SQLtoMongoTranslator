/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     4/16/2023 9:53:58 PM                         */
/*==============================================================*/

drop table if exists CENOVNIK;

drop table if exists DODATNI_TROSAK;

drop table if exists EKOLOSKI_POPUST;

drop table if exists GODINA_PROZIVODNJE;

drop table if exists ISPLATA;

drop table if exists KATEGORIJA_VOZILA;

drop table if exists KORISNIK;

drop table if exists LOKACIJA;

drop table if exists POPUST;

drop table if exists PROIZVODJAC;

drop table if exists RECENZIJA_KORISNIKA;

drop table if exists RECENZIJA_VOZACA;

drop table if exists RENT;

drop table if exists RENTABILNO_VOZILO;

drop table if exists RENT_A_CAR;

drop table if exists TIP;

drop table if exists TIP_PLACANJA;

drop table if exists VOZAC;

drop table if exists VOZILO;

drop table if exists ZAPOSLENI;

/*==============================================================*/
/* Table: CENOVNIK                                              */
/*==============================================================*/
create table CENOVNIK
(
   RENTABILNO_VOZILO_ID char(3) not null,
   KORISNIK_ID          char(5) not null,
   RENT_ID              char(5) not null,
   DATUM_UZIMANJA       date not null,
   DATUM_VRACANJA       date not null,
   CENOVNIK_ID          char(2) not null,
   CENA                 float(5,0) not null,
   primary key (RENTABILNO_VOZILO_ID, KORISNIK_ID, RENT_ID, DATUM_UZIMANJA, DATUM_VRACANJA, CENOVNIK_ID)
);

/*==============================================================*/
/* Table: DODATNI_TROSAK                                        */
/*==============================================================*/
create table DODATNI_TROSAK
(
   DODATAN_TROSAK_ID    char(3) not null,
   KORISNIK_ID          char(5) not null,
   RENT_ID              char(5) not null,
   DATUM_UZIMANJA       date not null,
   DATUM_VRACANJA       date not null,
   IZNOS                float(5,0) not null,
   DATUM_VRACANJA_VOZILA date not null,
   TIP_KAZNE            char(3) not null,
   primary key (DODATAN_TROSAK_ID)
);

/*==============================================================*/
/* Table: EKOLOSKI_POPUST                                       */
/*==============================================================*/
create table EKOLOSKI_POPUST
(
   EKOLOSKI_POPUST_ID   int not null,
   primary key (EKOLOSKI_POPUST_ID)
);

/*==============================================================*/
/* Table: GODINA_PROZIVODNJE                                    */
/*==============================================================*/
create table GODINA_PROZIVODNJE
(
   GODINA_PROIZVODNJE_ID numeric(4,0) not null,
   primary key (GODINA_PROIZVODNJE_ID)
);

/*==============================================================*/
/* Table: ISPLATA                                               */
/*==============================================================*/
create table ISPLATA
(
   ISPLATA_ID           varchar(10) not null,
   KORISNIK_ID          char(5) not null,
   RENT_ID              char(5) not null,
   DATUM_UZIMANJA       date not null,
   DATUM_VRACANJA       date not null,
   primary key (ISPLATA_ID)
);

/*==============================================================*/
/* Table: KATEGORIJA_VOZILA                                     */
/*==============================================================*/
create table KATEGORIJA_VOZILA
(
   KATEGORIJA_ID        char(3) not null,
   primary key (KATEGORIJA_ID)
);

/*==============================================================*/
/* Table: KORISNIK                                              */
/*==============================================================*/
create table KORISNIK
(
   KORISNIK_ID          char(5) not null,
   RENT_A_CAR_ID        char(3),
   POPUST_ID            char(2),
   IME_KORISNIKA        varchar(15) not null,
   JMBG                 char(13) not null,
   BROJ_TELEFONA        varchar(13) not null,
   EMAIL                varchar(15) not null,
   POL                  char(1),
   primary key (KORISNIK_ID)
);

/*==============================================================*/
/* Table: LOKACIJA                                              */
/*==============================================================*/
create table LOKACIJA
(
   LOKACIJA_ID          char(2) not null,
   GRAD                 varchar(12) not null,
   primary key (LOKACIJA_ID)
);

/*==============================================================*/
/* Table: POPUST                                                */
/*==============================================================*/
create table POPUST
(
   POPUST_ID            char(2) not null,
   KOLICINA_POPUSTA     decimal(2) not null,
   primary key (POPUST_ID)
);

/*==============================================================*/
/* Table: PROIZVODJAC                                           */
/*==============================================================*/
create table PROIZVODJAC
(
   PROIZVODJAC_ID       char(2) not null,
   IME                  varchar(15) not null,
   primary key (PROIZVODJAC_ID)
);

/*==============================================================*/
/* Table: RECENZIJA_KORISNIKA                                   */
/*==============================================================*/
create table RECENZIJA_KORISNIKA
(
   RECENZIJA_ID         char(2) not null,
   OCENA                decimal(3) not null,
   KORISNIK_ID          char(5) not null,
   primary key (RECENZIJA_ID, OCENA)
);

/*==============================================================*/
/* Table: RECENZIJA_VOZACA                                      */
/*==============================================================*/
create table RECENZIJA_VOZACA
(
   RECENZIJA_VOZACA_ID  char(3) not null,
   OCENA_VOZACA         decimal(3) not null,
   VOZAC_ID             char(2),
   primary key (RECENZIJA_VOZACA_ID, OCENA_VOZACA)
);

/*==============================================================*/
/* Table: RENT                                                  */
/*==============================================================*/
create table RENT
(
   KORISNIK_ID          char(5) not null,
   RENT_ID              char(5) not null,
   DATUM_UZIMANJA       date not null,
   DATUM_VRACANJA       date not null,
   ZAPOSLENI_ID         char(3) not null,
   primary key (KORISNIK_ID, RENT_ID, DATUM_UZIMANJA, DATUM_VRACANJA)
);

/*==============================================================*/
/* Table: RENTABILNO_VOZILO                                     */
/*==============================================================*/
create table RENTABILNO_VOZILO
(
   RENTABILNO_VOZILO_ID char(3) not null,
   GODINA_PROIZVODNJE_ID numeric(4,0) not null,
   KATEGORIJA_ID        char(3) not null,
   TIP_ID               char(1) not null,
   EKOLOSKI_POPUST_ID   int not null,
   PROIZVODJAC_ID       char(2) not null,
   VOZILO_ID            char(12) not null,
   RENT_A_CAR_ID        char(3) not null,
   primary key (RENTABILNO_VOZILO_ID)
);

/*==============================================================*/
/* Table: RENT_A_CAR                                            */
/*==============================================================*/
create table RENT_A_CAR
(
   RENT_A_CAR_ID        char(3) not null,
   LOKACIJA_ID          char(2) not null,
   primary key (RENT_A_CAR_ID)
);

/*==============================================================*/
/* Table: TIP                                                   */
/*==============================================================*/
create table TIP
(
   TIP_ID               char(1) not null,
   primary key (TIP_ID)
);

/*==============================================================*/
/* Table: TIP_PLACANJA                                          */
/*==============================================================*/
create table TIP_PLACANJA
(
   ISPLATA_ID           varchar(10) not null,
   KORISNIK_ID          char(5) not null,
   TIP_PLACANJA_ID      char(2) not null,
   primary key (ISPLATA_ID, KORISNIK_ID, TIP_PLACANJA_ID)
);

/*==============================================================*/
/* Table: VOZAC                                                 */
/*==============================================================*/
create table VOZAC
(
   VOZAC_ID             char(2) not null,
   RENTABILNO_VOZILO_ID char(3),
   KORISNIK_ID          char(5),
   RENT_ID              char(5),
   DATUM_UZIMANJA       date,
   DATUM_VRACANJA       date,
   CENOVNIK_ID          char(2),
   IME                  varchar(15) not null,
   primary key (VOZAC_ID)
);

/*==============================================================*/
/* Table: VOZILO                                                */
/*==============================================================*/
create table VOZILO
(
   GODINA_PROIZVODNJE_ID numeric(4,0) not null,
   KATEGORIJA_ID        char(3) not null,
   TIP_ID               char(1) not null,
   EKOLOSKI_POPUST_ID   int not null,
   PROIZVODJAC_ID       char(2) not null,
   VOZILO_ID            char(12) not null,
   MODEL                varchar(10),
   primary key (GODINA_PROIZVODNJE_ID, KATEGORIJA_ID, TIP_ID, EKOLOSKI_POPUST_ID, PROIZVODJAC_ID, VOZILO_ID)
);

/*==============================================================*/
/* Table: ZAPOSLENI                                             */
/*==============================================================*/
create table ZAPOSLENI
(
   ZAPOSLENI_ID         char(3) not null,
   RENT_A_CAR_ID        char(3) not null,
   IME                  varchar(15) not null,
   JMBG                 char(13) not null,
   POL                  char(1),
   primary key (ZAPOSLENI_ID)
);

alter table CENOVNIK add constraint FK_IMA_SVOJE foreign key (RENTABILNO_VOZILO_ID)
      references RENTABILNO_VOZILO (RENTABILNO_VOZILO_ID) on delete restrict on update restrict;

alter table CENOVNIK add constraint FK_PRIPADA_SVOJOJ foreign key (KORISNIK_ID, RENT_ID, DATUM_UZIMANJA, DATUM_VRACANJA)
      references RENT (KORISNIK_ID, RENT_ID, DATUM_UZIMANJA, DATUM_VRACANJA) on delete restrict on update restrict;

alter table DODATNI_TROSAK add constraint FK_IMATI_VISE foreign key (KORISNIK_ID, RENT_ID, DATUM_UZIMANJA, DATUM_VRACANJA)
      references RENT (KORISNIK_ID, RENT_ID, DATUM_UZIMANJA, DATUM_VRACANJA) on delete restrict on update restrict;

alter table ISPLATA add constraint FK_SE_VEZUJE foreign key (KORISNIK_ID, RENT_ID, DATUM_UZIMANJA, DATUM_VRACANJA)
      references RENT (KORISNIK_ID, RENT_ID, DATUM_UZIMANJA, DATUM_VRACANJA) on delete restrict on update restrict;

alter table KORISNIK add constraint FK_IMA_PRAVO_NA foreign key (POPUST_ID)
      references POPUST (POPUST_ID) on delete restrict on update restrict;

alter table KORISNIK add constraint FK_USLUZUJE foreign key (RENT_A_CAR_ID)
      references RENT_A_CAR (RENT_A_CAR_ID) on delete restrict on update restrict;

alter table RECENZIJA_KORISNIKA add constraint FK_MOZE_OCENITI foreign key (KORISNIK_ID)
      references KORISNIK (KORISNIK_ID) on delete restrict on update restrict;

alter table RECENZIJA_VOZACA add constraint FK_MOZE_IMATI foreign key (VOZAC_ID)
      references VOZAC (VOZAC_ID) on delete restrict on update restrict;

alter table RENT add constraint FK_IZDAJE foreign key (ZAPOSLENI_ID)
      references ZAPOSLENI (ZAPOSLENI_ID) on delete restrict on update restrict;

alter table RENT add constraint FK_VRSI foreign key (KORISNIK_ID)
      references KORISNIK (KORISNIK_ID) on delete restrict on update restrict;

alter table RENTABILNO_VOZILO add constraint FK_POSED foreign key (RENT_A_CAR_ID)
      references RENT_A_CAR (RENT_A_CAR_ID) on delete restrict on update restrict;

alter table RENTABILNO_VOZILO add constraint FK_RENTABILNOST foreign key (GODINA_PROIZVODNJE_ID, KATEGORIJA_ID, TIP_ID, EKOLOSKI_POPUST_ID, PROIZVODJAC_ID, VOZILO_ID)
      references VOZILO (GODINA_PROIZVODNJE_ID, KATEGORIJA_ID, TIP_ID, EKOLOSKI_POPUST_ID, PROIZVODJAC_ID, VOZILO_ID) on delete restrict on update restrict;

alter table RENT_A_CAR add constraint FK_PRIPADA foreign key (LOKACIJA_ID)
      references LOKACIJA (LOKACIJA_ID) on delete restrict on update restrict;

alter table TIP_PLACANJA add constraint FK_BIRA foreign key (KORISNIK_ID)
      references KORISNIK (KORISNIK_ID) on delete restrict on update restrict;

alter table TIP_PLACANJA add constraint FK_SE_VRSI foreign key (ISPLATA_ID)
      references ISPLATA (ISPLATA_ID) on delete restrict on update restrict;

alter table VOZAC add constraint FK_IMA_SVOG foreign key (RENTABILNO_VOZILO_ID, KORISNIK_ID, RENT_ID, DATUM_UZIMANJA, DATUM_VRACANJA, CENOVNIK_ID)
      references CENOVNIK (RENTABILNO_VOZILO_ID, KORISNIK_ID, RENT_ID, DATUM_UZIMANJA, DATUM_VRACANJA, CENOVNIK_ID) on delete restrict on update restrict;

alter table VOZILO add constraint FK_GODINA_PROIZVODNJE_VOZILA foreign key (GODINA_PROIZVODNJE_ID)
      references GODINA_PROZIVODNJE (GODINA_PROIZVODNJE_ID) on delete restrict on update restrict;

alter table VOZILO add constraint FK_KATEGORIJA_VOZILA foreign key (KATEGORIJA_ID)
      references KATEGORIJA_VOZILA (KATEGORIJA_ID) on delete restrict on update restrict;

alter table VOZILO add constraint FK_PROIZVODJAC_VOZILA foreign key (PROIZVODJAC_ID)
      references PROIZVODJAC (PROIZVODJAC_ID) on delete restrict on update restrict;

alter table VOZILO add constraint FK_TIP_VOZILA foreign key (TIP_ID)
      references TIP (TIP_ID) on delete restrict on update restrict;

alter table VOZILO add constraint FK_ZELENO_VOZILO foreign key (EKOLOSKI_POPUST_ID)
      references EKOLOSKI_POPUST (EKOLOSKI_POPUST_ID) on delete restrict on update restrict;

alter table ZAPOSLENI add constraint FK_IMA foreign key (RENT_A_CAR_ID)
      references RENT_A_CAR (RENT_A_CAR_ID) on delete restrict on update restrict;

