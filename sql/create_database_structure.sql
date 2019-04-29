drop table if exists Auction;

drop table if exists Person;

drop table if exists Bid;

drop table if exists Item;

drop table if exists User;

/*==============================================================*/
/* Table: Auction                                               */
/*==============================================================*/
create table Auction
(
   id         int not null,
   user_id        int not null,
   item_id         int not null,
   start_price      float(50) not null,
   min_price       float(50),
   min_bid      float(5) not null,
   start_date        datetime,
   how_long         int,
   primary key (id)
);

/*==============================================================*/
/* Table: Person                                                */
/*==============================================================*/
create table Person
(
   id             int not null,
   user_id        int not null,
   name                 char(50) not null,
   surname             char(50) not null,
   email                char(255) not null,
   phone              char(9),
   primary key (id)
);

/*==============================================================*/
/* Table: Bid                                                   */
/*==============================================================*/
create table Bid
(
   id         int not null,
   auction_id         int not null,
   user_id        int not null,
   amount                float(50),
   data                 datetime,
   primary key (id)
);

/*==============================================================*/
/* Table: Item                                                  */
/*==============================================================*/
create table Item
(
   id        int not null auto_increment,
   name                char(255) not null,
   category            char(255) not null,
   primary key (id)
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   id        int not null,
   login                char(50) not null,
   password                char(50) not null,
   create_date       datetime not null,
   active          bool not null,
   primary key (id)
);

alter table Auction add constraint FK_przedmiot_licytacja foreign key (item_id)
      references Item (id) on delete restrict on update restrict;

alter table Auction add constraint FK_uzytkownik_licytacja foreign key (user_id)
      references User (id) on delete restrict on update restrict;

alter table Person add constraint FK_uzytkownik_dane foreign key (user_id)
      references User (id) on delete restrict on update restrict;

alter table Bid add constraint FK_przebicie_licytacja foreign key (auction_id)
      references Auction (id) on delete restrict on update restrict;

alter table Bid add constraint FK_uzytkownik_przebicie foreign key (user_id)
      references User (id) on delete restrict on update restrict;
