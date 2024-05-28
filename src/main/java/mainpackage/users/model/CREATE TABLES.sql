CREATE TABLE users
	(username	varchar(20),
	 password 	varchar(20),
	 first_name	varchar(20),
	 surname	varchar(20),
	 role	    varchar(20),
	 primary key (username)
	);
	
CREATE TABLE sellers
	(company varchar(20),
	 username	varchar(20)
	);	

CREATE TABLE clients
	(username	varchar(20),
	 balance numeric(20,2),
	 phone_numbernumeric(10,0),
	 AFM varchar(11)
	);	

CREATE TABLE bills
	(number_of_calls numeric(10,0),
	 phone_number numeric(10,0),
	 billing_month smallint
	);	

CREATE TABLE calls
	(startTime timestamp,
	 endTime timestamp,
	 caller_phone_number numeric(10,0),
	 receiver_phone_number numeric(10,0)
	);
	
CREATE TABLE programs
	(base_charge double precision,
	 additional_charge double precision,
	 minutes integer,
	 programm_name varchar(20)
	);