--CREATE DATABASE--
----------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE DATABASE AnimalShelter;
GO
USE AnimalShelter;
GO
--STAGING--
-----------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE Staging(
	AnimalID varchar(50) NOT NULL,
	AnimalType varchar(50) NOT NULL,
	IntakeDate datetime NOT NULL,
	IntakeType varchar(50) NOT NULL,
	IntakeSubType varchar(50) NOT NULL,
	PrimaryColor varchar(50) NOT NULL,
	SecondaryColor varchar(50) NOT NULL,
	PrimaryBreed varchar(100) NOT NULL,
	Gender varchar(50) NOT NULL,
	DOB date NOT NULL,
	IntakeReason varchar(50) NOT NULL,
	IntakeInternalStatus varchar(50) NOT NULL,
	IntakeAsilomarStatus varchar(50) NOT NULL,
	ReproductiveStatusAtIntake varchar(50) NOT NULL,
	OutcomeDate datetime NOT NULL,
	OutcomeType varchar(50) NOT NULL,
	OutcomeSubtype varchar(50) NOT NULL,
	OutcomeAsilomarStatus varchar(50) NOT NULL,
	ReproductiveStatusAtOutcome varchar(50) NOT NULL
) ON [PRIMARY]


--STAGING SHELTER--
-----------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE Staging_Shelter(
	Shelter_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ShelterName varchar(100) NOT NULL,
	ShelterLocation varchar(50) NOT NULL
) ON [PRIMARY]



--ANIMAL TABLES--
----------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE Animal_Type_Dim(
	Animal_Type_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Animal_Type varchar(50) NOT NULL
) ON [PRIMARY]

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Primary_Breed_Dim(
	Primary_Breed_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Primary_Breed varchar(50) NOT NULL
) ON [PRIMARY]

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Gender_Dim(
	Gender_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Gender varchar(50) NOT NULL
) ON [PRIMARY]

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Primary_Color_Dim(
	Primary_Color_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Primary_Color varchar(50) NOT NULL
) ON [PRIMARY]

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Secondary_Color_Dim(
	Secondary_Color_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Secondary_Color varchar(50) NOT NULL
) ON [PRIMARY]

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Animal_Cost_Fact(
	Animal_Cost_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Animal_Type_ID int NOT NULL,
	Cost_Per_Day decimal(5,2) NOT NULL
) ON [PRIMARY]

ALTER TABLE Animal_Cost_Fact
ADD FOREIGN KEY (Animal_Type_ID) REFERENCES Animal_Type_Dim(Animal_Type_ID);

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Animal_Fact(
	Animal_ID varchar(50) PRIMARY KEY NOT NULL,
	Animal_Type_ID int NOT NULL,
	Primary_Breed_ID int NOT NULL,
	Gender_ID int NOT NULL,
	Primary_Color_ID int NOT NULL,
	Secondary_Color_ID int NOT NULL,
	DOB date
) ON [PRIMARY]

ALTER TABLE Animal_Fact
ADD FOREIGN KEY (Animal_Type_ID) REFERENCES Animal_Type_Dim(Animal_Type_ID);

ALTER TABLE Animal_Fact
ADD FOREIGN KEY (Primary_Breed_ID) REFERENCES Primary_Breed_Dim(Primary_Breed_ID);

ALTER TABLE Animal_Fact
ADD FOREIGN KEY (Gender_ID) REFERENCES Gender_Dim(Gender_ID);

ALTER TABLE Animal_Fact
ADD FOREIGN KEY (Primary_Color_ID) REFERENCES Primary_Color_Dim(Primary_Color_ID);

ALTER TABLE Animal_Fact
ADD FOREIGN KEY (Secondary_Color_ID) REFERENCES Secondary_Color_Dim(Secondary_Color_ID);


--SHELTER--
-----------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE Shelter_Dim(
	Shelter_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Shelter_Name varchar(100) NOT NULL,
	Shelter_Location varchar(100) NOT NULL
) ON [PRIMARY]



--INTAKE--
-----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Intake_Type_Dim(
	Intake_Type_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Intake_Type varchar(50) NOT NULL
) ON [PRIMARY]

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Intake_Sub_Type_Dim(
	Intake_Sub_Type_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Intake_Sub_Type varchar(50) NOT NULL
) ON [PRIMARY]

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Intake_Fertility_Dim(
	Intake_Fertility_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Intake_Fertility varchar(50) NOT NULL
) ON [PRIMARY]

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Intake_Status_Dim(
	Intake_Status_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Intake_Status varchar(50) NOT NULL
) ON [PRIMARY]

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Intake_Health_Dim(
	Intake_Health_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Intake_Health varchar(50) NOT NULL
) ON [PRIMARY]

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Intake_Reason_Dim(
	Intake_Reason_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Intake_Reason varchar(50) NOT NULL
) ON [PRIMARY]

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Intake_Date_Dim(
	Intake_Date date NOT NULL PRIMARY KEY,
	Intake_Month AS DATEPART(MONTH, [Intake_Date]),
	Intake_Year AS DATEPART(YEAR, [Intake_Date])
) ON [PRIMARY]

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Intake_Fact(
	Intake_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Intake_Type_ID int NOT NULL,
	Intake_Sub_Type_ID int NOT NULL,
	Intake_Fertility_ID int NOT NULL,
	Intake_Status_ID int NOT NULL,
	Intake_Health_ID int NOT NULL,
	Intake_Reason_ID int NOT NULL,
	Intake_Date date
) ON [PRIMARY]

