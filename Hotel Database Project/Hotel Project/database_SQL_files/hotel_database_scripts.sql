--IMPORT SEED.CSV--
--FILE POINTER--
create directory SEED_CSV as 'C:\Users\The Rich Family\eclipse-workspace\Hotel Project\csvoutput';

commit;

--IMPORT SEED SCRIPT--
CREATE OR REPLACE PROCEDURE importSeed
AS 
      F UTL_FILE.FILE_TYPE;
      V_LINE VARCHAR2 (20000);
      V_SeedID number (10);
      V_CatName VARCHAR2 (15);
      V_CatID number (10);
      V_CatIDname number(10);
      V_SeedWord VARCHAR2 (60);
      V_Polarity number (1);
BEGIN
      F := UTL_FILE.FOPEN ('SEED_CSV', 'seed.csv', 'R', 20000);
      IF UTL_FILE.IS_OPEN(F) THEN
        LOOP
          BEGIN
--            DBMS_OUTPUT.PUT_LINE('Start');
            UTL_FILE.GET_LINE(F, V_LINE);
--            DBMS_OUTPUT.PUT_LINE(V_LINE);
            IF V_LINE IS NULL THEN
              EXIT;
            END IF;
            --grab inputs--
            V_SeedID := SeedID_SQ.NEXTVAL;
            V_CatID := CatID_SQ.NEXTVAL;
            V_catIDname := TO_NUMBER(REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 1));
            IF V_CatIDname = 1 THEN
                V_CatName := 'Price';
            ELSIF V_CatIDname = 2 THEN
                V_CatName := 'Cleanliness';
            ELSIF V_CatIDname = 3 THEN
                V_CatName := 'Location';
            ELSIF V_CatIDname = 4 THEN
                V_CatName := 'Amenities';
            ELSIF V_CatIDname = 5 THEN
                V_CatName := 'Service';
            ELSIF V_CatIDname = 6 THEN
                V_CatName := 'Room';
            ELSE
                V_CatName := 'NA';
            END IF;
            V_SeedWord := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 2);
            V_Polarity := TO_NUMBER(REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 3)); 
            
            INSERT INTO SEED VALUES(V_SeedID, V_SeedWord, V_Polarity);
            INSERT INTO CATEGORY VALUES(V_CatID, V_SeedID, V_CatName);
            
            COMMIT;
          EXCEPTION
          WHEN NO_DATA_FOUND THEN
            EXIT;
          END;
        END LOOP;
      END IF;
      UTL_FILE.FCLOSE(F);
END;


--IMPORT REVIEW DATA--
--FILE POINTER--
--ADD AFTER COMBINED WITH JAVA--
create or replace directory REVIEWCSV as 'C:\Users\The Rich Family\eclipse-workspace-FX\Hotel Project\csvoutput';

commit;

--IMPORT REVIEW SCRIPT--
SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE importReviews
AS 
      F UTL_FILE.FILE_TYPE;
      V_LINE CLOB;
      -- HOTEL TABLE VARIABLES --
      V_HotelID number (10); -- calculated or generated
      V_HotelName VARCHAR2 (100);
      -- LOCATION TABLE VARIABLES --
      V_LocationID number (10); -- calculated or generated
      V_Country VARCHAR2 (100);
      V_State VARCHAR2 (100);
      V_City VARCHAR2 (100);
      -- REVIEW TABLE VARIABLES --
      V_ReviewID NUMBER (10); -- calculated or generated
      V_PostDate DATE;
      V_PostTitle VARCHAR2 (100);
      V_Content1 VARCHAR2 (32000);
      V_Content2 VARCHAR2 (32000);
      V_Content3 VARCHAR2 (32000);
      V_Content4 VARCHAR2 (32000);
      V_PostContent CLOB;
      -- RATING TABLE VARIABLES --
      V_RatingID NUMBER (10); -- calculated or generated
      V_Rating NUMBER (10); -- calculated
      V_CatName VARCHAR2 (25); -- calculated
      Ratings ratingsArray := ratingsArray();
      -- Misc Variables --
      i number(1);
      found NUMBER(1);
