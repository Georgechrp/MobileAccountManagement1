CREATE TABLE users
	(username	varchar(20),
	 password 	varchar(20),
	 first_name	varchar(20),
	 surname	varchar(20),
	 role	    varchar(20),
	 PRIMARY KEY (username)
	);

CREATE TABLE programs
	(program_id varchar(10),
	 program_name varchar(20),
	 base_charge double precision,
	 additional_charge double precision,
	 minutes integer,
	 PRIMARY KEY (program_id)
	);

CREATE TABLE phone_numbers
	(program_id varchar(10),
	 phone_number varchar(10),
	 PRIMARY KEY (phone_number),
	 FOREIGN KEY (program_id) REFERENCES programs(program_id)
	);
	

CREATE TABLE sellers
	(username varchar(20),
	 company varchar(20),
	 PRIMARY KEY (username),
	 FOREIGN KEY (username) REFERENCES users(username)
	);	

CREATE TABLE clients
	(username	varchar(20),
	 balance numeric(20,2),
	 phone_number varchar(20),
	 AFM varchar(11),
	 PRIMARY KEY (username),
	 FOREIGN KEY (username) REFERENCES users(username),
	 FOREIGN KEY (phone_number) REFERENCES phone_numbers(phone_number)
	);	

CREATE TABLE bills
	(bill_id varchar(20),
	 username varchar(20),
	 number_of_calls numeric(10,0),
	 billing_month smallint,
	 PRIMARY KEY (bill_id),
     FOREIGN KEY (username) REFERENCES clients(username)
	);	

CREATE TABLE calls
	(call_id varchar(20),
	 startTime timestamp,
	 endTime timestamp,
	 caller_phone_number varchar(20),
	 receiver_phone_number varchar(20),
	 PRIMARY KEY (call_id),
	 FOREIGN KEY (caller_phone_number) REFERENCES phone_numbers(phone_number),
	 FOREIGN KEY (receiver_phone_number) REFERENCES phone_numbers(phone_number)
	);
		
	
CREATE TABLE sellers_programs
	(program_id varchar(10),
	 seller_username varchar(20),
	 client_username varchar(20),
	 FOREIGN KEY (seller_username) REFERENCES sellers(username),
	 FOREIGN KEY (client_username) REFERENCES clients(username),
	 FOREIGN KEY (program_id) REFERENCES programs(program_id)
	);	