--LOAD DIMENSIONS--
-----------------------------------------------------------------------------------------------------------------------------------------------------------
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER PROCEDURE Load_Dim
AS
	DECLARE inputRow CURSOR FOR
		SELECT DISTINCT IntakeType, IntakeSubType, ReproductiveStatusAtIntake, IntakeInternalStatus, IntakeAsilomarStatus, IntakeReason, IntakeDate, 
			OutcomeType, OutcomeSubtype, ReproductiveStatusAtOutcome, OutcomeAsilomarStatus, OutcomeDate, 
			AnimalType, PrimaryBreed, Gender, PrimaryColor, SecondaryColor From Staging;

	DECLARE @Intake_Type varchar(50);
	DECLARE @Intake_Sub_Type varchar(50);
	DECLARE @Intake_Fertility varchar(50);
	DECLARE @Intake_Status varchar(50);
	DECLARE @Intake_Health varchar(50);
	DECLARE @Intake_Reason varchar(50);

	DECLARE @Outcome_Type varchar(50);
	DECLARE @Outcome_Sub_Type varchar(50);
	DECLARE @Outcome_Fertility varchar(50);
	DECLARE @Outcome_Health varchar(50);

	DECLARE @Animal_Type varchar(50);
	DECLARE @Primary_Breed varchar(100);
	DECLARE @Gender varchar(50);
	DECLARE @Primary_Color varchar(50);
	DECLARE @Secondary_Color varchar(50);

	DECLARE @Intake_Date date;
	DECLARE @Outcome_Date date;

