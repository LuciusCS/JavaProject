/*create database AssetsDB;*/
use AssetsDB;

create table AssetsType(
    TypeID int auto_increment primary key,
    B_Type varchar(20) not null,
    S_Type varchar(20) not null
);

create table Assets(
    AssetsID int auto_increment primary key,
    name varchar(20),
    TypeID int not null,
    Model varchar(30) not null,
    Price varchar(20) not null,
    BuyDate date not null,
    Status varchar(10) not null,
    Other varchar(50),
    foreign key (TypeID) references AssetsType(TypeID)
);

create table Person(
    PersonID int auto_increme   nt primary key,
    Name varchar(20) not null,
    Sex varchar(4) not null,
    Dept varchar(20) not null,
    Job varchar(20) not null,
    Other varchar(50)
);

create table AssetsTrjn(
    JourNo int auto_increment primary key,
    FromAcc varchar(20) not null,
    AssetsID  int not null,
    RegDate date not null,
    PersonID int not null,
    purpose varchar(50) not null,
    Other varchar(50),
    foreign key (AssetsID) references Assets(AssetsID),
    foreign key (PersonID) references Person(PersonID)
);
