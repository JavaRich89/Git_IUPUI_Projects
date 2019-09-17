SELECT hotel.hotelName FROM hotel, location WHERE hotel.locID = location.locID AND hotel.hotelName LIKE '%Beijing%';

SELECT hotel.hotelName FROM hotel IN(SELECT COUNT(rating) from rating where rating >= 3);

SELECT hotelID, ROUND(SUM(rating)/COUNT(rating), 1) AS AVG from rating where catName = 'Cleanliness' group by hotelID;

Select hotelID, rating from rating where rating > 1;

SELECT hotelName FROM AVG_RATING WHERE city = 'New delhi';
select * from location where country = 'India';

SELECT hotelName FROM AVG_RATING WHERE city = 'Beijing' AND overall > 2 AND cleanliness > 2;

select * from avg_rating;
truncate table avg_rating;

SELECT location.city from hotel, location where hotel.hotelID = 1 and hotel.locID = location.locID;

-- Overall Rating --
SELECT ROUND(SUM(rating)/COUNT(rating), 1) AS AVG from rating where hotelID = 1;

-- get rating of each category based on hotelID --
SELECT ROUND(SUM(rating)/COUNT(rating), 1) from rating where hotelID = 1 and catName = 'Cleanliness';

select TRUNC((SUM(rating)/COUNT(catName)), 1) AS Rating from rating where hotelID = 1 and catName = 'Price';

-- get hotelID --
SELECT hotelID FROM hotel where hotelName like 'Aloft Beijing Haidian';
-- overall --
select TRUNC((SUM(rating)/6), 1) AS overAll from rating where hotelID = 1;
-- price --
select TRUNC((SUM(rating)/COUNT(catName)), 1) AS priceRating from rating where hotelID = 1 and catName = 'Price';
-- cleanliness --
select TRUNC((SUM(rating)/COUNT(catName)), 1) AS cleanRating from rating where hotelID = 1 and catName = 'Cleanliness';
-- location --
select TRUNC((SUM(rating)/COUNT(catName)), 1) AS locRating from rating where hotelID = 1 and catName = 'Location';
-- Amenities --
select TRUNC((SUM(rating)/COUNT(catName)), 1) AS amenRating from rating where hotelID = 1 and catName = 'Amenities';
-- Service --
select TRUNC((SUM(rating)/COUNT(catName)), 1) AS servRating from rating where hotelID = 1 and catName = 'Service';
-- Room --
select TRUNC((SUM(rating)/COUNT(catName)), 1) AS roomRating from rating where hotelID = 1 and catName = 'Room';

-- search by rating --
select TRUNC((SUM(rating)/6), 1) AS overAll from rating where hotelID = (SELECT );

select * from rating where hotelID =1 and catName like 'Cleanliness';
select * from rating;

select hotelName from hotel where hotelName LIKE '%Beijing%';

select * from hotel;

ALTER TABLE rating
  ADD catName varchar2(45);

drop table category;
drop table seed;

drop sequence SEEDID_SQ;
drop sequence CATID_SQ;

select category.catID
from category
where (select seed.seedID from seed where seed.seedID = 2);

SELECT category.catID FROM category
WHERE seedID IN (SELECT seedID FROM seed where seedID = 5);


select * from category;

select * from seed;

-- HOTEL -- 
select * from hotel;

ALTER TABLE hotel
DROP CONSTRAINT fk_locID;

drop table hotel;

drop sequence hotelID_SQ;

-- LOCATION -- 
select * from location;
drop table location;

drop sequence locationID_SQ;

-- REVIEW --
select * from review;

ALTER TABLE review
DROP CONSTRAINT fk_hotelID;

drop table review;

drop sequence reviewID_SQ;

-- RATING -- 
select * from rating;

ALTER TABLE rating
DROP CONSTRAINT fk_hotel_ID;

drop table rating;

drop sequence ratingID_SQ;

-- jdbc scripts --
SELECT hotel.hotelName, location.city FROM hotel, location WHERE hotel.hotelName LIKE '%Beijing%' AND hotel.locID = location.locID;
SELECT hotel.hotelName, location.city FROM hotel, location WHERE hotel.locID = location.locID AND location.country = 'China';

-- TRUNCATE DATABASE --
ALTER TABLE review
DROP CONSTRAINT fk_hotelID;
truncate table review;
drop sequence reviewID_SQ;
ALTER TABLE rating
DROP CONSTRAINT fk_hotel_ID;
truncate table rating;
drop sequence ratingID_SQ;
ALTER TABLE hotel
DROP CONSTRAINT fk_locID;
truncate table hotel;
drop sequence hotelID_SQ;
truncate table location;
drop sequence locationID_SQ;
truncate table avg_rating;
drop sequence AVG_RATINGID_SQ;

-- re add constraints --
ALTER TABLE hotel
ADD CONSTRAINT fk_locID
  FOREIGN KEY (locID)
  REFERENCES location(locID);
ALTER TABLE review
ADD CONSTRAINT fk_hotelID
  FOREIGN KEY (hotelID)
  REFERENCES hotel(hotelID);
ALTER TABLE rating
ADD CONSTRAINT fk_hotel_ID
  FOREIGN KEY (hotelID)
  REFERENCES hotel(hotelID);