BEGIN
	OPEN inputRow
	FETCH NEXT FROM inputRow INTO @Intake_Type, @Intake_Sub_Type, @Intake_Fertility, @Intake_Status, @Intake_Health, @Intake_Reason, @Intake_Date,
		@Outcome_Type, @Outcome_Sub_Type, @Outcome_Fertility, @Outcome_Health, @Outcome_Date,
		@Animal_Type, @Primary_Breed, @Gender, @Primary_Color, @Secondary_Color

	WHILE @@FETCH_STATUS = 0
	BEGIN
		--Check NOT EXIST THEN INSERT--


		--INTAKE TYPE--
		IF NOT EXISTS(SELECT Intake_Type FROM Intake_Type_Dim WHERE Intake_Type = @Intake_Type)
		BEGIN
			INSERT INTO Intake_Type_Dim VALUES (@Intake_Type);
		END 
		--INTAKE SUB TYPE--
		IF NOT EXISTS(SELECT Intake_Sub_Type FROM Intake_Sub_Type_Dim WHERE Intake_Sub_Type = @Intake_Sub_Type)
		BEGIN
			INSERT INTO Intake_Sub_Type_Dim VALUES (@Intake_Sub_Type);
		END 
		--INTAKE FERTILITY--
		IF NOT EXISTS(SELECT Intake_Fertility FROM Intake_Fertility_Dim WHERE Intake_Fertility = @Intake_Fertility)
		BEGIN
			INSERT INTO Intake_Fertility_Dim VALUES (@Intake_Fertility);
		END 
		--INTAKE STATUS--
		IF NOT EXISTS(SELECT Intake_Status FROM Intake_Status_Dim WHERE Intake_Status = @Intake_Status)
		BEGIN
			INSERT INTO Intake_Status_Dim VALUES (@Intake_Status);
		END 
		--INTAKE HEALTH--
		IF NOT EXISTS(SELECT Intake_Health FROM Intake_Health_Dim WHERE Intake_Health = @Intake_Health)
		BEGIN
			INSERT INTO Intake_Health_Dim VALUES (@Intake_Health);
		END 
		--INTAKE REASON--
		IF NOT EXISTS(SELECT Intake_Reason FROM Intake_Reason_Dim WHERE Intake_Reason = @Intake_Reason)
		BEGIN
			INSERT INTO Intake_Reason_Dim VALUES (@Intake_Reason);
		END 
		--INTAKE DATE--
		IF NOT EXISTS(SELECT Intake_Date FROM Intake_Date_Dim WHERE Intake_Date = @Intake_Date)
		BEGIN
			INSERT INTO Intake_Date_Dim VALUES (@Intake_Date);
		END

		--OUTCOME TYPE--
		IF NOT EXISTS(SELECT Outcome_Type FROM Outcome_Type_Dim WHERE Outcome_Type = @Outcome_Type)
		BEGIN
			INSERT INTO Outcome_Type_Dim VALUES (@Outcome_Type);
		END 
		--OUTCOME SUB TYPE--
		IF NOT EXISTS(SELECT Outcome_Sub_Type FROM Outcome_Sub_Type_Dim WHERE Outcome_Sub_Type = @Outcome_Sub_Type)
		BEGIN
			INSERT INTO Outcome_Sub_Type_Dim VALUES (@Outcome_Sub_Type);
		END 
		--OUTCOME FERTILITY--
		IF NOT EXISTS(SELECT Outcome_Fertility FROM Outcome_Fertility_Dim WHERE Outcome_Fertility = @Outcome_Fertility)
		BEGIN
			INSERT INTO Outcome_Fertility_Dim VALUES (@Outcome_Fertility);
		END
		--OUTCOME HEALTH--
		IF NOT EXISTS(SELECT Outcome_Health FROM Outcome_Health_Dim WHERE Outcome_Health = @Outcome_Health)
		BEGIN
			INSERT INTO Outcome_Health_Dim VALUES (@Outcome_Health);
		END 
		--OUTCOME DATE--
		IF NOT EXISTS(SELECT Outcome_Date FROM Outcome_Date_Dim WHERE Outcome_Date = @Outcome_Date)
		BEGIN
			INSERT INTO Outcome_Date_Dim VALUES (@Outcome_Date);
		END

		--ANIMAL TYPE--
		IF NOT EXISTS(SELECT Animal_Type FROM Animal_Type_Dim WHERE Animal_Type = @Animal_Type)
		BEGIN
			INSERT INTO Animal_Type_Dim VALUES (@Animal_Type);
		END 
		--PRIMARY BREED--
		IF NOT EXISTS(SELECT Primary_Breed FROM Primary_Breed_Dim WHERE Primary_Breed = @Primary_Breed)
		BEGIN
			INSERT INTO Primary_Breed_Dim VALUES (@Primary_Breed);
		END 
		--GENDER--
		IF NOT EXISTS(SELECT Gender FROM Gender_Dim WHERE Gender = @Gender)
		BEGIN
			INSERT INTO Gender_Dim VALUES (@Gender);
		END 
		--PRIMARY COLOR--
		IF NOT EXISTS(SELECT Primary_Color FROM Primary_Color_Dim WHERE Primary_Color = @Primary_Color)
		BEGIN
			INSERT INTO Primary_Color_Dim VALUES (@Primary_Color);
		END 
		--SECONDARY COLOR--
		IF NOT EXISTS(SELECT Secondary_Color FROM Secondary_Color_Dim WHERE Secondary_Color = @Secondary_Color)
		BEGIN
			INSERT INTO Secondary_Color_Dim VALUES (@Secondary_Color);
		END 



		FETCH NEXT FROM inputRow INTO @Intake_Type, @Intake_Sub_Type, @Intake_Fertility, @Intake_Status, @Intake_Health, @Intake_Reason, @Intake_Date,
			@Outcome_Type, @Outcome_Sub_Type, @Outcome_Fertility, @Outcome_Health, @Outcome_Date, 
			@Animal_Type, @Primary_Breed, @Gender, @Primary_Color, @Secondary_Color
	END;
	CLOSE inputRow
	DEALLOCATE inputRow
END




--LOAD SHELTER DIMENSIONS--
-----------------------------------------------------------------------------------------------------------------------------------------------------------
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER PROCEDURE Load_Shelter_Dim
AS
	DECLARE inputRow CURSOR FOR
		SELECT DISTINCT ShelterName, ShelterLocation FROM Staging_Shelter;

	DECLARE @Shelter_Name varchar(100);
	DECLARE @Shelter_Location varchar(100);

BEGIN
	OPEN inputRow
	FETCH NEXT FROM inputRow INTO @Shelter_Name, @Shelter_Location

	WHILE @@FETCH_STATUS = 0
	BEGIN
		IF NOT EXISTS(SELECT Shelter_Name FROM Shelter_Dim WHERE Shelter_Name = @Shelter_Name)
		BEGIN
			INSERT INTO Shelter_Dim VALUES (@Shelter_Name, @Shelter_Location);
		END 

		FETCH NEXT FROM inputRow INTO @Shelter_Name, @Shelter_Location
	END;
	CLOSE inputRow
	DEALLOCATE inputRow
END;



