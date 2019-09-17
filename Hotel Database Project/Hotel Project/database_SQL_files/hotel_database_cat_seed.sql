--SEED TABLE--
create table SEED
    (
        seedID number NOT NULL,
        seedWord varchar2(50) NOT NULL,
        polarity NUMBER(10) NOT NULL,
        CONSTRAINT Seed_ID_pk PRIMARY KEY (seedID)
    );

--Seed ID sequence--   
create sequence SeedID_SQ
    start with 1
    increment by 1
    nomaxvalue
    minvalue 1
    nocache
    order;

--Category Table--    
create table CATEGORY 
    (
        catID INTEGER,
        seedID INTEGER,
        catName varchar2(50) NOT NULL,
        CONSTRAINT catID_pk PRIMARY KEY (catID),
        CONSTRAINT fk_seedID
            FOREIGN KEY (seedID)
                REFERENCES Seed(seedID)
    );
    
--category ID sequence--   
create sequence CatID_SQ
    start with 1
    increment by 1
    nomaxvalue
    minvalue 1
    nocache
    order;
    
commit;
