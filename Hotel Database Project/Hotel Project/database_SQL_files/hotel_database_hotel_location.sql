--LOCATION--
create table LOCATION 
    (
        locID INTEGER,
        country varchar2(100) NOT NULL,
        state varchar2(100),
        city varchar2(100),
        CONSTRAINT locID_pk PRIMARY KEY (locID)
    );
    
--Sequence for locationID--
create sequence LocationID_SQ
    start with 1
    increment by 1
    nomaxvalue
    minvalue 1
    nocache
    order;
    
commit;

--HOTEL--
create table HOTEL 
    (
        hotelID INTEGER,
        locID INTEGER NOT NULL,
        hotelName varchar2(100) NOT NULL,
        CONSTRAINT hotelID_pk PRIMARY KEY (hotelID),
        CONSTRAINT fk_locID
            FOREIGN KEY (locID)
                REFERENCES LOCATION(locID)
    );
    
--Sequence for hotelID--
create sequence HotelID_SQ
    start with 1
    increment by 1
    nomaxvalue
    minvalue 1
    nocache
    order;
    
commit;