BEGIN
        -- OPEN FILE --
      F := UTL_FILE.FOPEN ('REVIEWCSV', 'reviews_data.csv', 'R', 32000);
      IF UTL_FILE.IS_OPEN(F) THEN
        LOOP
          BEGIN
            UTL_FILE.GET_LINE(F, V_LINE);
            IF V_LINE IS NULL THEN
              EXIT;
            END IF;
            
            -------------------------- FOR EACH LINE IN CSV FILE --------------------------
            
            -- GRAB INPUT VARIABLES FROM CSV FILE ---------------------------------------------------
            V_Country := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 1);
            V_State := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 2);
            V_City := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 3);
            V_HotelName := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 4); 
            V_PostDate := TO_DATE(REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 5), 'MON-DD-YYYY');
            V_PostTitle := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 6);
            V_Content1 := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 7);
            V_Content2 := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 8);
            V_Content3 := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 9);
            V_Content4 := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 10);

            -- combine into clob --
            V_PostContent := V_Content1||V_Content2||V_Content3||V_Content4;
            
            
            -- INSERT INTO LOCATION ----------------------------------------------------------------
            --ignore if location already exists if not insert--
            found := 0;
            SELECT COUNT(*) INTO found FROM LOCATION WHERE City = V_City;
            -- if location does not exist, insert it as a new location --
            IF (found = 0) THEN
                V_LocationID := LocationID_SQ.NEXTVAL;
                INSERT INTO LOCATION VALUES(V_LocationID, V_Country, V_State, V_City);
            ELSE
                -- if location does exist, set V_LocationID to the location ID for that city/location --
                SELECT locID into V_LocationID from Location where city = V_City;
            END IF;
            ----------------------------------------------------------------------------------------
            
            -- INSERT INTO HOTEL ------------------------------------------------------------------
            --ignore if hotel already exists if not insert--
            found := 0;
            SELECT COUNT(*) INTO found FROM HOTEL WHERE hotelName = V_HotelName;
            IF (found = 0) THEN
                -- if hotel does not exist, insert it as a new hotel --
                V_HotelID := HotelID_SQ.NEXTVAL;
                INSERT INTO HOTEL VALUES(V_HotelID, V_LocationID, V_HotelName);
            ELSE
                -- if hotel does exist, set V_HotelID to the hotel ID for that hotel --
                SELECT hotelID into V_HotelID from hotel where hotelName = V_HotelName;
            END IF;
            -----------------------------------------------------------------------------------------
            
            
            -- INSERT INTO REVIEW ------------------------------------------------------------------
            V_ReviewID := ReviewID_SQ.NEXTVAL;
            INSERT INTO REVIEW VALUES(V_ReviewID, V_HotelID, V_PostDate, V_PostTitle, V_PostContent);
            ----------------------------------------------------------------------------------------
            
            -- INSERT INTO RATING ------------------------------------------------------------------
            Ratings := RatingCalc(V_PostContent);
            -- loop through ratings array and insert into rating table --
            FOR i IN 1..ratings.count LOOP
                -- get rating ID value from sequence --
                V_RatingID := RatingID_SQ.NEXTVAL;
                
                -- get category name based on index --
                IF ratings(i) IS NOT NULL THEN
                    -- Set V_CatName based on position in array --
                    CASE i
                        WHEN 1 THEN V_CatName := 'Price';
                        WHEN 2 THEN V_CatName := 'Cleanliness';
                        WHEN 3 THEN V_CatName := 'Location';
                        WHEN 4 THEN V_CatName := 'Amenities';
                        WHEN 5 THEN V_CatName := 'Service';
                        WHEN 6 THEN V_CatName := 'Room';
                    END CASE;
                    
                    -- INSERT INTO RATING --
                    INSERT INTO RATING VALUES(V_RatingID, ratings(i), V_HotelID, V_CatName);
                
                ELSE
                    -- Skip insert if ratings(i) is NULL --
                    CONTINUE;
                END IF;
            END LOOP;
            -----------------------------------------------------------------------------------------
            
            COMMIT;
          EXCEPTION
          WHEN NO_DATA_FOUND THEN EXIT;
          WHEN OTHERS THEN continue;
          END;
        END LOOP; 
      ELSE
        DBMS_OUTPUT.PUT_LINE('Did not open');
      END IF;
      UTL_FILE.FCLOSE(F);
      -- finally run avg_rating procedure --
      AVG_RATING_PROC;
END;

create or replace type ratingsArray IS VARRAY(6) OF NUMBER(10);
/

--RATING CALCULATION SCRIPT--
CREATE OR REPLACE FUNCTION RatingCalc (content IN CLOB) RETURN ratingsArray AS
    -- declare array --
    ratings ratingsArray := ratingsArray();
    -- declare cursor --
    CURSOR seedWordCursor IS
        SELECT seedID, seedWord, polarity from SEED;
    seed_row  seedWordCursor%ROWTYPE;
    
    -- Calculating category --
    catName VARCHAR2(50); -- placeholder for comparison for identifying category changes
    currentCatName VARCHAR2(50); -- current category name of current seed word
    catCounter NUMBER(10); -- starts at 1 then incremented when cat changes. used to assign totalRating to category total rating
    
    -- Calculating seed Words --
    wordCount NUMBER(10); -- count of how many of the current seed word exists in the post content
    currentPolarity NUMBER(10); -- polarity of the current seed word
    
    -- Calculating Ratings --
    rating NUMBER(10); -- per seed word found
    totalRating NUMBER(10); -- total rating placeholder before set to a category total
    
    -- Array input variables, totals per category are returned in order --
    priceTotal NUMBER(10);
    cleanTotal NUMBER(10);
    locTotal NUMBER(10);
    amenTotal NUMBER(10);
    servTotal NUMBER(10);
    roomTotal NUMBER(10);
