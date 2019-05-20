CREATE TABLE IF NOT EXISTS trips (
				id IDENTITY PRIMARY KEY,
				placeid BIGINT NOT NULL,
				tripname varchar(100) NOT NULL,
				description varchar(600)  NOT NULL,
				startDate DATE NOT NULL,
				EndDate DATE NOT NULL
);


insert into trips values(2, 2,'tripname2','description','2016-01-02','2016-02-03');
