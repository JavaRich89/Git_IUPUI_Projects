--REVIEW--
create table REVIEW 
    (
        reviewID number (10),
        hotelID number (10),
        postDate DATE,
        postTitle VARCHAR2 (100),
        postContent CLOB,
        CONSTRAINT reviewID_pk PRIMARY KEY (reviewID),
        CONSTRAINT fk_hotelID
            FOREIGN KEY (hotelID)
                REFERENCES HOTEL(hotelID)
    );
    
--Sequence for reviewID--
create sequence ReviewID_SQ
    start with 1
    increment by 1
    nomaxvalue
    minvalue 1
    nocache
    order;
    
commit;

--RATING--
create table RATING 
    (
        ratingID number (10),
        rating number (10),
        hotelID number (10),
        catName varchar2 (30),
        CONSTRAINT ratingID_pk PRIMARY KEY (ratingID),
        CONSTRAINT fk_hotel_ID
            FOREIGN KEY (hotelID)
                REFERENCES HOTEL(hotelID)
    );

    
--Sequence for hotelID--
create sequence RatingID_SQ
    start with 1
    increment by 1
    nomaxvalue
    minvalue 1
    nocache
    order;
    
commit;

-- Average Rating Table --
create table AVG_RATING 
    (
        avg_ratingID number (10),
        hotelName varchar2(100),
        city varchar2(100),
        overall number(10),
        price number(10),
        cleanliness number(10),
        location number(10),
        amenities number(10),
        service number(10),
        room number(10),
        CONSTRAINT avg_ratingID_pk PRIMARY KEY (avg_ratingID)
    );
    
--Sequence for avg_ratingID--
create sequence avg_ratingID_SQ
    start with 1
    increment by 1
    nomaxvalue
    minvalue 1
    nocache
    order;
    
commit;