BEGIN
    -- set catName and catCounter to first category --
    catName := 'Price';
    catCounter := 1;
    
    -- loop through the seed word cursor, search post for each word, count how many of each word, calculate rating by cat --
    FOR seed_row in seedWordCursor
        LOOP
        EXIT WHEN seedWordCursor%NOTFOUND;
        
        -- Find current seed word in the post --
        wordCount := 0; -- reset wordCount
        wordCount := REGEXP_COUNT(content, seed_row.seedWord, 1, 'i');
        
        IF wordCount > 0 THEN
            
            -- grab polarity of seed word --
            currentPolarity := seed_row.polarity;
            
            -- grab catID and catName--
            SELECT catName into currentCatName FROM category where seedID = seed_row.seedID;
            
            -- check for category changes, if changed, update catName placeholder and switch to new total --
            IF currentCatName != catName THEN
                -- change category to current category --
                catName := currentCatName;
                -- set category total based on the category counter --
                dbms_output.put_line('CATEGORY TOTAL');
                IF catCounter = 1 THEN
                    priceTotal := totalRating;
                    --dbms_output.put_line(priceTotal);
                ELSIF catCounter = 2 THEN
                    cleanTotal := totalRating;
                    --dbms_output.put_line(cleanTotal);
                ELSIF catCounter = 3 THEN
                    locTotal := totalRating;
                    --dbms_output.put_line(locTotal);
                ELSIF catCounter = 4 THEN
                    amenTotal := totalRating;
                    --dbms_output.put_line(amenTotal);
                ELSIF catCounter = 5 THEN
                    servTotal := totalRating;
                    --dbms_output.put_line(servTotal);
                ELSIF catCounter = 6 THEN
                    roomTotal := totalRating;
                    --dbms_output.put_line(roomTotal);
                ELSE
                    dbms_output.put_line('----NOT FOUND----');
                END IF;
                
                -- increment category counter --
                catCounter := catCounter + 1;
                
                -- reset total rating --
                totalRating := 0;
            END IF;
            
            -- Calculate rating then apply to the total for that category --
            rating := currentPolarity * wordCount;
            totalRating := totalRating + rating;

        END IF;
    END LOOP;
    
    -- After all seed words have been ran, insert array variables into array and return the array --
    ratings := ratingsArray (priceTotal, cleanTotal, locTotal, amenTotal, servTotal, roomTotal);
    
    /*dbms_output.put_line('---RATINGS ARRAY---');
    for i in 1..ratings.count loop
       dbms_output.put_line(ratings(i));
    end loop;*/
   
    return ratings;
END;

-- Overall Rating Procedure --
create or replace procedure AVG_Rating_Proc AS
    V_overall number(10);
    V_price number(10);
    V_cleanliness number(10);
    V_location number(10);
    V_amenities number(10);
    V_service number(10);
    V_room number(10);
    V_avg_ratingID number(10);
    V_hotelName varchar2(100);
    V_city varchar2(100);
    hotel_counter number(10);
BEGIN
    -- get total number of hotels --
    SELECT COUNT(hotelID) into hotel_counter from hotel;
    -- loop through ratings per hotel id to retrieve average ratings and insert into avg_rating table --
    FOR i IN 1..hotel_counter LOOP
        -- get hotel name --
        SELECT hotelName into V_hotelName from hotel where hotelID = i;
        -- get city name --
        SELECT location.city into V_city from hotel, location where hotel.hotelID = i and hotel.locID = location.locID;
        -- get overall rating --
        SELECT ROUND(SUM(rating)/COUNT(rating), 1) into V_overall from rating where hotelID = i;
        -- get each avg rating --
        SELECT ROUND(SUM(rating)/COUNT(rating), 1) into V_price from rating where hotelID = i and catName = 'Price';
        SELECT ROUND(SUM(rating)/COUNT(rating), 1) into V_cleanliness from rating where hotelID = i and catName = 'Cleanliness';
        SELECT ROUND(SUM(rating)/COUNT(rating), 1) into V_location from rating where hotelID = i and catName = 'Location';
        SELECT ROUND(SUM(rating)/COUNT(rating), 1) into V_amenities from rating where hotelID = i and catName = 'Amenities';
        SELECT ROUND(SUM(rating)/COUNT(rating), 1) into V_service from rating where hotelID = i and catName = 'Service';
        SELECT ROUND(SUM(rating)/COUNT(rating), 1) into V_room from rating where hotelID = i and catName = 'Room';
        -- insert row into avg_rating table --
        V_avg_ratingID := avg_ratingID_SQ.NEXTVAL;
        INSERT INTO AVG_RATING VALUES(V_avg_ratingID, V_hotelName, V_city, V_overall, V_price, V_cleanliness, V_location, V_amenities, V_service, V_room);
    END LOOP;
END;

BEGIN
    AVG_RATING_PROC;
END;
    