--LOAD FACTS--
------------------------------------------------------------------------------------------------------------------------------------------------------------
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER PROCEDURE Load_Fact
AS
	DECLARE inputRow CURSOR FOR
		SELECT IntakeType, IntakeSubType, ReproductiveStatusAtIntake, IntakeInternalStatus, IntakeAsilomarStatus, IntakeReason, IntakeDate,
			OutcomeType, OutcomeSubtype, ReproductiveStatusAtOutcome, OutcomeAsilomarStatus, OutcomeDate,
			AnimalID, AnimalType, PrimaryBreed, Gender, PrimaryColor, SecondaryColor, DOB From Staging;
	
	--INTAKE--
	DECLARE @Intake_Type varchar(50);
	DECLARE @Intake_Type_ID int;
	DECLARE @Intake_Sub_Type varchar(50);
	DECLARE @Intake_Sub_Type_ID int;
	DECLARE @Intake_Fertility varchar(50);
	DECLARE @Intake_Fertility_ID int;
	DECLARE @Intake_Status varchar(50);
	DECLARE @Intake_Status_ID int;
	DECLARE @Intake_Health varchar(50);
	DECLARE @Intake_Health_ID int;
	DECLARE @Intake_Reason varchar(50);
	DECLARE @Intake_Reason_ID int;
	DECLARE @Intake_Date date;

	--OUTCOME--
	DECLARE @Outcome_Type varchar(50);
	DECLARE @Outcome_Type_ID int;
	DECLARE @Outcome_Sub_Type varchar(50);
	DECLARE @Outcome_Sub_Type_ID int;
	DECLARE @Outcome_Fertility varchar(50);
	DECLARE @Outcome_Fertility_ID int;
	DECLARE @Outcome_Health varchar(50);
	DECLARE @Outcome_Health_ID int;
	DECLARE @Outcome_Date date;

	--ANIMAL--
	DECLARE @Animal_Type varchar(50);
	DECLARE @Animal_Type_ID int;
	DECLARE @Primary_Breed varchar(100);
	DECLARE @Primary_Breed_ID int;
	DECLARE @Gender varchar(50);
	DECLARE @Gender_ID int;
	DECLARE @Primary_Color varchar(50);
	DECLARE @Primary_Color_ID int;
	DECLARE @Secondary_Color varchar(50);
	DECLARE @Secondary_Color_ID int;
	DECLARE @DOB date;

	--ANIMAL COST--
	DECLARE @Cost_Per_Day DECIMAL(5,2);

	--VISIT--
	DECLARE @Animal_ID varchar(50);
	DECLARE @Shelter_ID int;
	DECLARE @Animal_Cost_ID int;
	DECLARE @Intake_ID int;
	DECLARE @Outcome_ID int;

	DECLARE @Counter int;