ALTER TABLE Intake_Fact
ADD FOREIGN KEY (Intake_Type_ID) REFERENCES Intake_Type_Dim(Intake_Type_ID);

ALTER TABLE Intake_Fact
ADD FOREIGN KEY (Intake_Sub_Type_ID) REFERENCES Intake_Sub_Type_Dim(Intake_Sub_Type_ID);

ALTER TABLE Intake_Fact
ADD FOREIGN KEY (Intake_Fertility_ID) REFERENCES Intake_Fertility_Dim(Intake_Fertility_ID);

ALTER TABLE Intake_Fact
ADD FOREIGN KEY (Intake_Status_ID) REFERENCES Intake_Status_Dim(Intake_Status_ID);

ALTER TABLE Intake_Fact
ADD FOREIGN KEY (Intake_Health_ID) REFERENCES Intake_Health_Dim(Intake_Health_ID);

ALTER TABLE Intake_Fact
ADD FOREIGN KEY (Intake_Reason_ID) REFERENCES Intake_Reason_Dim(Intake_Reason_ID);

ALTER TABLE Intake_Fact
ADD FOREIGN KEY (Intake_Date) REFERENCES Intake_Date_Dim(Intake_Date);


--OUTCOME--
-----------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE Outcome_Type_Dim(
	Outcome_Type_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Outcome_Type varchar(50) NOT NULL
) ON [PRIMARY]

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Outcome_Sub_Type_Dim(
	Outcome_Sub_Type_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Outcome_Sub_Type varchar(50) NOT NULL
) ON [PRIMARY]

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Outcome_Fertility_Dim(
	Outcome_Fertility_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Outcome_Fertility varchar(50) NOT NULL
) ON [PRIMARY]

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Outcome_Health_Dim(
	Outcome_Health_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Outcome_Health varchar(50) NOT NULL
) ON [PRIMARY]

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Outcome_Date_Dim(
	Outcome_Date date NOT NULL PRIMARY KEY,
	Outcome_Month AS DATEPART(MONTH, [Outcome_Date]),
	Outcome_Year AS DATEPART(YEAR, [Outcome_Date])
) ON [PRIMARY]

----------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Outcome_Fact(
	Outcome_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Outcome_Type_ID int NOT NULL,
	Outcome_Sub_Type_ID int NOT NULL,
	Outcome_Fertility_ID int NOT NULL,
	Outcome_Health_ID int NOT NULL,
	Outcome_Date date
) ON [PRIMARY]

ALTER TABLE Outcome_Fact
ADD FOREIGN KEY (Outcome_Type_ID) REFERENCES Outcome_Type_Dim(Outcome_Type_ID);

ALTER TABLE Outcome_Fact
ADD FOREIGN KEY (Outcome_Sub_Type_ID) REFERENCES Outcome_Sub_Type_Dim(Outcome_Sub_Type_ID);

ALTER TABLE Outcome_Fact
ADD FOREIGN KEY (Outcome_Fertility_ID) REFERENCES Outcome_Fertility_Dim(Outcome_Fertility_ID);

ALTER TABLE Outcome_Fact
ADD FOREIGN KEY (Outcome_Health_ID) REFERENCES Outcome_Health_Dim(Outcome_Health_ID);

ALTER TABLE Outcome_Fact
ADD FOREIGN KEY (Outcome_Date) REFERENCES Outcome_Date_Dim(Outcome_Date);



--VISIT--
----------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE Visit_Fact(
	Visit_ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Animal_ID varchar(50) NOT NULL,
	Shelter_ID int NOT NULL,
	Animal_Cost_ID int NOT NULL,
	Intake_ID int NOT NULL,
	Outcome_ID int NOT NULL
) ON [PRIMARY]

ALTER TABLE Visit_Fact
ADD FOREIGN KEY (Animal_ID) REFERENCES Animal_Fact(Animal_ID);

ALTER TABLE Visit_Fact
ADD FOREIGN KEY (Shelter_ID) REFERENCES Shelter_Dim(Shelter_ID);

ALTER TABLE Visit_Fact
ADD FOREIGN KEY (Animal_Cost_ID) REFERENCES Animal_Cost_Fact(Animal_Cost_ID);

ALTER TABLE Visit_Fact
ADD FOREIGN KEY (Intake_ID) REFERENCES Intake_Fact(Intake_ID);

ALTER TABLE Visit_Fact
ADD FOREIGN KEY (Outcome_ID) REFERENCES Outcome_Fact(Outcome_ID);