BEGIN

	OPEN inputRow
	FETCH NEXT FROM inputRow INTO @Intake_Type, @Intake_Sub_Type, @Intake_Fertility, @Intake_Status, @Intake_Health, @Intake_Reason, @Intake_Date, 
		@Outcome_Type, @Outcome_Sub_Type, @Outcome_Fertility, @Outcome_Health, @Outcome_Date,
		@Animal_ID, @Animal_Type, @Primary_Breed, @Gender, @Primary_Color, @Secondary_Color, @DOB
	
	--Recursive Counter Check number of current visits and set starting point for each load--
	IF EXISTS(SELECT Visit_ID FROM Visit_Fact)
	BEGIN
		SELECT @Counter=COUNT(Visit_ID) FROM Visit_Fact;
		SET @Counter = @Counter + 1;
	END
	ELSE
	BEGIN
		SET @Counter = 1;
	END

	WHILE @@FETCH_STATUS = 0
	BEGIN
		--INTAKE--
		SELECT @Intake_Type_ID=Intake_Type_ID from Intake_Type_Dim where Intake_Type = @Intake_Type;
		SELECT @Intake_Sub_Type_ID=Intake_Sub_Type_ID from Intake_Sub_Type_Dim where Intake_Sub_Type = @Intake_Sub_Type;
		SELECT @Intake_Fertility_ID=Intake_Fertility_ID from Intake_Fertility_Dim where Intake_Fertility = @Intake_Fertility;
		SELECT @Intake_Status_ID=Intake_Status_ID from Intake_Status_Dim where Intake_Status = @Intake_Status;
		SELECT @Intake_Health_ID=Intake_Health_ID from Intake_Health_Dim where Intake_Health = @Intake_Health;
		SELECT @Intake_Reason_ID=Intake_Reason_ID from Intake_Reason_Dim where Intake_Reason = @Intake_Reason;
		SELECT @Intake_Date=Intake_Date from Intake_Date_Dim where Intake_Date=@Intake_Date;
		INSERT INTO Intake_Fact VALUES (@Intake_Type_ID, @Intake_Sub_Type_ID, @Intake_Fertility_ID, @Intake_Status_ID, @Intake_Health_ID, @Intake_Reason_ID, @Intake_Date);

		--OUTCOME--
		SELECT @Outcome_Type_ID=Outcome_Type_ID from Outcome_Type_Dim where Outcome_Type = @Outcome_Type;
		SELECT @Outcome_Sub_Type_ID=Outcome_Sub_Type_ID from Outcome_Sub_Type_Dim where Outcome_Sub_Type = @Outcome_Sub_Type;
		SELECT @Outcome_Fertility_ID=Outcome_Fertility_ID from Outcome_Fertility_Dim where Outcome_Fertility = @Outcome_Fertility;
		SELECT @Outcome_Health_ID=Outcome_Health_ID from Outcome_Health_Dim where Outcome_Health = @Outcome_Health;
		SELECT @Outcome_Date=Outcome_Date from Outcome_Date_Dim where Outcome_Date=@Outcome_Date;
		INSERT INTO Outcome_Fact VALUES (@Outcome_Type_ID, @Outcome_Sub_Type_ID, @Outcome_Fertility_ID, @Outcome_Health_ID, @Outcome_Date);

		--ANIMAL--
		SELECT @Animal_Type_ID=Animal_Type_ID from Animal_Type_Dim where Animal_Type = @Animal_Type;
		IF NOT EXISTS(SELECT Animal_ID FROM Animal_Fact WHERE Animal_ID = @Animal_ID)
		BEGIN
			SELECT @Primary_Breed_ID=Primary_Breed_ID from Primary_Breed_Dim where Primary_Breed = @Primary_Breed;
			SELECT @Gender_ID=Gender_ID from Gender_Dim where Gender = @Gender;
			SELECT @Primary_Color_ID=Primary_Color_ID from Primary_Color_Dim where Primary_Color = @Primary_Color;
			SELECT @Secondary_Color_ID=Secondary_Color_ID from Secondary_Color_Dim where Secondary_Color = @Secondary_Color;
			INSERT INTO Animal_Fact VALUES (@Animal_ID, @Animal_Type_ID, @Primary_Breed_ID, @Gender_ID, @Primary_Color_ID, @Secondary_Color_ID, @DOB);
		END

		--ANIMAL COST--
		IF NOT EXISTS(SELECT Animal_Type_ID FROM Animal_Cost_Fact WHERE Animal_Type_ID=@Animal_Type_ID)
		BEGIN
			--Calculate Cost_Per_Day based on animal type--
			SELECT @Cost_Per_Day=(CASE
									WHEN Animal_Type = 'BIRD' THEN 1.65
									WHEN Animal_Type = 'CAT' THEN 2.25 
									WHEN Animal_Type = 'DOG' THEN 3.10  
									WHEN Animal_Type = 'FERRET' THEN 1.95 
									WHEN Animal_Type = 'LIVESTOCK' THEN 4.00
									WHEN Animal_Type = 'OTHER' THEN 1.85
									WHEN Animal_Type = 'RABBIT' THEN 1.40
									WHEN Animal_Type = 'REPTILE' THEN 2.35
									WHEN Animal_Type = 'RODENT' THEN 1.45
									ELSE 0
									END)
			FROM Animal_Type_Dim WHERE Animal_Type_ID=@Animal_Type_ID;

			INSERT INTO Animal_Cost_Fact VALUES (@Animal_Type_ID, @Cost_Per_Day);
		END;

		--Generate Random Shelter--
		SELECT @Shelter_ID=FLOOR(RAND()*(10-1)+1);

		--VISIT--
		SELECT @Animal_Cost_ID=Animal_Cost_ID from Animal_Cost_Fact where Animal_Type_ID=@Animal_Type_ID;
		SELECT @Intake_ID=Intake_ID from Intake_Fact where Intake_ID=@Counter;
		SELECT @Outcome_ID=Outcome_ID from Outcome_Fact where Outcome_ID=@Counter;
		INSERT INTO Visit_Fact VALUES (@Animal_ID, @Shelter_ID, @Animal_Cost_ID, @Intake_ID, @Outcome_ID);

		--Increment Counter--
		SET @Counter = @Counter + 1;

		FETCH NEXT FROM inputRow INTO @Intake_Type, @Intake_Sub_Type, @Intake_Fertility, @Intake_Status, @Intake_Health, @Intake_Reason, @Intake_Date,  
			@Outcome_Type, @Outcome_Sub_Type, @Outcome_Fertility, @Outcome_Health, @Outcome_Date, 
			@Animal_ID, @Animal_Type, @Primary_Breed, @Gender, @Primary_Color, @Secondary_Color, @DOB

	END;
	CLOSE inputRow
	DEALLOCATE inputRow

END;

